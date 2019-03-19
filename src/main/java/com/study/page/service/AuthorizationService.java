
package com.study.page.service;

import com.alibaba.fastjson.JSONObject;
import com.study.page.model.PmsUser;

/**
 * @description: 用户认证相关接口
 **/
public interface AuthorizationService {

    /**
     * 登录
     **/
    JSONObject loginUserAuth(PmsUser loginEntity);


    /**
     * 登出接口
     **/
    JSONObject logout(String accessToken);

    /**
     * 根据登录的用户验证token是否超时或者无效
     **/
    JSONObject validTokenByUser(String tokenKey);

    /**
     * 根据登录的token获取用户信息
     **/
    JSONObject getUserInfo(String tokenKey);
    
}
