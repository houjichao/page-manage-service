package com.study.page.service;

import java.util.List;

/**
 * 分词相关API
 */
public interface SegmentationWordService {
    /**
     * 中文分词
     *
     * @param data
     * @return
     */
    List<String> segmentationWord(String data);
}
