package com.bootdo.gamedata.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameAdvRequest {
    private Integer id;
    private String advName;
    private String picUrl;
    private Integer sort;
    private Boolean disabled;

}
