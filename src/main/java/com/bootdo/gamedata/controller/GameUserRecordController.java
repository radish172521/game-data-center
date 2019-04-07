package com.bootdo.gamedata.controller;

import com.bootdo.common.annotation.Log;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.R;
import com.bootdo.gamedata.domain.GameUserRecordDO;
import com.bootdo.gamedata.qo.GameUserRecordQo;
import com.bootdo.gamedata.service.GameUserRecordService;
import com.bootdo.gamedata.vo.GameUserRecordRequest;
import com.bootdo.gamedata.vo.PageParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "game/user-record")
public class GameUserRecordController {
    private final static String prefix = "game/record";


    private final GameUserRecordService userRecordService;

    @Autowired
    public GameUserRecordController(GameUserRecordService userRecordService) {
        this.userRecordService = userRecordService;
    }

    @GetMapping("/draw-list")
    String drawList() {
        return prefix + "/draw-record-list";
    }

    @GetMapping("/task-list")
    String taskList() {
        return prefix + "/task-record-list";
    }

    @GetMapping("/exchange-list")
    String exchangeList() {
        return prefix + "/exchange-record-list";
    }

    @RequestMapping("/findPage")   //查询所有分类
    @ResponseBody
    public PageUtils<GameUserRecordDO> findPage(PageParams pageParams, GameUserRecordQo qo) {
        Sort sort = new Sort(Sort.Direction.DESC, "id"); //设置根据id倒序排列
        pageParams.setOffset(pageParams.getOffset());
        Pageable pageable = new PageRequest(pageParams.getOffset(), pageParams.getLimit(), sort); //根据start、size、sort创建分页对象
        Page<GameUserRecordDO> gameAdvDOS = userRecordService.findPage(qo, pageable);
        return (PageUtils<GameUserRecordDO>) new PageUtils(gameAdvDOS.getContent(), gameAdvDOS.getTotalElements());
    }

    @Log("添加游戏用户记录")
//    @RequiresPermissions("sys:role:add")
    @PostMapping("/addGameRecord")
    @ResponseBody
    public R addGameRecord(GameUserRecordRequest request) {
        userRecordService.addGameRecord(request);
        return R.ok();
    }


    @Log("删除游戏用户记录")
    @PostMapping("/remove")
    @ResponseBody
    public R remove(Integer id) {
        userRecordService.delete(id);
        return R.ok();
    }

    @Log("批量删除游戏用户记录")
    @PostMapping("/batchRemove")
    @ResponseBody
    public R batchRemove(@RequestParam("ids[]") Integer[] advIds) {
        for (Integer id : advIds) {
            userRecordService.delete(id);
        }
        return R.ok();
    }

}
