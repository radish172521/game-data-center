package com.bootdo.gamedata.vo;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class GoodsRequest {
    private Integer id;
    private String goodsName;
    private BigDecimal goodsPrice;
    private String goodsPictureUrl;
    private Boolean goodsDisabled;
}
