package com.bootdo.gamedata.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "gm_user_task_relation")
public class GameUserTaskRelationDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "task_id")
    private Integer taskId;
    @Column(name = "today_task_count")
    private Integer todayTaskCount;
    @Column(name = "last_date")
    private Date lastDate;
}
