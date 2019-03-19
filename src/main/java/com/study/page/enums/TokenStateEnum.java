package com.study.page.enums;

/**
 * 枚举，定义token的三种状态
 * @author yf
 *
 */
public enum TokenStateEnum {
    /**
     * 过期
     */
    EXPIRED("EXPIRED"),
    /**
     * 无效(token不合法)
     */
    INVALID("INVALID"),
    /**
     * 有效的
     */
    VALID("VALID");

    private String  state;

    private TokenStateEnum(String state) {
        this.state = state;
    }

    /**
     * 根据状态字符串获取token状态枚举对象
     * @param tokenState
     * @return
     */
    public static TokenStateEnum getTokenState(String tokenState){
        TokenStateEnum[] states=TokenStateEnum.values();
        TokenStateEnum ts=null;
        for (TokenStateEnum state : states) {
            if(state.toString().equals(tokenState)){
                ts=state;
                break;
            }
        }
        return ts;
    }
    public String toString() {
        return this.state;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }

}