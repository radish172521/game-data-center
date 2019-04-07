package com.bootdo.gamedata.controller;

import com.bootdo.common.annotation.Log;
import com.bootdo.common.config.BootdoConfig;
import com.bootdo.common.utils.FileUtil;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.R;
import com.bootdo.common.utils.StringUtils;
import com.bootdo.gamedata.domain.GameDrawDO;
import com.bootdo.gamedata.qo.GameDrawQo;
import com.bootdo.gamedata.service.GameDrawService;
import com.bootdo.gamedata.vo.PageParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping(value = "game/draw")
public class GameDrawController {

    private final static String prefix = "game/draw";

    private final BootdoConfig bootdoConfig;

    private final GameDrawService gameDrawService;

    @Autowired
    public GameDrawController(GameDrawService gameDrawService, BootdoConfig bootdoConfig) {
        this.gameDrawService = gameDrawService;
        this.bootdoConfig = bootdoConfig;
    }

    @GetMapping("/list")
    String list() {
        return prefix + "/draw-list";
    }

    @GetMapping("/add")
    String add() {
        return prefix + "/draw-add";
    }

    @GetMapping("/edit/{id}")
    String edit(Model model, @PathVariable("id") Integer id) {
        model.addAttribute("draw", gameDrawService.getById(id));
        return prefix + "/draw-edit";
    }

    @RequestMapping("/findPage")   //查询所有分类
    @ResponseBody
    public PageUtils<GameDrawDO> findPage(PageParams pageParams, GameDrawQo qo) {
        Sort sort = new Sort(Sort.Direction.DESC, "id"); //设置根据id倒序排列
        pageParams.setOffset(pageParams.getOffset());
        Pageable pageable = new PageRequest(pageParams.getOffset(), pageParams.getLimit(), sort); //根据start、size、sort创建分页对象
        Page<GameDrawDO> gameAdvDOS = gameDrawService.findPage(qo, pageable);
        return (PageUtils<GameDrawDO>) new PageUtils(gameAdvDOS.getContent(), gameAdvDOS.getTotalElements());
    }


    @Log("保存转盘奖品")
//    @RequiresPermissions("sys:role:add")
    @PostMapping("/saveDraw")
    @ResponseBody
    public R saveDraw(GameDrawDO gameDrawDO, MultipartFile picFile) {
        if (picFile != null && StringUtils.isNotBlank(picFile.getOriginalFilename())) {
            String fileName = picFile.getOriginalFilename();
            fileName = FileUtil.renameToUUID(fileName);
            try {
                FileUtil.uploadFile(picFile.getBytes(), bootdoConfig.getUploadPath(), fileName);
                gameDrawDO.setPicUrl("/files/" + fileName);
            } catch (Exception e) {
                return R.error();
            }
        }
        gameDrawService.saveGameDraw(gameDrawDO);
        return R.ok();
    }


    @Log("删除转盘奖品")
    @PostMapping("/remove")
    @ResponseBody
    public R remove(Integer id) {
        gameDrawService.deleteGameDraw(id);
        return R.ok();
    }

    @Log("批量删除转盘奖品")
    @PostMapping("/batchRemove")
    @ResponseBody
    public R batchRemove(@RequestParam("ids[]") Integer[] advIds) {
        for (Integer id : advIds) {
            gameDrawService.deleteGameDraw(id);
        }
        return R.ok();
    }
}
