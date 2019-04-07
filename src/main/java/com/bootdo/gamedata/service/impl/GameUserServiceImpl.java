package com.bootdo.gamedata.service.impl;

import com.bootdo.common.utils.StringUtils;
import com.bootdo.gamedata.dao.GameUserAccountDao;
import com.bootdo.gamedata.dao.GameUserDao;
import com.bootdo.gamedata.domain.GameUserAccountDO;
import com.bootdo.gamedata.domain.GameUserDO;
import com.bootdo.gamedata.qo.GameUserQo;
import com.bootdo.gamedata.service.GameUserAccountService;
import com.bootdo.gamedata.service.GameUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

@Service
public class GameUserServiceImpl implements GameUserService {

    private final GameUserDao gameUserDao;

    private final GameUserAccountService gameUserAccountService;

    private final GameUserAccountDao gameUserAccountDao;

    @Autowired
    public GameUserServiceImpl(GameUserDao gameUserDao, GameUserAccountService gameUserAccountService, GameUserAccountDao gameUserAccountDao) {
        this.gameUserDao = gameUserDao;
        this.gameUserAccountService = gameUserAccountService;
        this.gameUserAccountDao = gameUserAccountDao;
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
    public void addGameUser(GameUserDO gameUserDO) {
        validParams(gameUserDO);
        gameUserDO.setId(null);
        Date date = new Date();
        gameUserDO.setGmtCreate(date);
        gameUserDO.setGmtModify(date);
        gameUserDao.save(gameUserDO);

        GameUserAccountDO gameUserAccountDO = new GameUserAccountDO();
        gameUserAccountDO.setUserId(gameUserDO.getId());
        gameUserAccountDO.setOpenId(gameUserDO.getOpenId());
        gameUserAccountDO.setDrawCount(0);
        gameUserAccountDO.setCoinCount(BigDecimal.ZERO);
        gameUserAccountDO.setDiamondCount(BigDecimal.ZERO);
        gameUserAccountDO.setScore(BigDecimal.ZERO);
        gameUserAccountDO.setGmtCreate(date);
        gameUserAccountDO.setGmtModify(date);
        gameUserAccountDao.save(gameUserAccountDO);
    }

    private void validParams(GameUserDO gameUserDO) {
        if (StringUtils.isBlank(gameUserDO.getOpenId()) || StringUtils.isBlank(gameUserDO.getUserName()) || StringUtils.isBlank(gameUserDO.getAvatarUrl())) {
            throw new RuntimeException("缺少必要参数,用户添加失败!");
        }
        if (gameUserDao.findByOpenId(gameUserDO.getOpenId()) != null) {
            throw new RuntimeException("该用户已经存在");
        }
    }

}
