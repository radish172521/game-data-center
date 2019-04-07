package com.bootdo.gamedata.domain;

import com.bootdo.gamedata.dto.GameTaskInfoDto;
import com.bootdo.gamedata.enums.RecordType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 游戏用户任务记录
 */
@Getter
@Setter
@Entity
@Table(name = "gm_user_record")
public class GameUserRecordDO implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "record_type")
    @Enumerated(EnumType.STRING)
    private RecordType recordType;

    @Column(name = "game_user_id")
    private Integer gameUserId;

    @Column(name = "game_user_name")
    private String gameUserName;

    @Column(name = "open_id")
    private String openId;

    @Column(name = "business_id")
    private Integer businessId;

    @Column(name = "business_name")
    private String businessName;

    @Column(name = "data")
    private String data;

    @Column(name = "gmt_create")
    private Date gmtCreate;

    @Column(name = "gmt_modify")
    private Date gmtModify;

    @Transient
    private GameTaskInfoDto gameTaskInfoDto;


}
