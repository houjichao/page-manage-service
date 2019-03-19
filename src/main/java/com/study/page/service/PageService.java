package com.study.page.service;

import com.study.page.model.PmsPage;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

public interface PageService {

    /**
     * 新增修改论文
     *
     * @param page
     * @return
     */
    String mergePage(PmsPage page);

    /**
     * 根据ID查询论文
     *
     * @param id
     * @return
     */
    PmsPage queryPageById(String id);


    /**
     * 上传文件
     *
     * @param targetFile
     * @return
     */
    String uploadFile(MultipartFile targetFile);


    /**
     * 论文评分
     *
     * @param page
     * @return
     */
    String updatePageScore(PmsPage page);


    /**
     * 下载论文
     *
     * @param fileName
     * @param response
     * @return
     */
    String downloadFile(String fileName, HttpServletResponse response);


    /**
     * 删除论文
     *
     * @param id
     * @return
     */
    Boolean delPage(String id);

}
