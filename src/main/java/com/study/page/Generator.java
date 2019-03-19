package com.study.page;

import org.mybatis.generator.api.ShellRunner;

/**
 * Created with IntelliJ IDEA.
 * Description: mybatis生成类
 */
public class Generator {
    /**
     * 该配置文件放在src/main/resources/该路径下即可
     * 使用该生成类，修改配置文件中的表即可
     */
    public static void main(String[] args) {
        args = new String[]{"-configfile", "src\\main\\resources\\mybatis-generator.xml", "-overwrite"};
        ShellRunner.main(args);
    }
}
