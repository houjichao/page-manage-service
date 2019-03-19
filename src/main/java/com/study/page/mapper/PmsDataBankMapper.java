package com.study.page.mapper;

import com.study.page.model.PmsDataBank;
import com.study.page.model.PmsPage;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PmsDataBankMapper {
    int deleteByPrimaryKey(String id);

    int insert(PmsDataBank record);

    int insertSelective(PmsDataBank record);

    PmsDataBank selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PmsDataBank record);

    int updateByPrimaryKey(PmsDataBank record);

    /**
     * 查询所有资料文章
     *
     * @return
     */
    List<PmsDataBank> queryAllData();

}