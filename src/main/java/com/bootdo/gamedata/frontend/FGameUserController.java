package com.bootdo.gamedata.frontend;

import com.bootdo.common.utils.ResponseVO;
import com.bootdo.gamedata.component.WechatAuthProperties;
import com.bootdo.gamedata.dto.WechatAuthCodeResponse;
import com.bootdo.gamedata.dto.WechatAuthenticationResponse;
import com.bootdo.gamedata.service.GameUserService;
import com.bootdo.gamedata.vo.GameUserAccountVo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Map;


@RestController
@RequestMapping("front/user")
public class FGameUserController {

    private static final Logger logger = LoggerFactory.getLogger(FGameUserController.class);

    @Autowired
    private GameUserService gameUserService;

    @Autowired
    private WechatAuthProperties wechatAuthProperties;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private RestTemplate wxAuthRestTemplate = new RestTemplate();

    @RequestMapping("/account-info")
    public ResponseVO<GameUserAccountVo> getUserAccount(){
        return ResponseVO.ok(null);
    }

    @RequestMapping("/login")
    public ResponseVO<WechatAuthenticationResponse> login(@RequestParam String code){
        String urlString = "?appid={appid}&secret={srcret}&js_code={code}&grant_type={grantType}";
        String response = wxAuthRestTemplate.getForObject(
                wechatAuthProperties.getSessionHost() + urlString, String.class,
                wechatAuthProperties.getAppId(),
                wechatAuthProperties.getSecret(),
                code,
                wechatAuthProperties.getGrantType());
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectReader reader = objectMapper.readerFor(WechatAuthCodeResponse.class);
        WechatAuthCodeResponse res;
        try {
            res = reader.readValue(response);
        } catch (IOException e) {
            res = null;
            logger.error("反序列化失败", e);
        }
        logger.info(response);
        if (null == res) {
            throw new RuntimeException("调用微信接口失败");
        }
        if (res.getErrcode() != null) {
            throw new RuntimeException(res.getErrmsg());
        }
        String wxOpenId = res.getOpenid();
        String wxSessionKey = res.getSessionKey();
        //保存到

        String thirdSessionKey = RandomStringUtils.randomAlphanumeric(64);
        stringRedisTemplate.opsForValue().set(thirdSessionKey,wxOpenId);
        return ResponseVO.ok(new WechatAuthenticationResponse(thirdSessionKey));
    }
}
