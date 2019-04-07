package com.bootdo.gamedata.dao;

import com.bootdo.gamedata.domain.GameAdvInfoDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;

public interface GameAdvInfoDao extends JpaRepository<GameAdvInfoDO, Integer>, JpaSpecificationExecutor<GameAdvInfoDO> {

    @Query(" from GameAdvInfoDO where advId=:advId and gmtCreate >= :startDate and gmtCreate <:endDate")
    GameAdvInfoDO findByAdvIdAndGmtCreateBetween(@Param("advId") Integer advId, @Param("startDate") Date startDate, @Param("endDate") Date endDate);

}
