package com.bootdo.gamedata.service;

import com.bootdo.gamedata.domain.GameAdvDO;
import com.bootdo.gamedata.qo.GameAdvQo;
import com.bootdo.gamedata.vo.GameAdvVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GameAdvService {

    void saveGameAdv(GameAdvDO gameAdvDO);

    GameAdvDO getById(Integer id);

    void deleteGameAdv(Integer advId);

    Page<GameAdvDO> findPage(GameAdvQo qo, Pageable pageable);

    /**
     * ==========================小游戏端相关接口==============================================
     */

    List<GameAdvVo> listAdv();

    void clickCount(String advId,String userId);
}
