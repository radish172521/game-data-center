package com.bootdo.gamedata.service.impl;

import com.bootdo.common.gameenum.GameTaskType;
import com.bootdo.gamedata.dao.GameTaskDao;
import com.bootdo.gamedata.dao.GameUserDao;
import com.bootdo.gamedata.dao.GameUserRecordDao;
import com.bootdo.gamedata.domain.GameTaskDO;
import com.bootdo.gamedata.domain.GameUserDO;
import com.bootdo.gamedata.domain.GameUserRecordDO;
import com.bootdo.gamedata.dto.GameTaskInfoDto;
import com.bootdo.gamedata.enums.RecordType;
import com.bootdo.gamedata.qo.GameTaskQo;
import com.bootdo.gamedata.service.GameTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class GameTaskServiceImpl implements GameTaskService {
    private final GameTaskDao gameTaskDao;

    private final GameUserDao gameUserDao;

    private final GameUserRecordDao gameUserRecordDao;

    @Autowired
    public GameTaskServiceImpl(GameTaskDao gameTaskDao, GameUserDao gameUserDao, GameUserRecordDao gameUserRecordDao) {
        this.gameTaskDao = gameTaskDao;
        this.gameUserDao = gameUserDao;
        this.gameUserRecordDao = gameUserRecordDao;
    }

    @Override
    public void saveGameTask(GameTaskDO gameTaskDO) {
        if (gameTaskDO.getId() == null) {
            Date now = new Date();
            gameTaskDO.setGmtCreate(now);
            gameTaskDO.setGmtModify(now);
            gameTaskDao.save(gameTaskDO);
        } else {
            GameTaskDO taskDO = gameTaskDao.getOne(gameTaskDO.getId());
            taskDO.setGameTaskName(gameTaskDO.getGameTaskName());
            taskDO.setGameTaskRewardType(gameTaskDO.getGameTaskRewardType());
            taskDO.setGameTaskType(gameTaskDO.getGameTaskType());
            taskDO.setEnabled(gameTaskDO.getEnabled());
            taskDO.setRewardCount(gameTaskDO.getRewardCount());
            taskDO.setRewardMultiple(gameTaskDO.getRewardMultiple());
            taskDO.setGmtModify(new Date());
            gameTaskDao.save(taskDO);
        }
    }

    @Override
    public GameTaskDO getById(Integer id) {
        return gameTaskDao.getOne(id);
    }

    @Override
    public void deleteGameTask(Integer taskId) {
        gameTaskDao.deleteById(taskId);
    }

    @Override
    public Page<GameTaskDO> findPage(GameTaskQo qo, Pageable pageable) {
        return gameTaskDao.findAll(pageable);
    }

    @Override
    public void doTask(Integer userId, Integer taskId) {
        if (userId != null && taskId != null) {
            GameUserDO userDO = gameUserDao.getOne(userId);
            GameTaskDO gameTaskDO = gameTaskDao.getOne(taskId);
            if (userDO != null && gameTaskDO != null) {
                if (gameTaskDO.getEnabled() != 1) {
                    throw new RuntimeException("该任务已经结束!");
                }
                GameUserRecordDO gameUserRecordDO = new GameUserRecordDO();
                gameUserRecordDO.setRecordType(RecordType.TASK);
                gameUserRecordDO.setGameTaskInfoDto(new GameTaskInfoDto(gameTaskDO));

                if (GameTaskType.SIGNED.equals(gameTaskDO.getGameTaskType())) {
//                    gameTaskDO.get
                }
            }
        }
    }
}
