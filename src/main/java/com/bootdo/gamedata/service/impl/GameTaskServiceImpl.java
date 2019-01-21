package com.bootdo.gamedata.service.impl;

import com.bootdo.gamedata.dao.GameTaskDao;
import com.bootdo.gamedata.domain.GameTaskDO;
import com.bootdo.gamedata.service.GameTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class GameTaskServiceImpl implements GameTaskService {
    @Autowired
    private GameTaskDao gameTaskDao;

    @Override
    public GameTaskDO get(Long id) {
        return null;
    }

    @Override
    public List<GameTaskDO> list(Map<String, Object> map) {
        return null;
    }

    @Override
    public int count(Map<String, Object> map) {
        return 0;
    }

    @Override
    public int save(GameTaskDO gameTaskDO) {
        return 0;
    }

    @Override
    public int update(GameTaskDO gameTaskDO) {
        return 0;
    }

    @Override
    public int remove(Long id) {
        return 0;
    }
}
