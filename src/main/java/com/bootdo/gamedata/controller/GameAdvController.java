package com.bootdo.gamedata.controller;

import com.bootdo.common.annotation.Log;
import com.bootdo.common.config.BootdoConfig;
import com.bootdo.common.utils.FileUtil;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.R;
import com.bootdo.common.utils.StringUtils;
import com.bootdo.gamedata.domain.ExchangeGoodsDO;
import com.bootdo.gamedata.domain.GameAdvDO;
import com.bootdo.gamedata.qo.ExchangeGoodsQo;
import com.bootdo.gamedata.qo.GameAdvQo;
import com.bootdo.gamedata.service.GameAdvService;
import com.bootdo.gamedata.vo.GameAdvRequest;
import com.bootdo.gamedata.vo.GoodsRequest;
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
@RequestMapping(value = "game/adv")
public class GameAdvController {

    private final static String prefix = "game/adv";

    private final BootdoConfig bootdoConfig;

    private final GameAdvService gameAdvService;

    @Autowired
    public GameAdvController(GameAdvService gameAdvService, BootdoConfig bootdoConfig) {
        this.gameAdvService = gameAdvService;
        this.bootdoConfig = bootdoConfig;
    }

    @GetMapping("/list")
    String list() {
        return prefix + "/adv-list";
    }

    @GetMapping("/add")
    String add() {
        return prefix + "/adv-add";
    }

    @GetMapping("/edit/{id}")
    String edit(Model model, @PathVariable("id") Integer id) {
        model.addAttribute("adv", gameAdvService.getById(id));
        return prefix + "/adv-edit";
    }

    @RequestMapping("/findPage")   //查询所有分类
    @ResponseBody
    public PageUtils<GameAdvDO> findPage(PageParams pageParams, GameAdvQo qo) {
        Sort sort = new Sort(Sort.Direction.DESC, "id"); //设置根据id倒序排列
        pageParams.setOffset(pageParams.getOffset());
        Pageable pageable = new PageRequest(pageParams.getOffset(), pageParams.getLimit(), sort); //根据start、size、sort创建分页对象
        Page<GameAdvDO> gameAdvDOS = gameAdvService.findPage(qo, pageable);
        return (PageUtils<GameAdvDO>) new PageUtils(gameAdvDOS.getContent(), gameAdvDOS.getTotalElements());
    }


    @Log("保存游戏推荐位广告")
//    @RequiresPermissions("sys:role:add")
    @PostMapping("/saveAdv")
    @ResponseBody
    public R saveAdv(GameAdvDO gameAdvRequest, MultipartFile picFile) {
        if (picFile != null && StringUtils.isNotBlank(picFile.getOriginalFilename())) {
            String fileName = picFile.getOriginalFilename();
            fileName = FileUtil.renameToUUID(fileName);
            try {
                FileUtil.uploadFile(picFile.getBytes(), bootdoConfig.getUploadPath(), fileName);
                gameAdvRequest.setPicUrl("/files/" + fileName);
            } catch (Exception e) {
                return R.error();
            }
        }
        gameAdvService.saveGameAdv(gameAdvRequest);
        return R.ok();
    }


    @Log("删除游戏广告位")
    @PostMapping("/remove")
    @ResponseBody
    public R remove(Integer id) {
        gameAdvService.deleteGameAdv(id);
        return R.ok();
    }

    @Log("批量删除游戏广告位")
    @PostMapping("/batchRemove")
    @ResponseBody
    public R batchRemove(@RequestParam("ids[]") Integer[] advIds) {
        for (Integer id : advIds) {
            gameAdvService.deleteGameAdv(id);
        }
        return R.ok();
    }
}
