package com.bootdo.gamedata.dao;

import com.bootdo.gamedata.domain.GameTaskDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface GameTaskDao extends JpaRepository<GameTaskDO, Integer>, JpaSpecificationExecutor<GameTaskDO> {

    GameTaskDO findByTaskConstantKey(String key);
}
