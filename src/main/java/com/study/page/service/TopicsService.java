package com.study.page.service;

import com.study.page.model.PmsTopics;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.List;

public interface TopicsService {
    /**
     * 新增修改选题
     *
     * @param topics
     * @return
     */
    String mergeTopics(PmsTopics topics);

    /**
     * 根据ID查询选题
     *
     * @param id
     * @return
     */
    PmsTopics queryTopicsById(String id);


    /**
     * 查询所有选题
     *
     * @return
     */
    List<PmsTopics> queryAllTopics();


    /**
     * 删除选题
     *
     * @param id
     * @return
     */
    Boolean delTopics(String id);

}
