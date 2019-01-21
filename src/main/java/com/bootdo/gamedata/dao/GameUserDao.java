package com.bootdo.gamedata.dao;

import com.bootdo.gamedata.domain.GameUserDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface GameUserDao {

    GameUserDO get(Long userId);

    GameUserDO getByOpenId(Long openId);

    List<GameUserDO> list(Map<String, Object> map);

    int count(Map<String, Object> map);
}
