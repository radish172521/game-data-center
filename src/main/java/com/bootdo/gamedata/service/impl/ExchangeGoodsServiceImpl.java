package com.bootdo.gamedata.service.impl;

import com.bootdo.gamedata.dao.ExchangeGoodsDao;
import com.bootdo.gamedata.domain.ExchangeGoodsDO;
import com.bootdo.gamedata.qo.ExchangeGoodsQo;
import com.bootdo.gamedata.service.ExchangeGoodsService;
import com.bootdo.gamedata.vo.GoodsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.Date;
import java.util.List;

@Service
public class ExchangeGoodsServiceImpl implements ExchangeGoodsService {

    private final ExchangeGoodsDao exchangeGoodsDao;

    @Autowired
    public ExchangeGoodsServiceImpl(ExchangeGoodsDao exchangeGoodsDao) {
        this.exchangeGoodsDao = exchangeGoodsDao;
    }

    @Override
    public void saveGoods(GoodsRequest goodsRequest) {
        ExchangeGoodsDO exchangeGoodsDO;
        if(goodsRequest.getId() != null){
            exchangeGoodsDO = exchangeGoodsDao.getOne(goodsRequest.getId());
        }else{
            exchangeGoodsDO = new ExchangeGoodsDO();
            exchangeGoodsDO.setGmtCreate(new Date());
        }
        exchangeGoodsDO.setGoodsName(goodsRequest.getGoodsName());
        exchangeGoodsDO.setGoodsDisable(goodsRequest.getGoodsDisabled());
        exchangeGoodsDO.setGoodsPictureUrl(goodsRequest.getGoodsPictureUrl());
        exchangeGoodsDO.setGoodsPrice(goodsRequest.getGoodsPrice());
        exchangeGoodsDao.save(exchangeGoodsDO);

    }

    @Override
    public void doExchangeGoods(Integer userId, Integer goodsId) {

    }

    @Override
    public void deleteGoods(Integer goodsId) {
        if(goodsId != null){
            exchangeGoodsDao.deleteById(goodsId);
        }
    }

    @Override
    public Page<ExchangeGoodsDO> findPage(ExchangeGoodsQo qo, Pageable pageable) {
        return exchangeGoodsDao.findAll((Specification<ExchangeGoodsDO>) (root, query, criteriaBuilder) -> {
            Predicate p1 = criteriaBuilder.equal(root.get("goodsName").as(String.class), qo.getGoodsName());
            query.where(criteriaBuilder.and(p1));
            return query.getRestriction();
        }, pageable);
    }

    @Override
    public List<ExchangeGoodsDO> findList() {
        return null;
    }
}
