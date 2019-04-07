package com.bootdo.gamedata.controller;

import com.bootdo.common.utils.PageUtils;
import com.bootdo.gamedata.domain.GameUserDO;
import com.bootdo.gamedata.qo.GameUserQo;
import com.bootdo.gamedata.service.GameUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "game/user")
public class GameUserController {

    private final static String prefix = "game/user";

    private final GameUserService gameUserService;

    @Autowired
    public GameUserController(GameUserService gameUserService) {
        this.gameUserService = gameUserService;
    }

    @GetMapping("/list")
    String list() {
        return prefix + "/user-list";
    }


    @RequestMapping("/findPage")   //查询所有分类
    @ResponseBody
    public PageUtils<GameUserDO> findPage(com.bootdo.gamedata.vo.PageParams pageParams, GameUserQo qo) {
        Sort sort = new Sort(Sort.Direction.DESC, "id"); //设置根据id倒序排列
        pageParams.setOffset(pageParams.getOffset());
        Pageable pageable = new PageRequest(pageParams.getOffset(), pageParams.getLimit(), sort); //根据start、size、sort创建分页对象
        Page<GameUserDO> gameAdvDOS = gameUserService.findPage(qo, pageable);
        return (PageUtils<GameUserDO>) new PageUtils(gameAdvDOS.getContent(), gameAdvDOS.getTotalElements());
    }

}
