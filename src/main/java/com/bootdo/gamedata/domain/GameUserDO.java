package com.bootdo.gamedata.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class GameUserDO implements Serializable {

    private Long userId;

    private String openId;

    private String userName;

    private Integer gender;

    private String avatarUrl;

    /**
     * 游戏用户宝石数量
     */
    private BigDecimal diamondCount;

    /**
     * 游戏用户金币s
     */
    private BigDecimal coinCount;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date gmtCreate;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date gmtModified;

}
