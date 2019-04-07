package com.bootdo.gamedata.service.impl;

import com.bootdo.gamedata.dao.*;
import com.bootdo.gamedata.domain.*;
import com.bootdo.gamedata.enums.RecordType;
import com.bootdo.gamedata.qo.GameTaskQo;
import com.bootdo.gamedata.service.GameTaskService;
import com.bootdo.gamedata.service.GameUserRecordService;
import com.bootdo.gamedata.vo.GameTaskVo;
import com.bootdo.gamedata.vo.GameUserRecordRequest;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
public class GameTaskServiceImpl implements GameTaskService {
    private final GameTaskDao gameTaskDao;

    private final GameUserDao gameUserDao;

    private final GameUserRecordDao gameUserRecordDao;

    private final GameUserAccountDao gameUserAccountDao;

    private final GameUserTaskRelationDao gameUserTaskRelationDao;

    private final GameUserRecordService userRecordService;

    @Autowired
    public GameTaskServiceImpl(GameTaskDao gameTaskDao, GameUserDao gameUserDao, GameUserRecordDao gameUserRecordDao, GameUserAccountDao gameUserAccountDao, GameUserTaskRelationDao gameUserTaskRelationDao, GameUserRecordService userRecordService) {
        this.gameTaskDao = gameTaskDao;
        this.gameUserDao = gameUserDao;
        this.gameUserRecordDao = gameUserRecordDao;
        this.gameUserAccountDao = gameUserAccountDao;
        this.gameUserTaskRelationDao = gameUserTaskRelationDao;
        this.userRecordService = userRecordService;
    }

