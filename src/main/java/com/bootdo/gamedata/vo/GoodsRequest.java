package com.bootdo.gamedata.vo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

@Getter
@Setter
public class GoodsRequest {
    private Integer id;
    private String goodsName;
    private BigDecimal goodsPrice;
    private String goodsPictureUrl;
    private Integer enabled;
    private Integer sort;
}
