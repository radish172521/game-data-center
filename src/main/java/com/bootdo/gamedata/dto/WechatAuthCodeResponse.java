package com.bootdo.gamedata.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WechatAuthCodeResponse {
    private String openid;

    @JsonProperty("session_key")
    private String sessionKey;

    /**
     * 请求失败错误码
     */
    private String errcode;

    /**
     * 请求失败错误信息
     */
    private String errmsg;
}
