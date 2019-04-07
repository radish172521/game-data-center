package com.bootdo.gamedata.dto;

import com.bootdo.common.gameenum.GameTaskRewardType;
import com.bootdo.common.gameenum.GameTaskType;
import com.bootdo.gamedata.domain.GameTaskDO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class GameTaskInfoDto {
    /**
     * 游戏任务类型
     */
    private GameTaskType gameTaskType;

    /**
     * 游戏奖励类型
     */
    private GameTaskRewardType gameTaskRewardType;

    /**
     * 奖励数量
     */
    private BigDecimal rewardCount;

    public GameTaskInfoDto(GameTaskDO gameTaskDO){
        this.gameTaskType = gameTaskDO.getGameTaskType();
        this.gameTaskRewardType = gameTaskDO.getGameTaskRewardType();
        this.rewardCount = gameTaskDO.getRewardCount();
    }
}
