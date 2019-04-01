package com.study.page.controller;


import com.study.page.model.PmsTopics;
import com.study.page.service.TopicsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/topics")
@Slf4j
@Api(value = "选题相关API",description = "选题相关API")
public class TopicsController {
    private static Logger LOGGER = LoggerFactory.getLogger(TopicsController.class);

    @Autowired
    TopicsService topicsService;

    @ApiOperation(value = "新增选题")
    @PostMapping(value = "/add")
    public PmsTopics saveTopics(@RequestBody PmsTopics topics) {
        return topicsService.mergeTopics(topics);
    }


    @ApiOperation(value = "修改选题")
    @PostMapping(value = "/update")
    public PmsTopics updateTopics(@RequestBody PmsTopics topics) {
        return topicsService.mergeTopics(topics);
    }


    @ApiOperation(value = "查询选题")
    @GetMapping(value = "/get/{id}")
    public PmsTopics queryTopics(@PathVariable String id) {
        return topicsService.queryTopicsById(id);
    }


    @ApiOperation(value = "删除选题")
    @DeleteMapping(value = "/del/{id}")
    public Boolean delTopics(@PathVariable String id) {
        return topicsService.delTopics(id);
    }

    @ApiOperation(value = "查询所有选题")
    @GetMapping(value = "/getAll")
    public List<PmsTopics> queryAllTopics() {
        return topicsService.queryAllTopics();
    }


    @ApiOperation(value = "根据查询选题")
    @GetMapping(value = "/getByTeacher/{id}")
    public List<PmsTopics> queryTopicsByTeacher(@PathVariable String teacherId){
        return null;
    }

}
