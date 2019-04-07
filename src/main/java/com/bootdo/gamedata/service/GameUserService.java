package com.bootdo.gamedata.service;

import com.bootdo.gamedata.domain.GameUserDO;
import com.bootdo.gamedata.qo.GameUserQo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GameUserService {

    GameUserDO getById(Integer id);

    void deleteGameUser(Integer userId);

    Page<GameUserDO> findPage(GameUserQo qo, Pageable pageable);

    /**
     * ==============================小游戏端接口==========================================
     */

    void addGameUser(GameUserDO gameUserDO);

}
