package com.narc.alibaba.service.alimama.controller;

import com.narc.alibaba.service.alimama.service.AlimamaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @ApiOperation(value = "创建用户", notes = "根据User对象创建用户")
    @ApiImplicitParam(name = "param", value = "param", required = true, dataType = "String")
    @PostMapping(value = "/user")
    public String getUser(String param) {
        log.info(param);
        return "123";
    }

    @ApiOperation(value = "淘口令转链", notes = "淘口令转链")
    @GetMapping(value = "/tranShareWord")
    public String tranShareWord(String shareWord) {
        return alimamaService.tranShareWord(shareWord);
    }


}
