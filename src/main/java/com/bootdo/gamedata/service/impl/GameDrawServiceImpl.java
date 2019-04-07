package com.bootdo.gamedata.service.impl;

import com.bootdo.gamedata.dao.GameDrawDao;
import com.bootdo.gamedata.domain.GameDrawDO;
import com.bootdo.gamedata.qo.GameDrawQo;
import com.bootdo.gamedata.service.GameDrawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class GameDrawServiceImpl implements GameDrawService {

    private final GameDrawDao gameDrawDao;

    @Autowired
    public GameDrawServiceImpl(GameDrawDao gameDrawDao) {
        this.gameDrawDao = gameDrawDao;
    }

    @Override
    public void saveGameDraw(GameDrawDO gameDrawDO) {
        if (gameDrawDO.getId() == null) {
            Date now = new Date();
            gameDrawDO.setGmtCreate(now);
            gameDrawDO.setGmtModify(now);
            gameDrawDao.save(gameDrawDO);
        } else {
            GameDrawDO drawDO = gameDrawDao.getOne(gameDrawDO.getId());
            drawDO.setDrawName(gameDrawDO.getDrawName());
            drawDO.setPicUrl(gameDrawDO.getPicUrl());
            drawDO.setDrawRatio(gameDrawDO.getDrawRatio());
            drawDO.setEnabled(gameDrawDO.getEnabled());
            drawDO.setSort(gameDrawDO.getSort());
            drawDO.setGmtModify(new Date());
            gameDrawDao.save(drawDO);
        }
    }

    @Override
    public GameDrawDO getById(Integer id) {
        return gameDrawDao.getOne(id);
    }

    @Override
    public void deleteGameDraw(Integer drawId) {
        gameDrawDao.deleteById(drawId);
    }

    @Override
    public Page<GameDrawDO> findPage(GameDrawQo qo, Pageable pageable) {
        return gameDrawDao.findAll(pageable);
    }

    @Override
    public List<GameDrawDO> listDraw() {
        return null;
    }
}
