package com.bootdo.gamedata.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageParams {

    private Integer limit;
    private Integer offset;
    private String order;
}
