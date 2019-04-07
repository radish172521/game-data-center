package com.bootdo.gamedata.controller;

import com.bootdo.common.annotation.Log;
import com.bootdo.common.config.BootdoConfig;
import com.bootdo.common.utils.FileUtil;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.R;
import com.bootdo.common.utils.StringUtils;
import com.bootdo.gamedata.domain.ExchangeGoodsDO;
import com.bootdo.gamedata.qo.ExchangeGoodsQo;
import com.bootdo.gamedata.service.ExchangeGoodsService;
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
@RequestMapping("game/exchangeGoods")
public class ExchangeGoodsController {
    private final BootdoConfig bootdoConfig;

    private final static String prefix = "game/exchangeGoods";

    private final ExchangeGoodsService exchangeGoodsService;

    @GetMapping("/list")
    String list() {
        return prefix + "/eg-list";
    }

    @GetMapping("/add")
    String add() {
        return prefix + "/eg-add";
    }

    @GetMapping("/edit/{id}")
    String edit(Model model, @PathVariable("id") Integer id) {
        model.addAttribute("goods", exchangeGoodsService.getById(id));
        return prefix + "/eg-edit";
    }

    @Autowired
    public ExchangeGoodsController(ExchangeGoodsService exchangeGoodsService, BootdoConfig bootdoConfig) {
        this.exchangeGoodsService = exchangeGoodsService;
        this.bootdoConfig = bootdoConfig;
    }

    @RequestMapping("/findPage")   //查询所有分类
    @ResponseBody
    public PageUtils<ExchangeGoodsDO> findPage(PageParams pageParams, ExchangeGoodsQo qo) {
        Sort sort = new Sort(Sort.Direction.DESC, "id"); //设置根据id倒序排列
        pageParams.setOffset(pageParams.getOffset());
        Pageable pageable = new PageRequest(pageParams.getOffset(), pageParams.getLimit(), sort); //根据start、size、sort创建分页对象
        Page<ExchangeGoodsDO> exchangeGoodsDOS = exchangeGoodsService.findPage(qo, pageable);
        return (PageUtils<ExchangeGoodsDO>) new PageUtils(exchangeGoodsDOS.getContent(), exchangeGoodsDOS.getTotalElements());
    }


    @Log("保存K金商品")
//    @RequiresPermissions("sys:role:add")
    @PostMapping("/saveGoods")
    @ResponseBody
    public R saveGoods(GoodsRequest goodsRequest, MultipartFile picFile) {
        if (picFile != null && StringUtils.isNotBlank(picFile.getOriginalFilename())) {
            String fileName = picFile.getOriginalFilename();
            fileName = FileUtil.renameToUUID(fileName);
            try {
                FileUtil.uploadFile(picFile.getBytes(), bootdoConfig.getUploadPath(), fileName);
                goodsRequest.setGoodsPictureUrl("/files/" + fileName);
            } catch (Exception e) {
                return R.error();
            }
        }
        exchangeGoodsService.saveGoods(goodsRequest);
        return R.ok();
    }


    @Log("删除K金商品")
    @PostMapping("/remove")
    @ResponseBody
    public R remove(Integer id) {
        exchangeGoodsService.deleteGoods(id);
        return R.ok();
    }

    @Log("批量删除K金商品")
    @PostMapping("/batchRemove")
    @ResponseBody
    public R batchRemove(@RequestParam("ids[]") Integer[] goodsIds) {
        exchangeGoodsService.batchDelGoods(goodsIds);
        return R.ok();
    }


}
