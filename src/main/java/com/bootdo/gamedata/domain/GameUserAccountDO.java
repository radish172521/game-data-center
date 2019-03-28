package com.bootdo.gamedata.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "gm_user_account")
public class GameUserAccountDO implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 游戏用户宝石数量
     */
    @Column(name = "diamond_count")
    private BigDecimal diamondCount;

    /**
     * 游戏用户金币
     */
    @Column(name = "coin_count")
    private BigDecimal coinCount;
    /**
     * 用户最高分数
     */
    @Column(name = "score")
    private BigDecimal score;

    /**
     * 游戏道具数统计
     */
    @Column(name = "tools_count1")
    private Integer toolsCount1;

    @Column(name = "tools_count2")
    private Integer toolsCount2;

    @Column(name = "tools_count3")
    private Integer toolsCount3;

    @Column(name = "tools_count4")
    private Integer toolsCount4;

    /**
     * 抽奖次数
     */
    @Column(name = "draw_count")
    private Integer drawCount;

    @Column(name = "gmt_create")
    private Date gmtCreate;

    @Column(name = "gmt_modify")
    private Date gmtModify;

}
