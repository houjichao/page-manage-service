package com.study.page.enums;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Description: 用户类型
 *
 * @author: hjc
 * @date: 2019-02-18 11:16
 */
public enum UserTypeEnum {

    MANAGER(1, "管理员"),
    TEACHER(2, "教师"),
    STUDENT(3, "学生");

    private int code;
    private String msg;

    private UserTypeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public static List<Integer> getAllCode() {
        List<Integer> codeList = Lists.newArrayList();
        for (UserTypeEnum administrativeLevelEnum : UserTypeEnum.values()) {
            codeList.add(administrativeLevelEnum.code);
        }
        return codeList;
    }

    public static boolean containCode(Integer code) {
        List<Integer> codeList = getAllCode();
        return codeList.contains(code);
    }
}
