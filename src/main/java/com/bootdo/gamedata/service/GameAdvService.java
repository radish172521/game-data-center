package com.bootdo.gamedata.service;

import com.bootdo.gamedata.domain.GameAdvDO;
import com.bootdo.gamedata.qo.GameAdvQo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GameAdvService {

    void saveGameAdv(GameAdvDO gameAdvDO);

    void deleteGameAdv(Integer advId);

    Page<GameAdvDO> findPage(GameAdvQo qo, Pageable pageable);

    List<GameAdvDO> listAdv();

}
