package com.narc.alibaba.service.alimama.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.narc.alibaba.service.alimama.service.AlimamaService;
import com.narc.alibaba.utils.DateUtils;
import com.narc.alibaba.utils.HttpUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author : Narcssus
 * @date : 2019/11/17 17:03
 */
@RestController
@RequestMapping("/alimama")
@Api(tags = "淘宝联盟相关接口")
@Slf4j
public class AlimamaController {


    @Autowired
    private AlimamaService alimamaService;

    @ApiOperation(value = "淘口令转链", notes = "淘口令转链")
    @GetMapping(value = "/tranShareWord")
    public JSONObject tranShareWord(String param) {
        log.info("淘口令转链收到请求：{}", param);
        JSONObject paramObject = JSON.parseObject(param);
        return alimamaService.tranShareWord(paramObject);
    }

    @ApiOperation(value = "淘口令管理接口", notes = "淘口令管理接口")
    @GetMapping(value = "/dealTklAdmin")
    public JSONObject dealTklAdmin(String param) {
        log.info("淘口令转链收到请求：{}", param);
        JSONObject paramObject = JSON.parseObject(param);
        return alimamaService.dealTklAdmin(paramObject);
    }

    @ApiOperation(value = "test", notes = "test")
    @GetMapping(value = "/test")
    public String test(String startTime,String endTime) {

        alimamaService.getOrders(
                DateUtils.convertStrToDate(startTime,DateUtils.FORMAT_19),
                DateUtils.convertStrToDate(endTime,DateUtils.FORMAT_19)
        );
        return "";
    }

//    @Value("${from}")
//    private String value;
//
//    @ApiOperation(value = "test", notes = "test")
//    @GetMapping(value = "/test2")
//    public String test2(String param) {
//        return value;
//    }


}
