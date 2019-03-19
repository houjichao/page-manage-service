package com.study.page.service;

import com.study.page.model.PmsDataBank;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface DataBankService {

    /**
     * 新增资料文章
     *
     * @param dataBank
     * @return
     */
    String mergeData(PmsDataBank dataBank);

    /**
     * 根据ID查询文章
     *
     * @param id
     * @return
     */
    PmsDataBank queryDataById(String id);


    /**
     * 查询所有资料文章
     *
     * @return
     */
    List<PmsDataBank> queryAllData();


    /**
     * 上传资料文章
     *
     * @param targetFile
     * @return
     */
    String uploadFile(MultipartFile targetFile);


    /**
     * 下载资料文章
     *
     * @param fileName
     * @param response
     * @return
     */
    String downloadFile(String fileName, HttpServletResponse response);

}
