package com.bootdo.gamedata.dao;

import com.bootdo.gamedata.domain.GameDrawDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface GameDrawDao extends JpaRepository<GameDrawDO, Integer>,JpaSpecificationExecutor<GameDrawDO> {
}
