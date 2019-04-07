package com.bootdo.gamedata.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "gm_adv_info")
@Getter
@Setter
public class GameAdvInfoDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "adv_id")
    private Integer advId;
    @Column(name = "uv_count")
    private Integer uvCount;
    @Column(name = "gmt_create")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd")
    private Date gmtCreate;
    @Column(name = "gmt_modify")
    private Date gmtModify;
}
