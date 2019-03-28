package com.bootdo.gamedata.service.impl;

import com.bootdo.gamedata.dao.GameAdvDao;
import com.bootdo.gamedata.domain.GameAdvDO;
import com.bootdo.gamedata.qo.GameAdvQo;
import com.bootdo.gamedata.service.GameAdvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class GameAdvServiceImpl implements GameAdvService {

    private final GameAdvDao gameAdvDao;

    @Autowired
    public GameAdvServiceImpl(GameAdvDao gameAdvDao) {
        this.gameAdvDao = gameAdvDao;
    }

    @Override
    public void saveGameAdv(GameAdvDO gameAdvDO) {
        if(gameAdvDO.getId() == null){
            gameAdvDO.setGmtCreate(new Date());
        }
        gameAdvDao.save(gameAdvDO);

    }

    @Override
    public void deleteGameAdv(Integer advId) {
        if(advId != null){
            gameAdvDao.deleteById(advId);
        }
    }

    @Override
    public Page<GameAdvDO> findPage(GameAdvQo qo, Pageable pageable) {
        return gameAdvDao.findAll(pageable);
    }

    @Override
    public List<GameAdvDO> listAdv() {
        return gameAdvDao.findAll();
    }
}
