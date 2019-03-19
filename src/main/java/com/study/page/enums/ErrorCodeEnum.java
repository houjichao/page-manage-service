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
    FACE_COMPARE_EXCEPTION(10003, "10003", "人脸比对异常"),

    APPLICATION_NON_EXIST(20001, "20001", "应用系统不存在"),
    APPLICATION_NAME_EXIST(20002, "20002", "应用系统名已存在"),
    APPLICATION_PRECONDITION_FAILED(20003, "20003", "该应用系统下存在用户，需要先删除应用系统用户关系以及用户"),
    APPLICATION_ID_EMPTY(20004, "20004", "应用系统ID为空"),

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
    USER_FACE_ERROR(30014, "30014", "人脸比对不通过"),
    USER_FACE_FEATURE_EMPTY(30015, "30015", "该用户未添加照片"),
    USER_FACE_FEATURE_ERROR(30016, "30016", "人脸特征参数有误"),
    USER_FACE_THRESHOLD_ERROR(30017, "30017", "该用户未设置人脸比对阈值"),
    USER_IS_MANAGER(30018, "30018", "用户是超级管理员"),

    ROLE_NON_EXIST(40001, "40001", "角色不存在"),
    ROLE_NAME_EXIST(40002, "40002", "角色名已存在"),
    ROLE_PRECONDITION_FAILED(40003, "40003", "该角色下存在用户，需要先删除用户角色关系以及用户"),
    ROLE_ID_EMPTY(40004,"40004","角色ID为空"),
    ROLE_TYPE_ERROR(40005,"40005","角色类型错误"),

    USER_GOVERNMENT_NON_EXIST(50001, "50001", "用户组织不存在"),
    USER_GOVERNMENT_NAME_EXIST(50002, "50002", "用户组织名已存在"),
    USER_GOVERNMENT_PRECONDITION_FAILED(50003, "50003", "该用户组织下存在用户，不可以删除该组织"),
    USER_GOVERNMENT_EXIST_CHILD(50004, "50004", "该用户组织下存在子组织，不可以删除该组织"),
    USER_GOVERNMENT_CODE_EXIST(50005, "50005", "用户组织编码已存在"),
    USER_GOVERNMENT_ID_EMPTY(50006,"50006","用户组织ID为空"),
    USER_GOVERNMENT_CODE_ERROR(50007, "50007", "用户组织编码(社会信用代码)有误"),
    USER_GOVERNMENT_CODE_LENGTH_ERROR(50008, "50008", "用户组织编码(社会信用代码)长度错误，应为9位或18位"),
    USER_GOVERNMENT_CODE_EMPTY(50009, "50009", "用户组织编码(社会信用代码)为空"),
    USER_GOVERNMENT_TIER_MAX(50010, "50010", "用户组织层级过大"),
    USER_GOVERNMENT_LEVEL_ERROR(50011, "50011", "用户组织行政级别有误，应为1-6或者99");


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
