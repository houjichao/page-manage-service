package com.study.page.base;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class BusinessException extends RuntimeException {

    private static Logger LOGGER = LoggerFactory.getLogger(BusinessException.class);

    private String message;

    private BusinessExceptionEnum code;

    private Throwable error;

    public BusinessException(BusinessExceptionEnum code) {
        this.code = code == null ? BusinessExceptionEnum.BUSINESS_NORMAL : code;
    }

    public BusinessException(String message) {
        super(message);
        this.code = BusinessExceptionEnum.BUSINESS_NORMAL;
        this.message = message;
    }

    public BusinessException(BusinessExceptionEnum code, String message) {
        super(message);
        this.code = code == null ? BusinessExceptionEnum.BUSINESS_NORMAL : code;
        this.message = message;
    }

    public BusinessException(BusinessExceptionEnum code, String message, Throwable error) {
        super(message, error);
        this.code = code == null ? BusinessExceptionEnum.BUSINESS_NORMAL : code;
        this.message = message;
        this.error = error;
    }

    public void log() {
        LOGGER.warn(this.code.getCode() + "-" + (this.message == null ? this.code.getMsg() : this.message), this.error);
    }

    public BaseResponse toBaseResponse() {
        return new BaseResponse(this.code.getCode(), this.message == null ? this.code.getMsg() : this.message, null);
    }
}
