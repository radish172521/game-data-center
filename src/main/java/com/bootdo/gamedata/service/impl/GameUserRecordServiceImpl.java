package com.bootdo.gamedata.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.bootdo.common.utils.StringUtils;
import com.bootdo.gamedata.dao.*;
import com.bootdo.gamedata.domain.*;
import com.bootdo.gamedata.dto.GameTaskInfoDto;
import com.bootdo.gamedata.enums.RecordType;
import com.bootdo.gamedata.qo.GameUserRecordQo;
import com.bootdo.gamedata.service.GameUserRecordService;
import com.bootdo.gamedata.vo.GameUserRecordRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class GameUserRecordServiceImpl implements GameUserRecordService {

    private final GameUserRecordDao gameUserRecordDao;

    private final GameDrawDao gameDrawDao;

    private final GameUserDao gameUserDao;

    private final GameTaskDao gameTaskDao;

    private final ExchangeGoodsDao exchangeGoodsDao;

    @Autowired
    public GameUserRecordServiceImpl(GameUserRecordDao gameUserRecordDao, GameDrawDao gameDrawDao, GameUserDao gameUserDao, GameTaskDao gameTaskDao, ExchangeGoodsDao exchangeGoodsDao) {
        this.gameUserRecordDao = gameUserRecordDao;
        this.gameDrawDao = gameDrawDao;
        this.gameUserDao = gameUserDao;
        this.gameTaskDao = gameTaskDao;
        this.exchangeGoodsDao = exchangeGoodsDao;
    }

    @Override
    public void addGameRecord(GameUserRecordRequest request) {
        validParams(request);
        GameUserDO userDO = gameUserDao.findByOpenId(request.getOpenId());
        GameUserRecordDO userDrawRecordDO = new GameUserRecordDO();
        if (RecordType.TASK.equals(request.getRecordType())) {
            GameTaskDO gameTaskDO = gameTaskDao.getOne(request.getBusinessId());
            if (gameTaskDO != null) {
                userDrawRecordDO.setBusinessName(gameTaskDO.getGameTaskName());
                userDrawRecordDO.setBusinessId(gameTaskDO.getId());
                GameTaskInfoDto gameTaskInfoDto = new GameTaskInfoDto(gameTaskDO);
                userDrawRecordDO.setData(JSONObject.toJSONString(gameTaskInfoDto));
            }
        } else if (RecordType.DRAW.equals(request.getRecordType())) {
            GameDrawDO gameDrawDO = gameDrawDao.getOne(request.getBusinessId());
            if (gameDrawDO != null) {
                userDrawRecordDO.setBusinessName(gameDrawDO.getDrawName());
                userDrawRecordDO.setBusinessId(gameDrawDO.getId());
            } else {
                throw new RuntimeException("对应的任务或用户不存在!");
            }
        } else if (RecordType.EXCHANGE.equals(request.getRecordType())) {
            ExchangeGoodsDO exchangeGoodsDO = exchangeGoodsDao.getOne(request.getBusinessId());
            if (exchangeGoodsDO != null) {
                userDrawRecordDO.setBusinessId(exchangeGoodsDO.getId());
                userDrawRecordDO.setBusinessName(exchangeGoodsDO.getGoodsName());
            }
        } else {
            //不作处理
            throw new RuntimeException("找不到对应的类型!");
        }
        Date now = new Date();
        userDrawRecordDO.setGmtCreate(now);
        userDrawRecordDO.setGmtModify(now);

        userDrawRecordDO.setOpenId(userDO.getOpenId());
        userDrawRecordDO.setGameUserId(userDO.getId());
        userDrawRecordDO.setGameUserName(userDO.getUserName());
        gameUserRecordDao.save(userDrawRecordDO);

    }

    private void validParams(GameUserRecordRequest request) {
        if (StringUtils.isBlank(request.getOpenId())) {

        }
    }

    @Override
    public GameUserRecordDO getById(Integer id) {
        return gameUserRecordDao.getOne(id);
    }

    @Override
    public Page<GameUserRecordDO> findPage(GameUserRecordQo qo, Pageable pageable) {
        Specification<GameUserRecordDO> doSpecification = (Specification<GameUserRecordDO>) (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicate = new ArrayList<>();
            if (qo.getRecordType() != null) {
                predicate.add(criteriaBuilder.equal(root.get("recordType").as(RecordType.class), qo.getRecordType()));
            }
            Predicate[] p = new Predicate[predicate.size()];
            return criteriaBuilder.and(predicate.toArray(p));
        };
        return gameUserRecordDao.findAll(doSpecification, pageable);
    }

    @Override
    public void delete(Integer taskId) {
        gameUserRecordDao.deleteById(taskId);
    }
}
