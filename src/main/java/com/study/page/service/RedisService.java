package com.study.page.service;

import com.sun.org.apache.xpath.internal.operations.Bool;

public interface RedisService {

    /**
     * 新增修改缓存
     *
     * @param key
     * @param value
     * @return
     */
    Boolean mergeKeyValue(String key,String value);


    /**
     * 删除缓存
     *
     * @param key
     * @return
     */
    Boolean delKeyValue(String key);


    /**
     * 查询缓存
     *
     * @param key
     * @return
     */
    String queryValueByKey(String key);

}
