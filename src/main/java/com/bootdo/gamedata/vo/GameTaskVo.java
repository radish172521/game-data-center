package com.bootdo.gamedata.vo;

import com.bootdo.common.gameenum.GameTaskType;
import com.bootdo.gamedata.domain.GameTaskDO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameTaskVo extends GameTaskDO {
    private Integer times1;
    private GameTaskType gameTaskType1;
    private Integer times2;
    private GameTaskType gameTaskType2;
    private Integer times3;
    private GameTaskType gameTaskType3;
    private Integer times4;
    private GameTaskType gameTaskType4;
    private Integer times5;
    private GameTaskType gameTaskType5;

}
