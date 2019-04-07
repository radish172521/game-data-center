package com.bootdo.gamedata.dao;

import com.bootdo.gamedata.domain.GameAdvDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface GameAdvDao extends JpaRepository<GameAdvDO, Integer>,JpaSpecificationExecutor<GameAdvDO> {

    List<GameAdvDO> findByEnabledOrderBySortAsc(Integer enabled);
}
