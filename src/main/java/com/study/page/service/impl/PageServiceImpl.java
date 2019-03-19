package com.study.page.service.impl;

import com.study.page.mapper.PmsPageMapper;
import com.study.page.model.PmsPage;
import com.study.page.service.PageService;
import com.study.page.util.FileUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Service
public class PageServiceImpl implements PageService {

    private static Logger LOGGER = LoggerFactory.getLogger(PageServiceImpl.class);

    @Autowired
    PmsPageMapper pmsPageMapper;

    @Autowired
    FileUtil fileUtil;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public String mergePage(PmsPage page) {
        if (StringUtils.isBlank(page.getId())) {
            page.setId(UUID.randomUUID().toString());
            pmsPageMapper.insert(page);
        }else{
            pmsPageMapper.updateByPrimaryKeySelective(page);
        }
        return page.getId();
    }

    @Override
    public PmsPage queryPageById(String id) {
        return pmsPageMapper.selectByPrimaryKey(id);
    }

    @Override
    public String uploadFile(MultipartFile file) {
        // 设置文件存储路径
        String filePath = "/page/workspace/temp/";
        return fileUtil.uploadFile(file, filePath);
    }

    @Override
    public String updatePageScore(PmsPage page) {
        pmsPageMapper.updateByPrimaryKeySelective(page);
        return page.getId();
    }

    @Override
    public String downloadFile(String fileName, HttpServletResponse response) {
        String filePath = "page/workspace/temp/";
        return fileUtil.download(fileName, filePath, response);
    }

    @Override
    public Boolean delPage(String id) {
        pmsPageMapper.deleteByPrimaryKey(id);
        return true;
    }
}
