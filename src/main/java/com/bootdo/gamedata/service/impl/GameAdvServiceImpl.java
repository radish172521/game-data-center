package com.bootdo.gamedata.service.impl;

import com.bootdo.gamedata.dao.GameAdvDao;
import com.bootdo.gamedata.dao.GameAdvInfoDao;
import com.bootdo.gamedata.domain.GameAdvDO;
import com.bootdo.gamedata.domain.GameAdvInfoDO;
import com.bootdo.gamedata.qo.GameAdvQo;
import com.bootdo.gamedata.service.GameAdvService;
import com.bootdo.gamedata.vo.GameAdvVo;
import com.google.common.collect.Lists;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class GameAdvServiceImpl implements GameAdvService {

    private final GameAdvDao gameAdvDao;

    private final GameAdvInfoDao gameAdvInfoDao;

    @Autowired
    public GameAdvServiceImpl(GameAdvDao gameAdvDao, GameAdvInfoDao gameAdvInfoDao) {
        this.gameAdvDao = gameAdvDao;
        this.gameAdvInfoDao = gameAdvInfoDao;
    }

    @Override
    public void saveGameAdv(GameAdvDO gameAdvDO) {
        if (gameAdvDO.getId() == null) {
            Date now = new Date();
            gameAdvDO.setGmtCreate(now);
            gameAdvDO.setGmtModify(now);
            gameAdvDO.setClickCount(0);
            gameAdvDO.setAccreditCount(0);
            gameAdvDao.save(gameAdvDO);
        } else {
            GameAdvDO advDO = gameAdvDao.getOne(gameAdvDO.getId());
            advDO.setAdvName(gameAdvDO.getAdvName());
            advDO.setPicUrl(gameAdvDO.getPicUrl());
            advDO.setEnabled(gameAdvDO.getEnabled());
            advDO.setSort(gameAdvDO.getSort());
            advDO.setGmtModify(new Date());
            gameAdvDao.save(advDO);
        }


    }

    @Override
    public GameAdvDO getById(Integer id) {
        return gameAdvDao.getOne(id);
    }

    @Override
    public void deleteGameAdv(Integer advId) {
        if (advId != null) {
            gameAdvDao.deleteById(advId);
        }
    }

    @Override
    @Transactional
    public Page<GameAdvDO> findPage(GameAdvQo qo, Pageable pageable) {
        Page<GameAdvDO> gameAdvDOS = gameAdvDao.findAll(pageable);
        for (GameAdvDO gameAdvDO : gameAdvDOS.getContent()) {
            LocalDate localDate = LocalDate.now();
            GameAdvInfoDO gameAdvInfoToday = gameAdvInfoDao.findByAdvIdAndGmtCreateBetween(gameAdvDO.getId(), localDate.toDate(), localDate.plusDays(1).toDate());
            if (gameAdvInfoToday == null) {
                gameAdvInfoToday = new GameAdvInfoDO();
                gameAdvInfoToday.setAdvId(gameAdvDO.getId());
                gameAdvInfoToday.setGmtCreate(localDate.toDate());
                gameAdvInfoToday.setUvCount(0);
                gameAdvInfoDao.save(gameAdvInfoToday);
            }
            gameAdvDO.setUvCount(gameAdvInfoToday.getUvCount());

            GameAdvInfoDO gameAdvInfoYest = gameAdvInfoDao.findByAdvIdAndGmtCreateBetween(gameAdvDO.getId(), localDate.minusDays(1).toDate(), localDate.toDate());
            if (gameAdvInfoYest == null) {
                gameAdvInfoYest = new GameAdvInfoDO();
                gameAdvInfoYest.setAdvId(gameAdvDO.getId());
                gameAdvInfoYest.setGmtCreate(localDate.minusDays(1).toDate());
                gameAdvInfoYest.setUvCount(0);
                gameAdvInfoDao.save(gameAdvInfoYest);
            }
            gameAdvDO.setUvCountYest(gameAdvInfoYest.getUvCount());
        }
        return gameAdvDOS;
    }

    @Override
    public List<GameAdvVo> listAdv() {
        List<GameAdvDO> gameAdvDOS = gameAdvDao.findByEnabledOrderBySortAsc(1);
        List<GameAdvVo> voList = Lists.newLinkedList();
        for (GameAdvDO gameAdvDO : gameAdvDOS) {
            GameAdvVo gameAdvVo = new GameAdvVo();
            gameAdvVo.setAdvChannelId(gameAdvDO.getAdvChannelId());
            gameAdvVo.setAdvId(gameAdvDO.getId());
            gameAdvVo.setAdvName(gameAdvDO.getAdvName());
            gameAdvVo.setPicUrl(gameAdvDO.getPicUrl());
            gameAdvVo.setSort(gameAdvDO.getSort());
            voList.add(gameAdvVo);
        }
        return voList;
    }

    @Override
    public void clickCount(String advId, String userId) {
//        gameAdvDao.fin
    }

}
