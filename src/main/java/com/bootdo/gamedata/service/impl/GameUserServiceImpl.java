package com.bootdo.gamedata.service.impl;

import com.bootdo.gamedata.dao.GameUserDao;
import com.bootdo.gamedata.domain.GameUserDO;
import com.bootdo.gamedata.service.GameUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class GameUserServiceImpl implements GameUserService {

    @Autowired
    private GameUserDao gameUserDao;

    @Override
    public GameUserDO get(Long id) {
        return null;
    }

    @Override
    public List<GameUserDO> list(Map<String, Object> map) {
        return null;
    }

    @Override
    public int count(Map<String, Object> map) {
        return 0;
    }

    @Override
    public int save(GameUserDO gameUserDO) {
        return 0;
    }

    @Override
    public int update(GameUserDO gameUserDO) {
        return 0;
    }

    @Override
    public int remove(Long id) {
        return 0;
    }
}
