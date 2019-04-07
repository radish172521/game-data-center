package com.bootdo.gamedata.domain;

import com.bootdo.common.gameenum.GameTaskType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "gm_task")
@Getter
@Setter
public class GameTaskDO implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    /**
     * 任务约定key 与前端对接使用 (唯一)
     */
    @Column(name = "task_constant_key")
    private String taskConstantKey;

    @Column(name = "game_task_name")
    private String gameTaskName;

    /**
     * 奖励抽奖次数
     */
    @Column(name = "reward_draw_times")
    private Integer rewardDrawTimes;

    /**
     * 奖励积分最低为多少
     */
    @Column(name = "reward_integral_min")
    private Integer rewardIntegralMin;
    /**
     * 奖励积分最多为多少
     */
    @Column(name = "reward_integral_max")
    private Integer rewardIntegralMax;

    /**
     * 是否开启次数规则
     */
    @Column(name = "enabled_times_rule")
    private Integer enabledTimesRule;

    /**
     * 游戏任务类型 (次数规则为否则选择此项)
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "game_task_type")
    private GameTaskType gameTaskType;

    @Column(name = "gmt_create")
    private Date gmtCreate;

    @Column(name = "gmt_modify")
    private Date gmtModify;

    @Column(name = "enabled")
    private Integer enabled;

    @OneToMany(mappedBy = "gameTaskDO", cascade = CascadeType.ALL)
    private List<GameTaskRuleDO> ruleDOList;



}
