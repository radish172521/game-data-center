package com.bootdo.gamedata.dao;

import com.bootdo.gamedata.domain.GameUserTaskRelationDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface GameUserTaskRelationDao extends JpaRepository<GameUserTaskRelationDO, Integer>, JpaSpecificationExecutor<GameUserTaskRelationDO> {

    GameUserTaskRelationDO findByUserIdAndAndTaskId(Integer userId, Integer taskId);
}
