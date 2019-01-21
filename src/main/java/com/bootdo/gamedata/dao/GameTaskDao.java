package com.bootdo.gamedata.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface GameTaskDao {
    GameTaskDao get(Long userId);

    GameTaskDao getByOpenId(Long openId);

    List<GameTaskDao> list(Map<String, Object> map);

    int count(Map<String, Object> map);
}
