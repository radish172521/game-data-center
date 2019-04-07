package com.bootdo.gamedata.service.impl;

import com.bootdo.common.utils.StringUtils;
import com.bootdo.gamedata.dao.ExchangeGoodsDao;
import com.bootdo.gamedata.dao.GameUserAccountDao;
import com.bootdo.gamedata.dao.GameUserDao;
import com.bootdo.gamedata.dao.GameUserRecordDao;
import com.bootdo.gamedata.domain.ExchangeGoodsDO;
import com.bootdo.gamedata.domain.GameUserAccountDO;
import com.bootdo.gamedata.domain.GameUserDO;
import com.bootdo.gamedata.domain.GameUserRecordDO;
import com.bootdo.gamedata.enums.RecordType;
import com.bootdo.gamedata.qo.ExchangeGoodsQo;
import com.bootdo.gamedata.service.ExchangeGoodsService;
import com.bootdo.gamedata.vo.GameExchangeGoodsVo;
import com.bootdo.gamedata.vo.GoodsRequest;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class ExchangeGoodsServiceImpl implements ExchangeGoodsService {

    private final ExchangeGoodsDao exchangeGoodsDao;

    private final GameUserDao gameUserDao;

    private final GameUserAccountDao gameUserAccountDao;

    private final GameUserRecordDao gameUserRecordDao;

    @Autowired
    public ExchangeGoodsServiceImpl(ExchangeGoodsDao exchangeGoodsDao, GameUserDao gameUserDao, GameUserAccountDao gameUserAccountDao, GameUserRecordDao gameUserRecordDao) {
        this.exchangeGoodsDao = exchangeGoodsDao;
        this.gameUserDao = gameUserDao;
        this.gameUserAccountDao = gameUserAccountDao;
        this.gameUserRecordDao = gameUserRecordDao;
    }

    @Override
    public void saveGoods(GoodsRequest goodsRequest) {
        ExchangeGoodsDO exchangeGoodsDO;
        if (goodsRequest.getId() != null) {
            exchangeGoodsDO = exchangeGoodsDao.getOne(goodsRequest.getId());
        } else {
            exchangeGoodsDO = new ExchangeGoodsDO();
            exchangeGoodsDO.setGmtCreate(new Date());
        }
        exchangeGoodsDO.setGoodsName(goodsRequest.getGoodsName());
        exchangeGoodsDO.setEnabled(goodsRequest.getEnabled());
        if (StringUtils.isNotBlank(goodsRequest.getGoodsPictureUrl())) {
            exchangeGoodsDO.setGoodsPictureUrl(goodsRequest.getGoodsPictureUrl());
        }
        exchangeGoodsDO.setGoodsPrice(goodsRequest.getGoodsPrice());
        exchangeGoodsDO.setSort(goodsRequest.getSort());
        exchangeGoodsDO.setGmtModify(new Date());
        exchangeGoodsDao.save(exchangeGoodsDO);

    }

    @Override
    public ExchangeGoodsDO getById(Integer id) {
        return exchangeGoodsDao.getOne(id);
    }

    @Override
    public void doExchangeGoods(Integer userId, Integer goodsId) {
        GameUserAccountDO userDO = gameUserAccountDao.getOne(userId);
        ExchangeGoodsDO exchangeGoodsDO = exchangeGoodsDao.getOne(goodsId);
        if (userDO != null && exchangeGoodsDO != null) {
            if (userDO.getCoinCount().min(exchangeGoodsDO.getGoodsPrice()).compareTo(BigDecimal.ZERO) >= 0) {
                userDO.setCoinCount(userDO.getCoinCount().min(exchangeGoodsDO.getGoodsPrice()));
                gameUserAccountDao.save(userDO);

                GameUserRecordDO gameUserRecordDO = new GameUserRecordDO();
                gameUserRecordDO.setGameUserId(userId);
                GameUserDO gameUserDO = gameUserDao.getOne(userId);
                if (gameUserDO != null) {
                    gameUserRecordDO.setGameUserName(gameUserDO.getUserName());
                    gameUserRecordDO.setOpenId(gameUserDO.getOpenId());
                }
                Date now = new Date();
                gameUserRecordDO.setGmtCreate(now);
                gameUserRecordDO.setGmtModify(now);

                gameUserRecordDO.setBusinessId(exchangeGoodsDO.getId());
                gameUserRecordDO.setBusinessName(exchangeGoodsDO.getGoodsName());
                gameUserRecordDO.setRecordType(RecordType.EXCHANGE);

                gameUserRecordDao.save(gameUserRecordDO);
            }
        } else {
            throw new RuntimeException("");
        }
    }

    @Override
    public void deleteGoods(Integer goodsId) {
        if (goodsId != null) {
            exchangeGoodsDao.deleteById(goodsId);
        } else {
            throw new RuntimeException("goodsID不能为空哦");
        }
    }

    @Override
    public void batchDelGoods(Integer[] goodsIs) {
        for (Integer id : goodsIs) {
            exchangeGoodsDao.deleteById(id);
        }
    }

    @Override
    public Page<ExchangeGoodsDO> findPage(ExchangeGoodsQo qo, Pageable pageable) {
        return exchangeGoodsDao.findAll((Specification<ExchangeGoodsDO>) (root, query, criteriaBuilder) -> {
            if (StringUtils.isNotBlank(qo.getGoodsName())) {
                Predicate p1 = criteriaBuilder.equal(root.get("goodsName").as(String.class), qo.getGoodsName());
                query.where(criteriaBuilder.and(p1));
            }
            return query.getRestriction();
        }, pageable);
//        return exchangeGoodsDao.findAll(pageable);
    }

    @Override
    public List<GameExchangeGoodsVo> findList() {
        List<ExchangeGoodsDO> goodsDOS = exchangeGoodsDao.findByEnabledOrderBySortAsc(1);
        List<GameExchangeGoodsVo> gameExchangeGoodsVos = Lists.newLinkedList();
        GameExchangeGoodsVo gameExchangeGoodsVo;
        for (ExchangeGoodsDO goodsDO : goodsDOS) {
            gameExchangeGoodsVo = new GameExchangeGoodsVo();
            gameExchangeGoodsVo.setGoodsName(goodsDO.getGoodsName());
            gameExchangeGoodsVo.setGoodsPictureUrl(goodsDO.getGoodsPictureUrl());
            gameExchangeGoodsVo.setGoodsPrice(goodsDO.getGoodsPrice());
            gameExchangeGoodsVo.setSort(goodsDO.getSort());
            gameExchangeGoodsVos.add(gameExchangeGoodsVo);
        }
        return gameExchangeGoodsVos;
    }
}
