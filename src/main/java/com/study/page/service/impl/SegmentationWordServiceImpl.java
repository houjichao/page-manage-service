package com.study.page.service.impl;

import com.google.common.collect.Lists;
import com.study.page.service.SegmentationWordService;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.IOException;
import java.util.List;

@Service
public class SegmentationWordServiceImpl implements SegmentationWordService {
    private static Logger LOGGER = LoggerFactory.getLogger(SegmentationWordServiceImpl.class);

    @Override
    public List<String> segmentationWord(String data) {
        Analyzer analyzer = new IKAnalyzer();
        TokenStream stream = null;
        List<String> result = Lists.newArrayList();
        try {
            stream = analyzer.tokenStream("myfield", data);
            stream.reset();
            CharTermAttribute offsetAtt = stream.addAttribute(CharTermAttribute.class);
            while (stream.incrementToken()) {
                result.add(offsetAtt.toString());
            }
            stream.end();
        } catch (IOException e) {
            LOGGER.error("seg word exception:{}", e);
        } finally {
            try {
                if (stream != null) {
                    stream.close();
                }
            } catch (IOException e) {
                LOGGER.error("seg word exception:{}", e);
            }
        }
        return result;
    }
}
