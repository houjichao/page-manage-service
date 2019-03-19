package com.study.page.enums;

import com.google.common.collect.Lists;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description: 错误码枚举类
 */
public enum ErrorCodeEnum {

    /**
     通用错误码以100**开头命名
     应用系统错误码以200**开头命名
     用户相关错误码以300**开头命名
     角色错误码以400**开头命名
    **/

    DB_CONN_EXCEPTION(10001, "10001", "数据库连接异常"),
    DB_OPERATION_EXCEPTION(10002, "10002", "数据库操作异常"),

    USER_NON_EXIST(30001, "30001", "用户不存在或已被禁用"),
    USER_NAME_EXIST(30002, "30002", "用户名已存在"),
    USER_LOCKED(30003, "30003", "该用户已被禁用"),
    USER_EXPIRE(30004, "30004", "当前时间已超过用户有效期"),
    USER_OLD_PSW_ERROR(30005, "30005", "修改密码时旧密码错误"),
    USER_WRONG_PSW(30006, "30006", "用户密码错误"),
    USER_WRONG_SYS(30007, "30007", "用户没有系统权限"),
    //token无效或不合法
    USER_TOKEN_INVALID(30008, "30008", "无效的登录"),
    //token超时
    USER_TOKEN_EXPIRED(30009, "30009", "过期的登录"),
    //非超管
    USER_NON_MANAGER(30010, "30010", "用户不是超级管理员"),
    //非超管
    USER_IS_CURRENT(30011, "30011", "该用户是当前用户"),
    USER_ID_EMPTY(30012, "30012", "用户ID为空"),
    USER_STATUS_ERROR(30013,"30013","用户状态错误"),
    USER_IS_MANAGER(30018, "30018", "用户是超级管理员");


    private int flag;
    private String errCode;
    private String errDesc;

    private ErrorCodeEnum(int flag, String errCode, String errDesc) {
        this.flag = flag;
        this.errCode = errCode;
        this.errDesc = errDesc;
    }

    public int getFlag() {
        return flag;
    }

    public String getErrCode() {
        return this.errCode;
    }

    public String getErrDesc() {
        return this.errDesc;
    }

    @Override
    public String toString() {
        return "ErrorCodeEnum{" +
                "errCode='" + errCode + '\'' +
                ", errDesc='" + errDesc + '\'' +
                '}';
    }

    public static ErrorCodeEnum valueOf(int flag) {
        return FLAG_TO_ERROR_ENUM.get(flag);
    }

    private static final Map<Integer, ErrorCodeEnum> FLAG_TO_ERROR_ENUM = new HashMap<>();

    static {
        for (ErrorCodeEnum errorCodeEnum : ErrorCodeEnum.values()) {
            FLAG_TO_ERROR_ENUM.put(errorCodeEnum.flag, errorCodeEnum);
        }
    }

    public static List<String> getAllCode() {
        List<String> codeList = Lists.newArrayList();
        for (ErrorCodeEnum errorCodeEnum : ErrorCodeEnum.values()) {
            codeList.add(errorCodeEnum.getErrCode());
        }
        return codeList;
    }

    public static boolean containCode(String code) {
        List<String> codeList = getAllCode();
        return codeList.contains(code);
    }

}
