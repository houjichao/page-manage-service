package com.study.page.mapper;

import com.study.page.model.PmsPage;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface PmsPageMapper {
    int deleteByPrimaryKey(String id);

    int insert(PmsPage record);

    int insertSelective(PmsPage record);

    PmsPage selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PmsPage record);

    int updateByPrimaryKey(PmsPage record);

    /**
     * 查询所有论文
     *
     * @return
     */
    List<PmsPage> queryAllPage();

    /**
     * 查询多个
     *
     * @param ids
     * @return
     */
    List<PmsPage> queryPageByIds(@Param(value = "ids") Set<String> ids);

}