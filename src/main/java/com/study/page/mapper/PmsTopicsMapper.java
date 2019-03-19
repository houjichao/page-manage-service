package com.study.page.mapper;

import com.study.page.model.PmsTopics;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface PmsTopicsMapper {
    int deleteByPrimaryKey(String id);

    int insert(PmsTopics record);

    int insertSelective(PmsTopics record);

    PmsTopics selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PmsTopics record);

    int updateByPrimaryKey(PmsTopics record);

    /**
     * 查询所有选题
     *
     * @return
     */
    List<PmsTopics> queryAllTopics();

    /**
     * 查询多个
     *
     * @param ids
     * @return
     */
    List<PmsTopics> queryTopicsByIds(@Param(value = "ids") Set<String> ids);
}