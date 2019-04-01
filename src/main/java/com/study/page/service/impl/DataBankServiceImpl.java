package com.study.page.service.impl;

import com.study.page.mapper.PmsDataBankMapper;
import com.study.page.model.PmsDataBank;
import com.study.page.service.DataBankService;
import com.study.page.util.FileUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.UUID;

@Service
public class DataBankServiceImpl implements DataBankService {

    @Autowired
    PmsDataBankMapper dataBankMapper;

    @Autowired
    FileUtil fileUtil;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public PmsDataBank mergeData(PmsDataBank dataBank) {
        if (StringUtils.isBlank(dataBank.getId())) {
            dataBank.setId(UUID.randomUUID().toString());
            dataBankMapper.insert(dataBank);
        }else{
            dataBankMapper.updateByPrimaryKeySelective(dataBank);
        }
        return dataBank;
    }

    @Override
    public PmsDataBank queryDataById(String id) {
        return dataBankMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<PmsDataBank> queryAllData() {
        return dataBankMapper.queryAllData();
    }

    @Override
    public String uploadFile(MultipartFile targetFile) {
        // 设置文件存储路径
        String filePath = "/data/workspace/temp/";
        return fileUtil.uploadFile(targetFile, filePath);
    }

    @Override
    public String downloadFile(String fileName, HttpServletResponse response) {
        String filePath = "data/workspace/temp/";
        return fileUtil.download(fileName, filePath, response);
    }
}
