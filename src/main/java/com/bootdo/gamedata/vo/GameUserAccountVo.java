package com.bootdo.gamedata.vo;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class GameUserAccountVo {
    private Integer userId;

    /**
     * 游戏用户宝石数量
     */
    private BigDecimal diamondCount;

    /**
     * 游戏用户金币
     */
    private BigDecimal coinCount;
    /**
     * 用户最高分数
     */
    private BigDecimal score;

    /**
     * 游戏道具数统计
     */
    private Integer toolsCount1;

    private Integer toolsCount2;

    private Integer toolsCount3;

    private Integer toolsCount4;

    /**
     * 抽奖次数
     */
    private Integer drawCount;
}
