package com.bootdo.gamedata.controller;

import com.bootdo.gamedata.domain.ExchangeGoodsDO;
import com.bootdo.gamedata.qo.ExchangeGoodsQo;
import com.bootdo.gamedata.service.ExchangeGoodsService;
import com.bootdo.gamedata.vo.PageParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("exchange-goods")
public class ExchangeGoodsController {
    @Autowired
    private ExchangeGoodsService exchangeGoodsService;

    @RequestMapping("/findPage")   //查询所有分类
    @ResponseBody
    public Page<ExchangeGoodsDO> findPage(PageParams pageParams, ExchangeGoodsQo qo) throws Exception {
        Sort sort = new Sort(Sort.Direction.DESC,"id"); //设置根据id倒序排列
        Pageable pageable = new PageRequest(pageParams.getPage(), pageParams.getSize(),sort); //根据start、size、sort创建分页对象
        return exchangeGoodsService.findPage(qo,pageable);
    }
}
