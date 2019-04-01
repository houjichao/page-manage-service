package com.study.page.controller;


import com.study.page.model.PmsUser;
import com.study.page.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api("用户服务")
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    UserService userService;

    @ApiOperation(value = "用户注册")
    @PostMapping(value = "/add")
    public PmsUser saveUser(@RequestBody PmsUser user) {
        return userService.mergeUser(user);
    }


    @ApiOperation(value = "用户修改")
    @PostMapping(value = "/update")
    public PmsUser updateUser(@RequestBody PmsUser user) {
        return userService.mergeUser(user);
    }


    @ApiOperation(value = "用户查询")
    @GetMapping(value = "/get")
    public PmsUser queryUser(@PathVariable String id) {
        return userService.queryUserById(id);
    }


    @ApiOperation(value = "用户删除")
    @DeleteMapping(value = "/del/{id}")
    public PmsUser delPage(String id){
        return userService.delUser(id);
    }

}
