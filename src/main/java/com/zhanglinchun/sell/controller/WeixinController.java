package com.zhanglinchun.sell.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 微信支付原始api开发demo
 */
@Slf4j
@RestController
@RequestMapping("/weixin")
public class WeixinController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/auth")
    public void auth(@RequestParam("code") String code) {
        log.info("==========>微信用户同意授权，获取code");
        log.info("==========>获取code成功，code={}" + code);

        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wx1a67eaa5669d999a&secret=0dcdaad4b0b6ea8545f3f8881ca230f5&code="+ code +"&grant_type=authorization_code";
        String result = restTemplate.getForObject(url, String.class);
        log.info("==========>获取微信用户网页授权access_token，access_token={}" + result);
    }
}
