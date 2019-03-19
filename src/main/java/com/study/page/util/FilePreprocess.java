package com.study.page.util;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FilePreprocess {
    //预处理
    public static void preprocess(File file,String outputDir) {
        try {
            //拆分成小文件
            splitToSmallFiles(file, outputDir);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void splitToSmallFiles(File file,String outputpath)throws IOException {
        int filePointer = 0;   //文件命名 自增序列
        int MAX_SIZE = 10240; //设置文件大小
        BufferedWriter writer = null;
        BufferedReader reader = new BufferedReader(new FileReader(file));
        StringBuffer buffer = new StringBuffer();
        String line = reader.readLine();
        while (line != null) {
            buffer.append(line).append("\r\n");
            if (buffer.toString().getBytes().length >= MAX_SIZE) {   //如果超出限制大小 new file
                writer = new BufferedWriter(new FileWriter(outputpath + "output" + filePointer + ".txt"));
                writer.write(buffer.toString());
                writer.close();
                filePointer++;

                buffer = new StringBuffer();
            }
            line = reader.readLine();
        }
        writer = new BufferedWriter(new FileWriter(outputpath + "output" + filePointer + ".txt"));
        writer.write(buffer.toString());
        writer.close();
    }
    //入口
    public static void main(String [] args) {
        String inputFile = "f:\\book.txt";  //读取文件
        String outputf = "f:\\outputFolder\\";        //文件预处理后输出目录
        if (!new File(outputf).exists()) {
            new File(outputf).mkdirs();
        }
        FilePreprocess f = new FilePreprocess();
        f.preprocess(new File(inputFile), outputf);  //分块处理

    }
}
