package com.study.page.config;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.internal.DefaultCommentGenerator;
import org.mybatis.generator.internal.util.StringUtility;



/**
 * Created with IntelliJ IDEA.
 * Description: mybatis generator 自定义comment生成器
 */
public class MyCommentGenerator extends DefaultCommentGenerator {
    /**
     * 定义一个是否使用修改后的模式的标识
     */
    private boolean suppressAllComments = true;

    /**
     * 设置实体类 属性注释
     */
    @Override
    public void addFieldComment(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {

        if (suppressAllComments) {
            if (StringUtility.stringHasValue(introspectedColumn.getRemarks())) {
                StringBuilder sb = new StringBuilder();
                field.addJavaDocLine("/**");
                sb.append(" * ");
                sb.append(introspectedColumn.getRemarks());
                field.addJavaDocLine(sb.toString());
                field.addJavaDocLine(" */");
            }
        }else {
            super.addFieldComment(field, introspectedTable, introspectedColumn);
        }
    }

    /**
     * 设置实体类 getter注释
     */
    @Override
    public void addGetterComment(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
        if (suppressAllComments) {
            if (StringUtility.stringHasValue(introspectedColumn.getRemarks())) {
                //method.addJavaDocLine("//获取:" + introspectedColumn.getRemarks());
                return;
            }
        }else {
            super.addGetterComment(method, introspectedTable, introspectedColumn);
        }
    }

    /**
     * 设置实体类 setter注释
     */
    @Override
    public void addSetterComment(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
        if (suppressAllComments) {
            if (StringUtility.stringHasValue(introspectedColumn.getRemarks())) {
                //method.addJavaDocLine("//设置:" + introspectedColumn.getRemarks());
                return;
            }
        }else {
            super.addSetterComment(method, introspectedTable, introspectedColumn);
        }
    }

    /**
     * 去掉mapper原始注释
     */
    @Override
    public void addGeneralMethodComment(Method method,IntrospectedTable introspectedTable) {
        if (suppressAllComments) {
            return;
        }else {
            super.addGeneralMethodComment(method, introspectedTable);
        }
    }

    /**
     * 去掉mapping原始注释
     */
    @Override
    public void addComment(XmlElement xmlElement) {
        if (suppressAllComments) {
            return;
        }else {
            super.addComment(xmlElement);
        }
    }


}
