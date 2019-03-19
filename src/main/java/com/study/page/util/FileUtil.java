package com.study.page.util;

import com.study.page.service.impl.PageServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Component
public class FileUtil {

    private static Logger LOGGER = LoggerFactory.getLogger(PageServiceImpl.class);

    public String uploadFile(MultipartFile file,String filePath) {
        try {
            if (file.isEmpty()) {
                return "文件为空";
            }
            // 获取文件名
            String fileName = file.getOriginalFilename();
            LOGGER.info("上传的文件名为：" + fileName);
            // 获取文件的后缀名
            /*String suffixName = fileName.substring(fileName.lastIndexOf("."));
            LOGGER.info("文件的后缀名为：" + suffixName);*/

            String path = filePath + fileName;

            File dest = new File(ResourceUtils.getURL("classpath:").getPath() + path);
            //dest.createNewFile();
            // 检测是否存在目录
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();// 新建文件夹
            }
            file.transferTo(dest);// 文件写入
            return path;
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "上传失败";
    }

    public String download(String fileName,String filePath, HttpServletResponse response){
        if (fileName != null) {
            //设置文件路径
            String path = filePath + fileName;
            File file = null;
            try {
                file = new File(ResourceUtils.getURL("classpath:").getPath() + path);

            } catch (Exception ex) {
                LOGGER.error("文件不存在");
            }
            //File file = new File(realPath , fileName);
            if (file.exists()) {
                response.setContentType("application/force-download");// 设置强制下载不打开
                response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);// 设置文件名
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                    return "下载成功";
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return "下载失败";
    }
}
