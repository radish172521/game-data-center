package com.bootdo.gamedata.dao;

import com.bootdo.gamedata.domain.GameAdvDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface GameAdvDao extends JpaRepository<GameAdvDO, Integer>,JpaSpecificationExecutor<GameAdvDO> {
}
