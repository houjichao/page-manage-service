package com.study.page.service.impl;

import com.study.page.mapper.PmsTopicsMapper;
import com.study.page.model.PmsTopics;
import com.study.page.service.TopicsService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class TopicsServiceImpl implements TopicsService {
    @Autowired
    PmsTopicsMapper pmsTopicsMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public PmsTopics mergeTopics(PmsTopics topics) {
        if (StringUtils.isEmpty(topics.getId())){
            topics.setId(UUID.randomUUID().toString());
            topics.setAddTime(System.currentTimeMillis());
            pmsTopicsMapper.insert(topics);
        }else{
            topics.setAddTime(System.currentTimeMillis());
            pmsTopicsMapper.updateByPrimaryKeySelective(topics);
        }
        return topics;
    }

    @Override
    public PmsTopics queryTopicsById(String id) {
        return pmsTopicsMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<PmsTopics> queryAllTopics() {
        return pmsTopicsMapper.queryAllTopics();
    }

    @Override
    public Boolean delTopics(String id) {
        pmsTopicsMapper.deleteByPrimaryKey(id);
        return true;
    }

    @Override
    public List<PmsTopics> queryTopicsByTeacher(String teacherId) {
        return pmsTopicsMapper.queryTopicsByTeacher(teacherId);
    }
}
