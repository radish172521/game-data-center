package com.bootdo.gamedata.dao;

import com.bootdo.gamedata.domain.GameUserAccountDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface GameUserAccountDao extends JpaRepository<GameUserAccountDO, Integer>,JpaSpecificationExecutor<GameUserAccountDO> {
}
