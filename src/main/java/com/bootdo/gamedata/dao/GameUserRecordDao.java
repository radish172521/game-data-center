package com.bootdo.gamedata.dao;

import com.bootdo.gamedata.domain.GameUserRecordDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface GameUserRecordDao extends JpaRepository<GameUserRecordDO, Integer>,JpaSpecificationExecutor<GameUserRecordDO> {
}
