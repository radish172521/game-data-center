package com.bootdo.gamedata.service;

import com.bootdo.gamedata.domain.GameUserAccountDO;
import com.bootdo.gamedata.vo.GameUserAccountVo;

public interface GameUserAccountService {

    /***
     * 获取账户信息
     * @param userId
     * @return
     */
    GameUserAccountDO getById(Integer userId);


    /**
     * ==================================对外接口===================================
     */
    /**
     * 添加抽奖次数
     * @param userId
     */
    void addDrawCount(Integer userId);

    /**
     * 消耗抽奖次数
     * @param userId
     */
    void consumeDrawCount(Integer userId);

    /**
     * 获取账户视图
     * @param userId
     * @return
     */
    GameUserAccountVo getVoById(Integer userId);
}
