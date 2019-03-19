package com.study.page.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description 暂时保留，用于做一些测试的
 **/
@RestController
@RequestMapping("/tests")
@Slf4j
@Api(value = "单元测试", description = "单元测试")
public class EmptyController {


    @GetMapping(value = "/session")
    public void sessionTest(HttpServletRequest request) {
    }

    @PostMapping(value = "/jsontest")
    public Object jsonTest(@RequestParam  String json){
        return null;
    }
}
