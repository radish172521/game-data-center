package com.bootdo.gamedata.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * K金商城
 */
@Entity
@Table(name="gm_exchange_goods")
@Getter
@Setter
public class ExchangeGoodsDO {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;
    @Column(name = "goods_name")
    private String goodsName;
    @Column(name = "goods_price")
    private BigDecimal goodsPrice;
    @Column(name = "goods_picture_url")
    private String goodsPictureUrl;
    @Column(name = "gmt_create")
    private Date gmtCreate;
    @Column(name = "gmt_modify")
    private Date gmtModify;
    @Column(name = "enabled")
    private Integer enabled;
    /**
     * 排序
     */
    @Column(name = "sort")
    private Integer sort;

}
