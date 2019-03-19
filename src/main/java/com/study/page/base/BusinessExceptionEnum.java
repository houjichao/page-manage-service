package com.study.page.base;

public enum BusinessExceptionEnum {

    BUSINESS_NORMAL(10000, "业务异常"),
    UN_FIND_RESOURCE(10001, "未找到操作资源对象"),
    OPERATION_NOT_PERMITTED(10002, "不允许的操作"),
    DATABASE_OPERATE_ERROR(10003, "数据库操作异常"),
    BUSINESS_DATA_ERROR(10004, "业务数据为空或错误");

    private int code;
    private String msg;

    private BusinessExceptionEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public static BusinessExceptionEnum fromCode(int code) {
        for (BusinessExceptionEnum item : BusinessExceptionEnum.values()) {
            if (code == item.getCode()) {
                return item;
            }
        }
        return null;
    }

}
