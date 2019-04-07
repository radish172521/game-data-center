package com.bootdo.gamedata.service;

import com.bootdo.gamedata.domain.GameDrawDO;
import com.bootdo.gamedata.qo.GameDrawQo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GameDrawService {

    void saveGameDraw(GameDrawDO gameDrawDO);

    GameDrawDO getById(Integer id);

    void deleteGameDraw(Integer DrawId);

    Page<GameDrawDO> findPage(GameDrawQo qo, Pageable pageable);

    List<GameDrawDO> listDraw();
}
