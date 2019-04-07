package com.bootdo.gamedata.controller;

import com.bootdo.common.annotation.Log;
import com.bootdo.common.config.BootdoConfig;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.R;
import com.bootdo.gamedata.domain.GameUserDO;
import com.bootdo.gamedata.qo.GameTaskQo;
import com.bootdo.gamedata.qo.GameUserQo;
import com.bootdo.gamedata.service.GameUserAccountService;
import com.bootdo.gamedata.service.GameUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "game/user")
public class GameUserController {

    private final static String prefix = "game/task";

    private final BootdoConfig bootdoConfig;

    private final GameUserService gameUserService;

    @Autowired
    public GameUserController(GameUserService gameUserService, BootdoConfig bootdoConfig) {
        this.gameUserService = gameUserService;
        this.bootdoConfig = bootdoConfig;
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


    @Log("保存转盘奖品")
//    @RequiresPermissions("sys:role:add")
    @PostMapping("/saveTask")
    @ResponseBody
    public R saveTask(GameUserDO GameUserDO) {
        gameUserService.saveGameUser(GameUserDO);
        return R.ok();
    }


    @Log("删除转盘奖品")
    @PostMapping("/remove")
    @ResponseBody
    public R remove(Integer id) {
        gameUserService.deleteGameUser(id);
        return R.ok();
    }

    @Log("批量删除转盘奖品")
    @PostMapping("/batchRemove")
    @ResponseBody
    public R batchRemove(@RequestParam("ids[]") Integer[] advIds) {
        for (Integer id : advIds) {
            gameUserService.deleteGameUser(id);
        }
        return R.ok();
    }

}
