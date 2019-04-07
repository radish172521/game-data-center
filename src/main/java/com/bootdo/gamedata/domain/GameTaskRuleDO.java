package com.bootdo.gamedata.domain;

import com.bootdo.common.gameenum.GameTaskType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@Entity
@Table(name = "gm_task_rule")
public class GameTaskRuleDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @JsonIgnore
    @ManyToOne(cascade={CascadeType.ALL},optional=false)
    @JoinColumn(name = "task_id")
    private GameTaskDO gameTaskDO;
    @Column(name = "times")
    private Integer times;
    @Enumerated(EnumType.STRING)
    @Column(name = "game_task_type")
    private GameTaskType gameTaskType;
}


