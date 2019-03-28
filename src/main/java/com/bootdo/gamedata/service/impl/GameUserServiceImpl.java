package com.bootdo.gamedata.service.impl;

import com.bootdo.gamedata.dao.GameUserDao;
import com.bootdo.gamedata.service.GameUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameUserServiceImpl implements GameUserService {

    @Autowired
    private GameUserDao gameUserDao;

}
