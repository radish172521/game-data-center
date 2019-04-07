package com.bootdo.gamedata.controller;

import com.bootdo.common.annotation.Log;
import com.bootdo.common.config.BootdoConfig;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.R;
import com.bootdo.gamedata.domain.GameTaskDO;
import com.bootdo.gamedata.qo.GameTaskQo;
import com.bootdo.gamedata.service.GameTaskService;
import com.bootdo.gamedata.vo.PageParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "game/task")
public class GameTaskController {
    private final static String prefix = "game/task";

    private final GameTaskService gameTaskService;

    @Autowired
    public GameTaskController(GameTaskService gameTaskService) {
        this.gameTaskService = gameTaskService;
    }

    @GetMapping("/list")
    String list() {
        return prefix + "/task-list";
    }

    @GetMapping("/add")
    String add() {
        return prefix + "/task-add";
    }

    @GetMapping("/edit/{id}")
    String edit(Model model, @PathVariable("id") Integer id) {
        model.addAttribute("task", gameTaskService.getById(id));
        return prefix + "/task-edit";
    }

    @RequestMapping("/findPage")   //查询所有分类
    @ResponseBody
    public PageUtils<GameTaskDO> findPage(PageParams pageParams, GameTaskQo qo) {
        Sort sort = new Sort(Sort.Direction.DESC, "id"); //设置根据id倒序排列
        pageParams.setOffset(pageParams.getOffset());
        Pageable pageable = new PageRequest(pageParams.getOffset(), pageParams.getLimit(), sort); //根据start、size、sort创建分页对象
        Page<GameTaskDO> gameAdvDOS = gameTaskService.findPage(qo, pageable);
        return (PageUtils<GameTaskDO>) new PageUtils(gameAdvDOS.getContent(), gameAdvDOS.getTotalElements());
    }


    @Log("保存转盘奖品")
//    @RequiresPermissions("sys:role:add")
    @PostMapping("/saveTask")
    @ResponseBody
    public R saveTask(GameTaskDO gameTaskDO) {
        gameTaskService.saveGameTask(gameTaskDO);
        return R.ok();
    }


    @Log("删除转盘奖品")
    @PostMapping("/remove")
    @ResponseBody
    public R remove(Integer id) {
        gameTaskService.deleteGameTask(id);
        return R.ok();
    }

    @Log("批量删除转盘奖品")
    @PostMapping("/batchRemove")
    @ResponseBody
    public R batchRemove(@RequestParam("ids[]") Integer[] advIds) {
        for (Integer id : advIds) {
            gameTaskService.deleteGameTask(id);
        }
        return R.ok();
    }
}
