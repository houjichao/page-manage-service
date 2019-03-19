package com.study.page.controller;

import com.study.page.model.PmsDataBank;
import com.study.page.service.DataBankService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/data")
@Slf4j
@Api(value = "资料库API", description = "资料库API")
public class DataBankController {

    @Autowired
    DataBankService dataBankService;

    @ApiOperation(value = "新增资料文章")
    @PostMapping(value = "/add")
    public String saveData(@RequestBody PmsDataBank dataBank) {
        return dataBankService.mergeData(dataBank);
    }


    @ApiOperation(value = "修改资料文章")
    @PostMapping(value = "/update")
    public String updateData(@RequestBody PmsDataBank pmsDataBank) {
        return dataBankService.mergeData(pmsDataBank);
    }


    @ApiOperation(value = "查询资料文章")
    @GetMapping(value = "/get/{id}")
    public PmsDataBank queryData(@PathVariable String id) {
        return dataBankService.queryDataById(id);
    }


    @ApiOperation(value = "查询所有资料文章")
    @GetMapping(value = "/getAll")
    public List<PmsDataBank> queryAllData() {
        return dataBankService.queryAllData();
    }

    /**
     * 资料文章上传
     * @param file
     * @return
     */
    @ApiOperation(value = "资料文章上传", notes = "资料文章上传")
    @RequestMapping(value = "/upload", method = {RequestMethod.GET, RequestMethod.POST})
    public String importFromExcel(@RequestParam(value = "file") MultipartFile file) {
        return dataBankService.uploadFile(file);
    }


    @ApiOperation(value = "资料文章下载", notes = "资料文章下载")
    @PostMapping("/download")
    public String downloadFile(@RequestParam(value = "fileName") String fileName, HttpServletResponse response) {
        return dataBankService.downloadFile(fileName, response);
    }

}
