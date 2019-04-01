package com.study.page.controller;


import com.study.page.service.SegmentationWordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/seg")
@Slf4j
@Api(value = "中文分词API", description = "中文分词API")
public class SegmentationWordController {

    @Autowired
    SegmentationWordService segmentationWordService;

    @ApiOperation(value = "中文分词")
    @PostMapping(value = "/segWord")
    public List<String> segWord(@RequestParam String data) {
        return segmentationWordService.segmentationWord(data);
    }
}
