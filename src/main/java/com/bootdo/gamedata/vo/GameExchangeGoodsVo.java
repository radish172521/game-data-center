package com.bootdo.gamedata.vo;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class GameExchangeGoodsVo {
    private String goodsName;
    private BigDecimal goodsPrice;
    private String goodsPictureUrl;
    private Integer sort;
}
