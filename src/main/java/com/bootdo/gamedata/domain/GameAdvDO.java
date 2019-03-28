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
    @Column(name = "disabled")
    private Boolean disabled;

}
