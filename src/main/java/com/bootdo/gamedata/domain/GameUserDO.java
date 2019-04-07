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
@Table(name = "gm_user")
public class GameUserDO implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer id;

    @Column(name = "openId")
    private String openId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "gender")
    private Integer gender;

    @Column(name = "avatar_url")
    private String avatarUrl;

    @Column(name = "gmt_create")
    private Date gmtCreate;

    @Column(name = "gmt_modify")
    private Date gmtModify;

    @Transient
    private GameUserAccountDO gameUserAccountDO;

}
