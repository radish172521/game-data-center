package com.bootdo.gamedata.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * 游戏广告位
 */
@Entity
@Table(name = "gm_adv")
@Getter
@Setter
public class GameAdvDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "adv_channel_id")
    private String advChannelId;
    @Column(name = "adv_name")
    private String advName;
    @Column(name = "pic_url")
    private String picUrl;
    @Column(name = "sort")
    private Integer sort;
    @Column(name = "gmt_create")
    private Date gmtCreate;
    @Column(name = "gmt_modify")
    private Date gmtModify;
    @Column(name = "enabled")
    private Integer enabled;
    /**
     * 点击量
     */
    @Column(name = "click_count")
    private Integer clickCount;

    /**
     * 授权量
     */
    @Column(name = "accredit_count")
    private Integer accreditCount;

    /**
     * 今天Uv
     */
    @Transient
    private Integer uvCount;

    /**
     * 昨天uv
     */
    @Transient
    private Integer uvCountYest;
}
