package com.bootdo.gamedata.domain;

import com.bootdo.common.gameenum.GameTaskRewardType;
import com.bootdo.common.gameenum.GameTaskType;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 用户任务记录
 */
public class UserTaskRecordDO implements Serializable {

    private Long recordId;

    private Long userId;

    private Long gameTaskId;

    /**
     * 游戏任务类型
     */
    private GameTaskType gameTaskType;

    /**
     * 游戏奖励类型
     */
    private GameTaskRewardType gameTaskRewardType;

    /**
     * 奖励数量
     */
    private BigDecimal rewardCount;
    /**
     * 奖励倍数
     */
    private Integer rewardMultiple;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date gmtCreate;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date gmtModified;
}
