package com.bootdo.gamedata.service.impl;

import com.bootdo.gamedata.dao.GameUserDao;
import com.bootdo.gamedata.domain.GameUserDO;
import com.bootdo.gamedata.qo.GameUserQo;
import com.bootdo.gamedata.service.GameUserAccountService;
import com.bootdo.gamedata.service.GameUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameUserServiceImpl implements GameUserService {

    private final GameUserDao gameUserDao;

    private final GameUserAccountService gameUserAccountService;

    @Autowired
    public GameUserServiceImpl(GameUserDao gameUserDao, GameUserAccountService gameUserAccountService) {
        this.gameUserDao = gameUserDao;
        this.gameUserAccountService = gameUserAccountService;
    }


    @Override
    public void saveGameUser(GameUserDO gameUserDO) {

    }

    @Override
    public GameUserDO getById(Integer id) {
        return gameUserDao.getOne(id);
    }

    @Override
    public void deleteGameUser(Integer userId) {
        gameUserDao.deleteById(userId);
    }

    @Override
    public Page<GameUserDO> findPage(GameUserQo qo, Pageable pageable) {
        Page<GameUserDO> page = gameUserDao.findAll(pageable);
        for (GameUserDO userDO : page.getContent()) {
            userDO.setGameUserAccountDO(gameUserAccountService.getById(userDO.getId()));
        }
        return page;
    }

    @Override
    public List<GameUserDO> listTask() {
        return null;
    }
}
