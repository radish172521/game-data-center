package com.bootdo.gamedata.dao;

import com.bootdo.gamedata.domain.GameUserDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface GameUserDao extends JpaRepository<GameUserDO, Integer>,JpaSpecificationExecutor<GameUserDO> {

    GameUserDO findByOpenId(String openId);
}
