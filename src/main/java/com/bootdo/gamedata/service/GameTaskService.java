package com.bootdo.gamedata.service;

import com.bootdo.gamedata.domain.GameTaskDO;

import java.util.List;
import java.util.Map;

public interface GameTaskService {

    GameTaskDO get(Long id);

    List<GameTaskDO> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(GameTaskDO gameTaskDO);

    int update(GameTaskDO gameTaskDO);

    int remove(Long id);
}
