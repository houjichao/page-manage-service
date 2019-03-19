package com.study.page.controller;

import com.alibaba.fastjson.JSONObject;
import com.study.page.model.PmsUser;
import com.study.page.service.AuthorizationService;
import com.study.page.util.CookieUtil;
import com.study.page.util.TokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RestController
@RequestMapping("/auth")
@Slf4j
@Api(value = "用户登录", description = "用户登录")
public class AuthorizationController {

    private static Logger LOGGER = LoggerFactory.getLogger(AuthorizationController.class);


    @Resource
    AuthorizationService authorizationService;

    /**
     * @Description 用户登录校验
     **/
    @PostMapping(value = "/loginUserAuth")
    @ApiOperation(value = "用户登录校验", notes = "用户登录校验")
    public JSONObject loginUserAuth(@RequestBody PmsUser loginEntity, HttpServletResponse response) {
        JSONObject data = authorizationService.loginUserAuth(loginEntity);
        if (data != null) {
            String tokenKey = data.getString("tokenId");
            CookieUtil.setNewCookie(TokenUtil.COOKIE_TOKEN_PREFIX, null, tokenKey, response); //设置cookies
        }
        return data;
    }

    /**
     * @Description 用户登出接口
     **/
    @GetMapping(value = "/logout")
    @ApiOperation(value = "用户登出", notes = "用户登出")
    public JSONObject logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Cookie[] cookies = request.getCookies();
        String accessToken = null;
        JSONObject data = new JSONObject();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (TokenUtil.COOKIE_TOKEN_PREFIX.equals(cookie.getName())) {
                    accessToken = cookie.getValue();
                }
            }
        }
        if (StringUtils.isEmpty(accessToken)) {
            LOGGER.error("没有获取到accessToken");
            throw new Exception("没有获取到accessToken");
        }
        if (!StringUtils.isEmpty(accessToken)) {
            data = authorizationService.logout(accessToken);
        }
        if (data != null && "success".equals(data.getString("result"))) {
            CookieUtil.deleteCookies(TokenUtil.COOKIE_TOKEN_PREFIX, request, response);
        }
        return data;
    }

    /**
     * @Description 验证token时效性
     **/
    @RequestMapping(path = "/validToken", method = RequestMethod.POST)
    @ApiOperation(value = "验证token时效性", notes = "验证token时效性")
    @ApiImplicitParam(name = "tokenId", value = "tokenId", dataType = "String")
    public JSONObject validToken(String tokenId) {
        JSONObject validData = authorizationService.validTokenByUser(tokenId);
        return validData;
    }

    /**
     * @Description 根据token获取redis中的用户信息
     **/
    @RequestMapping(path = "/getUserInfo", method = RequestMethod.POST)
    @ApiOperation(value = "根据token获取redis中的用户信息", notes = "根据token获取redis中的用户信息")
    @ApiImplicitParam(name = "tokenId", value = "tokenId", dataType = "String")
    public JSONObject getUserInfo(String tokenId) {
        JSONObject object = authorizationService.getUserInfo(tokenId);
        return object;
    }

}
