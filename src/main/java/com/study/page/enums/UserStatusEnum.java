package com.study.page.enums;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description: 用户状态枚举
 */
public enum UserStatusEnum {
    AVAILABLE(1, "可用"),
    LOCKED(2,"锁定"),
    DELETED(3,"被删除");

    private int code;
    private String msg;

    private UserStatusEnum(int code, String msg) {
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
        for (UserStatusEnum userStatusEnum : UserStatusEnum.values()) {
            codeList.add(userStatusEnum.code);
        }
        return codeList;
    }

    public static  boolean containCode(int code) {
        List<Integer> codeList = getAllCode();
        return codeList.contains(code);
    }
}
