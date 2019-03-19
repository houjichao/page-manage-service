package com.study.page.controller;

import com.study.page.service.RedisService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/redis")
@Slf4j
@Api(value = "缓存操作API",description = "缓存操作API")
public class RedisController {

    @Autowired
    RedisService redisService;

    @ApiOperation(value = "新增缓存")
    @PostMapping(value = "/add")
    public Boolean saveCache(@RequestParam String key,@RequestParam String value) {
        return redisService.mergeKeyValue(key,value);
    }

    @ApiOperation(value = "修改缓存")
    @PostMapping(value = "/update")
    public Boolean updateCache(@RequestParam String key,@RequestParam String value) {
        return redisService.mergeKeyValue(key, value);
    }

    @ApiOperation(value = "查询缓存")
    @GetMapping(value = "/get/{key}")
    public String queryValueByKey(@PathVariable String key) {
        return redisService.queryValueByKey(key);
    }

    @ApiOperation(value = "删除缓存")
    @DeleteMapping(value = "/del/{key}")
    public Boolean delCache(@PathVariable String key) {
        return redisService.delKeyValue(key);
    }

}
