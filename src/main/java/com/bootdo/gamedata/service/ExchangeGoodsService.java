package com.bootdo.gamedata.service;

import com.bootdo.gamedata.domain.ExchangeGoodsDO;
import com.bootdo.gamedata.qo.ExchangeGoodsQo;
import com.bootdo.gamedata.vo.GoodsRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ExchangeGoodsService {

    /**
     * 新增/保存商品
     */
    void saveGoods(GoodsRequest goodsRequest);

    /**
     * 兑换商品接口
     *
     * @param userId
     */
    void doExchangeGoods(Integer userId, Integer goodsId);

    /**
     * 删除商品
     *
     * @param goodsId
     */
    void deleteGoods(Integer goodsId);

    /**
     * 查询分页
     *
     * @param qo
     * @param pageable
     * @return
     */
    Page<ExchangeGoodsDO> findPage(ExchangeGoodsQo qo, Pageable pageable);

    /**
     * 小游戏可兑换的商品
     */
    List<ExchangeGoodsDO> findList();
}
