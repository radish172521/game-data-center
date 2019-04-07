package com.bootdo.gamedata.service;

import com.bootdo.gamedata.domain.GameTaskDO;
import com.bootdo.gamedata.qo.GameTaskQo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GameTaskService {

    void saveGameTask(GameTaskDO gameTaskDO);

    GameTaskDO getById(Integer id);

    void deleteGameTask(Integer taskId);

    Page<GameTaskDO> findPage(GameTaskQo qo, Pageable pageable);

    /**
     * ===============================小游戏端接口====================================================
     */
    void doTask(Integer userId, Integer taskId);
}
