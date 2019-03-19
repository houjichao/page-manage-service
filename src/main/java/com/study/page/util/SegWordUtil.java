package com.study.page.util;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.ClassicAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.util.CharArraySet;
import org.apache.lucene.util.Version;
import org.springframework.util.ResourceUtils;


import java.io.*;

public class SegWordUtil {
    //创建一个去除停用词的分析器对象
    public static CharArraySet stopwords = new CharArraySet(Version.LUCENE_5_0_0.prerelease, false);
    public static Analyzer noStopAnalyzer;
    public static void initializeData() throws IOException{
        //读取停用词表格
        File filename = ResourceUtils.getFile("classpath:files/stopwords.txt");
        InputStreamReader reader = new InputStreamReader(new FileInputStream(filename));
        BufferedReader br = new BufferedReader(reader);
        String line = "";
        line = br.readLine();
        while(line != null){
            stopwords.add(line);
            line = br.readLine();
        }
        br.close();
        noStopAnalyzer = new ClassicAnalyzer(stopwords);
    }
    public static void testAnanlyzer(String cont) throws IOException{
		/*
		//不去除停用词的版本
		//创建一个中文分词的分析器对象
		Analyzer analyzer = new SmartChineseAnalyzer(Version.LUCENE_46);
		//从分析器对象中获得tokenStream对象
		TokenStream tokenStream = analyzer.tokenStream("myfield", new StringReader(cont));
		*/
        TokenStream tokenStream = noStopAnalyzer.tokenStream("myfield", new StringReader(cont));
        //设置一个引用，这个引用可以使多种类型，可以是关键词的引用，偏移量的引用等等
        //关键词的引用
        CharTermAttribute charTermAttribute = tokenStream.addAttribute(CharTermAttribute.class);
        //偏移量的引用，可以得到词汇开始和结束的位置
        //OffsetAttribute offsetAttribute = tokenStream.addAttribute(OffsetAttribute.class);
        tokenStream.reset();	//很关键，不然会报错
        while(tokenStream.incrementToken()){
            System.out.println(charTermAttribute);
        }
        tokenStream.close();
    }
    public static void main(String args[]) throws IOException {
        initializeData();
        String con = "这是一架高空高速飞行的飞机";
        testAnanlyzer(con);
    }

}
