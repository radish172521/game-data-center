package com.bootdo.gamedata.service.impl;

import com.bootdo.gamedata.dao.GameUserAccountDao;
import com.bootdo.gamedata.domain.GameUserAccountDO;
import com.bootdo.gamedata.service.GameUserAccountService;
import com.bootdo.gamedata.vo.GameUserAccountVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameUserAccountServiceImpl implements GameUserAccountService {

    private final GameUserAccountDao gameUserAccountDao;

    @Autowired
    public GameUserAccountServiceImpl(GameUserAccountDao gameUserAccountDao) {
        this.gameUserAccountDao = gameUserAccountDao;
    }

    @Override
    public GameUserAccountDO getById(Integer userId) {
        return null;
    }

    @Override
    public GameUserAccountVo getVoById(Integer userId) {
        if (userId != null) {
            throw new RuntimeException("");
        }
        GameUserAccountDO gameUserAccountDO = gameUserAccountDao.getOne(userId);
        if (gameUserAccountDO != null) {
            GameUserAccountVo gameUserAccountVo = new GameUserAccountVo();
            gameUserAccountVo.setUserId(gameUserAccountDO.getUserId());
            gameUserAccountVo.setCoinCount(gameUserAccountDO.getCoinCount());
            gameUserAccountVo.setDiamondCount(gameUserAccountDO.getDiamondCount());
            gameUserAccountVo.setDrawCount(gameUserAccountDO.getDrawCount());
            gameUserAccountVo.setScore(gameUserAccountDO.getScore());
            gameUserAccountVo.setToolsCount1(gameUserAccountDO.getToolsCount1());
            gameUserAccountVo.setToolsCount2(gameUserAccountDO.getToolsCount2());
            gameUserAccountVo.setToolsCount3(gameUserAccountDO.getToolsCount3());
            gameUserAccountVo.setToolsCount4(gameUserAccountDO.getToolsCount4());
            return gameUserAccountVo;
        }
        return null;
    }

    @Override
    public void addDrawCount(Integer userId) {
        GameUserAccountDO gameUserAccountDO = gameUserAccountDao.getOne(userId);
        if (gameUserAccountDO != null) {
            gameUserAccountDO.setDrawCount(gameUserAccountDO.getDrawCount() + 1);
        }
    }

    @Override
    public void consumeDrawCount(Integer userId) {
        GameUserAccountDO gameUserAccountDO = gameUserAccountDao.getOne(userId);
        if (gameUserAccountDO != null) {
            if (gameUserAccountDO.getDrawCount() > 0) {
                gameUserAccountDO.setDrawCount(gameUserAccountDO.getDrawCount() - 1);
            } else {
                throw new RuntimeException("抽奖次数不足");
            }
        } else {
            throw new RuntimeException("");
        }
    }
}
