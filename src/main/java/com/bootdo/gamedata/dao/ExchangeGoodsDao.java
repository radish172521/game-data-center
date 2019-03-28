package com.bootdo.gamedata.dao;

import com.bootdo.gamedata.domain.ExchangeGoodsDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ExchangeGoodsDao extends JpaRepository<ExchangeGoodsDO, Integer> ,JpaSpecificationExecutor<ExchangeGoodsDO> {

}
