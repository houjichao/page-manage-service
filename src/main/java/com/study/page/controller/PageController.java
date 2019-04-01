package com.study.page.controller;


import com.study.page.model.PmsPage;
import com.study.page.service.PageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "/page")
@Slf4j
@Api(value = "论文相关API",description = "论文相关API")
public class PageController {

    private static Logger LOGGER = LoggerFactory.getLogger(PageController.class);

    @Autowired
    PageService pageService;


    @ApiOperation(value = "新增论文")
    @PostMapping(value = "/add")
    public PmsPage savePage(@RequestBody PmsPage page) {
        return pageService.mergePage(page);
    }


    @ApiOperation(value = "修改论文")
    @PostMapping(value = "/update")
    public PmsPage updatePage(@RequestBody PmsPage page) {
        return pageService.mergePage(page);
    }


    @ApiOperation(value = "查询论文")
    @GetMapping(value = "/get/{id}")
    public PmsPage queryPage(@PathVariable String id) {
        return pageService.queryPageById(id);
    }

    @ApiOperation(value = "论文评分")
    @PostMapping(value = "/updateScore")
    public PmsPage updatePageScore(@RequestBody PmsPage page) {
        return pageService.mergePage(page);
    }

    @ApiOperation(value = "删除论文")
    @DeleteMapping(value = "/del/{id}")
    public Boolean delPage(String id){
        return pageService.delPage(id);
    }

    /**
     * 论文上传
     * @param file
     * @return
     */
    @ApiOperation(value = "论文上传", notes = "论文上传")
    @RequestMapping(value = "/upload", method = {RequestMethod.GET, RequestMethod.POST})
    public String importFromExcel(@RequestParam(value = "file") MultipartFile file) {
        return pageService.uploadFile(file);
    }


    @PostMapping("/download")
    public String downloadFile(@RequestParam(value = "fileName") String fileName, HttpServletResponse response) {
        return pageService.downloadFile(fileName, response);
    }

}
