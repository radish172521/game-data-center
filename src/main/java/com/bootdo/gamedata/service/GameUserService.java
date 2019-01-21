package com.bootdo.gamedata.service;

import com.bootdo.gamedata.domain.GameUserDO;

import java.util.List;
import java.util.Map;

public interface GameUserService {

    GameUserDO get(Long id);

    List<GameUserDO> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(GameUserDO gameUserDO);

    int update(GameUserDO gameUserDO);

    int remove(Long id);
}