    @Override
    public void saveGameTask(GameTaskVo request) {
        GameTaskDO taskDO;
        if (request.getId() == null) {
            taskDO = new GameTaskDO();
            Date now = new Date();
            taskDO.setGmtCreate(now);
            taskDO.setGmtModify(now);
        } else {
            taskDO = gameTaskDao.getOne(request.getId());
        }
        taskDO.setTaskConstantKey(request.getTaskConstantKey());
        taskDO.setGameTaskName(request.getGameTaskName());
        taskDO.setRewardDrawTimes(request.getRewardDrawTimes());
        taskDO.setRewardIntegralMin(request.getRewardIntegralMin());
        taskDO.setRewardIntegralMax(request.getRewardIntegralMax());
        taskDO.setEnabled(request.getEnabled());
        taskDO.setEnabledTimesRule(request.getEnabledTimesRule());
        if (request.getEnabledTimesRule() == 1) {
            List<GameTaskRuleDO> gameTaskRuleDOS = Lists.newLinkedList();
            if (request.getTimes1() != null && request.getGameTaskType1() != null) {
                GameTaskRuleDO gameTaskRuleDO = new GameTaskRuleDO();
                gameTaskRuleDO.setTimes(request.getTimes1());
                gameTaskRuleDO.setGameTaskType(request.getGameTaskType1());
                gameTaskRuleDO.setGameTaskDO(taskDO);
                gameTaskRuleDOS.add(gameTaskRuleDO);
                if (request.getTimes2() != null && request.getGameTaskType2() != null) {
                    GameTaskRuleDO gameTaskRuleDO1 = new GameTaskRuleDO();
                    gameTaskRuleDO1.setTimes(request.getTimes2());
                    gameTaskRuleDO1.setGameTaskType(request.getGameTaskType2());
                    gameTaskRuleDO1.setGameTaskDO(taskDO);
                    gameTaskRuleDOS.add(gameTaskRuleDO1);
                    if (request.getTimes3() != null && request.getGameTaskType3() != null) {
                        GameTaskRuleDO gameTaskRuleDO2 = new GameTaskRuleDO();
                        gameTaskRuleDO2.setTimes(request.getTimes3());
                        gameTaskRuleDO2.setGameTaskType(request.getGameTaskType3());
                        gameTaskRuleDO2.setGameTaskDO(taskDO);
                        gameTaskRuleDOS.add(gameTaskRuleDO2);

                        if (request.getTimes4() != null && request.getGameTaskType4() != null) {
                            GameTaskRuleDO gameTaskRuleDO3 = new GameTaskRuleDO();
                            gameTaskRuleDO3.setTimes(request.getTimes4());
                            gameTaskRuleDO3.setGameTaskType(request.getGameTaskType4());
                            gameTaskRuleDO3.setGameTaskDO(taskDO);
                            gameTaskRuleDOS.add(gameTaskRuleDO3);

                            if (request.getTimes5() != null && request.getGameTaskType5() != null) {
                                GameTaskRuleDO gameTaskRuleDO4 = new GameTaskRuleDO();
                                gameTaskRuleDO4.setTimes(request.getTimes2());
                                gameTaskRuleDO4.setGameTaskType(request.getGameTaskType2());
                                gameTaskRuleDO4.setGameTaskDO(taskDO);
                                gameTaskRuleDOS.add(gameTaskRuleDO4);

                            }
                        }
                    }
                }
            }
            taskDO.setRuleDOList(gameTaskRuleDOS);
        } else {
            taskDO.setGameTaskType(request.getGameTaskType());
        }
        gameTaskDao.save(taskDO);
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
    public void doTask(Integer userId, String taskKey) {
        GameUserDO userDO = gameUserDao.getOne(userId);
        GameTaskDO gameTaskDO = gameTaskDao.findByTaskConstantKey(taskKey);
        if (userDO != null && gameTaskDO != null) {
            if (gameTaskDO.getEnabled() != 1) {
                throw new RuntimeException("该任务已经结束啦!");
            }
            GameUserAccountDO gameUserAccountDO = gameUserAccountDao.getOne(userId);
            if (gameTaskDO.getRewardDrawTimes() != null && gameTaskDO.getRewardDrawTimes() > 0) {
                gameUserAccountDO.setDrawCount(gameUserAccountDO.getDrawCount() + gameTaskDO.getRewardDrawTimes());
            }
            if (gameTaskDO.getRewardIntegralMin() != null && gameTaskDO.getRewardIntegralMax() != null) {
                gameUserAccountDO.setCoinCount(gameUserAccountDO.getCoinCount().add(BigDecimal.valueOf(getRandom(gameTaskDO.getRewardIntegralMin(), gameTaskDO.getRewardIntegralMax()))));
            }
            if (gameTaskDO.getEnabledTimesRule() == 1) {
                GameUserTaskRelationDO relationDO = gameUserTaskRelationDao.findByUserIdAndAndTaskId(userId, gameTaskDO.getId());
                if (relationDO == null) {
                    relationDO = new GameUserTaskRelationDO();
                    relationDO.setTaskId(gameTaskDO.getId());
                    relationDO.setUserId(userId);
                    relationDO.setTodayTaskCount(1);
                    relationDO.setLastDate(new Date());
                } else {
                    relationDO.setTodayTaskCount(relationDO.getTodayTaskCount() + 1);
                }
                gameUserTaskRelationDao.save(relationDO);
            }
            //插入任务记录
            userRecordService.addGameRecord(new GameUserRecordRequest(userDO.getId(), userDO.getOpenId(), gameTaskDO.getId(), RecordType.TASK));
        }
    }

    @Override
    public Map<String, Object> checkUserTask(Integer userId, String taskKey) {
        Map<String, Object> result = Maps.newHashMap();
        GameUserDO userDO = gameUserDao.getOne(userId);
        GameTaskDO gameTaskDO = gameTaskDao.findByTaskConstantKey(taskKey);
        if (userDO != null && gameTaskDO != null) {
            if (gameTaskDO.getEnabled() != 1) {
                throw new RuntimeException("该任务已经结束啦!");
            }
            result.put("enabledTimesRule", gameTaskDO.getEnabledTimesRule());
            if (gameTaskDO.getEnabledTimesRule() == 1) {
                //当前任务次数
                GameUserTaskRelationDO relationDO = gameUserTaskRelationDao.findByUserIdAndAndTaskId(userId, gameTaskDO.getId());
                Integer doTaskCount = 0;
                if (relationDO != null) {
                    doTaskCount = relationDO.getTodayTaskCount();
                }
                List<GameTaskRuleDO> gameTaskRuleDOS = gameTaskDO.getRuleDOList();
                int index = 0, size = gameTaskRuleDOS.size();
                for (GameTaskRuleDO taskRuleDO : gameTaskRuleDOS) {
                    if (index == 0) {
                        if (doTaskCount <= taskRuleDO.getTimes()) {
                            result.put("taskType", taskRuleDO.getGameTaskType());
                            break;
                        }
                    } else {
                        if (doTaskCount >= gameTaskRuleDOS.get(index - 1).getTimes() && doTaskCount < taskRuleDO.getTimes()) {
                            result.put("taskType", taskRuleDO.getGameTaskType());
                            break;
                        }
                    }
                    index++;
                }
                result.put("doTaskCount", doTaskCount);
                result.computeIfAbsent("taskType", k -> gameTaskRuleDOS.get(size - 1).getGameTaskType());
            }
        }
        return result;
    }

    private Integer getRandom(Integer min, Integer max) {
        Random random = new Random();
        return random.nextInt(max) % (max - min + 1) + min;
    }

}
