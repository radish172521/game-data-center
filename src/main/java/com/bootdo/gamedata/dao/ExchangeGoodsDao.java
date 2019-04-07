package com.bootdo.gamedata.dao;

import com.bootdo.gamedata.domain.ExchangeGoodsDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ExchangeGoodsDao extends JpaRepository<ExchangeGoodsDO, Integer>, JpaSpecificationExecutor<ExchangeGoodsDO> {

    List<ExchangeGoodsDO> findByEnabledOrderBySortAsc(Integer enabled);

}
