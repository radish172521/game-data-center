package com.bootdo.gamedata.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 转盘奖品设置
 */
@Entity
@Table(name="gm_draw")
@Getter
@Setter
public class GameDrawDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "pic_url")
    private String picUrl;
    @Column(name = "draw_name")
    private String drawName;
    @Column(name = "enabled")
    private Integer enabled;
    @Column(name = "gmt_create")
    private Date gmtCreate;
    @Column(name = "gmt_modify")
    private Date gmtModify;
    @Column(name = "sort")
    private Integer sort;
    @Column(name = "draw_ratio")
    private BigDecimal drawRatio;

}
