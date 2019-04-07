package com.bootdo.gamedata.domain;

import com.bootdo.common.gameenum.GameTaskRewardType;
import com.bootdo.common.gameenum.GameTaskType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name="gm_task")
@Getter
@Setter
public class GameTaskDO implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "game_task_name")
    private String gameTaskName;

    /**
     * 游戏任务类型
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "game_task_type")
    private GameTaskType gameTaskType;

    /**
     * 游戏奖励类型
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "game_task_reward_type")
    private GameTaskRewardType gameTaskRewardType;

    /**
     * 奖励数量
     */
    @Column(name = "reward_count")
    private BigDecimal rewardCount;
    /**
     * 奖励倍数
     */
    @Column(name = "reward_multiple")
    private BigDecimal rewardMultiple;

    @Column(name = "gmt_create")
    private Date gmtCreate;

    @Column(name = "gmt_modify")
    private Date gmtModify;

    @Column(name = "enabled")
    private Integer enabled;

    /**
     * 该任务是否奖励抽奖次数
     */
    @Column(name = "reward_draw_times")
    private Integer rewardDrawTimes;

}
