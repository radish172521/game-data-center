package com.bootdo.gamedata.service.impl;

import com.bootdo.gamedata.dao.GameUserAccountDao;
import com.bootdo.gamedata.domain.GameUserAccountDO;
import com.bootdo.gamedata.enums.RecordType;
import com.bootdo.gamedata.service.GameUserAccountService;
import com.bootdo.gamedata.service.GameUserRecordService;
import com.bootdo.gamedata.vo.GameUserAccountVo;
import com.bootdo.gamedata.vo.GameUserRecordRequest;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class GameUserAccountServiceImpl implements GameUserAccountService {

    private final GameUserAccountDao gameUserAccountDao;

    private final GameUserRecordService gameUserRecordService;

    @Autowired
    public GameUserAccountServiceImpl(GameUserAccountDao gameUserAccountDao, GameUserRecordService gameUserRecordService) {
        this.gameUserAccountDao = gameUserAccountDao;
        this.gameUserRecordService = gameUserRecordService;
    }

    @Override
    public GameUserAccountDO getById(Integer userId) {
        return null;
    }

    @Override
    public GameUserAccountVo getVoById(Integer userId) {
        if (userId == null) {
            throw new RuntimeException("");
        }
        GameUserAccountDO gameUserAccountDO = gameUserAccountDao.getOne(userId);
        if (gameUserAccountDO.getUserId() != null) {
            GameUserAccountVo gameUserAccountVo = new GameUserAccountVo();
            gameUserAccountVo.setUserId(gameUserAccountDO.getUserId());
            gameUserAccountVo.setCoinCount(gameUserAccountDO.getCoinCount());
            gameUserAccountVo.setDiamondCount(gameUserAccountDO.getDiamondCount());
            gameUserAccountVo.setDrawCount(gameUserAccountDO.getDrawCount());
            gameUserAccountVo.setScore(gameUserAccountDO.getScore());
            if (gameUserAccountDO.getSignedTime() == null) {
                gameUserAccountVo.setSigned(Boolean.FALSE);
            } else {
                LocalDate now = LocalDate.now();
                LocalDate signedDate = new LocalDate(gameUserAccountDO.getSignedTime());
                if (now.equals(signedDate)) {
                    gameUserAccountVo.setSigned(Boolean.TRUE);
                } else {
                    gameUserAccountVo.setSigned(Boolean.FALSE);
                }
            }
            return gameUserAccountVo;
        }
        return null;
    }

    @Override
    public void signed(Integer userId) {
        GameUserAccountDO gameUserAccountDO = gameUserAccountDao.getOne(userId);
        if (gameUserAccountDO.getUserId() != null) {
            if (gameUserAccountDO.getSignedTime() == null) {
                gameUserAccountDO.setSignedTime(new Date());
                gameUserRecordService.addGameRecord(new GameUserRecordRequest(gameUserAccountDO.getUserId(), gameUserAccountDO.getOpenId(), null, RecordType.SIGN));
            } else {
                LocalDate now = LocalDate.now();
                LocalDate signedDate = new LocalDate(gameUserAccountDO.getSignedTime());
                if (now.equals(signedDate)) {
                    throw new RuntimeException("今天已经签到啦,明天再来吧");
                } else {
                    gameUserAccountDO.setSignedTime(new Date());
                    gameUserRecordService.addGameRecord(new GameUserRecordRequest(gameUserAccountDO.getUserId(), gameUserAccountDO.getOpenId(), null, RecordType.SIGN));
                }
            }
            gameUserAccountDao.save(gameUserAccountDO);
        }
    }


    @Override
    public void addDrawCount(Integer userId) {
        GameUserAccountDO gameUserAccountDO = gameUserAccountDao.getOne(userId);
        if (gameUserAccountDO.getUserId() != null) {
            gameUserAccountDO.setDrawCount(gameUserAccountDO.getDrawCount() + 1);
            gameUserAccountDao.save(gameUserAccountDO);
        }
    }

    @Override
    public void consumeDrawCount(Integer userId) {
        GameUserAccountDO gameUserAccountDO = gameUserAccountDao.getOne(userId);
        if (gameUserAccountDO.getUserId() != null) {
            if (gameUserAccountDO.getDrawCount() > 0) {
                gameUserAccountDO.setDrawCount(gameUserAccountDO.getDrawCount() - 1);
                gameUserAccountDao.save(gameUserAccountDO);
            } else {
                throw new RuntimeException("抽奖次数不足");
            }
        } else {
            throw new RuntimeException("");
        }
    }
}
