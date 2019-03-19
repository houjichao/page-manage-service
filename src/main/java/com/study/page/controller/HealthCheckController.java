package com.study.page.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description: 健康检测
 */
@Api("服务")
@RestController
@RequestMapping("/health")
@Slf4j
public class HealthCheckController {

    @ApiOperation(value = "健康检测")
    @GetMapping(value = "/check")
    public String healthCheck() {
        return "OK";
    }
}
