package com.study.page.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.study.page.base.BusinessException;
import com.study.page.base.BusinessExceptionEnum;
import com.study.page.enums.ErrorCodeEnum;
import com.study.page.mapper.PmsUserMapper;
import com.study.page.model.PmsUser;
import com.study.page.service.AuthorizationService;
import com.study.page.util.TokenUtil;
import liquibase.util.MD5Util;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 登录实现
 */
@Service
@Slf4j
public class AuthorizationServiceImpl implements AuthorizationService {
    @Resource
    PmsUserMapper pmsUserMapper;

    @Value("${user.cookie.time:30}")
    private Integer EXPIRE_TIME;

    Map<String, Integer> pswErrorCountMap = Maps.newHashMap();

    @Autowired
    StringRedisTemplate redisTemplate;

    @Override
    public JSONObject loginUserAuth(PmsUser user) {
        JSONObject result = new JSONObject();
        //根据登录用户验证用户
        PmsUser pmsUser = pmsUserMapper.checkLoginUserByName(user);
        //----------用户存在性校验
        if (null != pmsUser) {
            String pmsUserPsw = pmsUser.getPassword();
            String loginPsw = MD5Util.computeMD5(user.getPassword());
            String userId = user.getId();
            //----------用户密码校验
            if (!loginPsw.equals(pmsUserPsw)) {
                if (pswErrorCountMap.get(userId) == null) {
                    pswErrorCountMap.put(user.getId(), 1);
                } else {
                    pswErrorCountMap.replace(user.getId(), pswErrorCountMap.get(userId).intValue() + 1);
                }
                if (pswErrorCountMap.get(userId) != null && pswErrorCountMap.get(userId) >= 3) {
                    //将用户表状态改为锁定
                    //将用户状态置为锁定后移除错误次数记录。
                    pswErrorCountMap.remove(userId);
                }
                //用户密码错误
                int surplusCount = 3 - pswErrorCountMap.get(userId).intValue();
                throw new BusinessException(BusinessExceptionEnum.BUSINESS_DATA_ERROR, ErrorCodeEnum.USER_WRONG_PSW.getErrDesc() + ", 剩余错误次数：" + surplusCount, new Throwable());
            } else {
                this.doLogin(pmsUser, user, result);
            }
        } else {
            //用户不存在
            throw new BusinessException(BusinessExceptionEnum.BUSINESS_DATA_ERROR, ErrorCodeEnum.USER_NON_EXIST.getErrDesc(), new Throwable());
        }
        return result;
    }

    /**
     * 执行登录流程
     */
    private void doLogin(PmsUser pmsUser, PmsUser user, JSONObject result) {
        //将登录用户的用户名进行MD5加密作为token共享的标识
        String tokenKey = MD5Util.computeMD5(user.getLoginName() + System.currentTimeMillis());
        String redisKey = TokenUtil.ACCESS_REDISKEY + TokenUtil.TOKEN_PREFIX + tokenKey;
        result.put("user", JSONObject.toJSONString(user));
        result.put("tokenId", tokenKey);
        //将token绑定用户数据存入缓存
        redisTemplate.opsForHash().putAll(redisKey, result);
        redisTemplate.expire(redisKey, 30, TimeUnit.MINUTES);
        //移除记录错误次数
        pswErrorCountMap.remove(user.getId());
    }

    @Override
    public JSONObject logout(String accessToken) {
        JSONObject responseData = new JSONObject();
        String redisKey = TokenUtil.ACCESS_REDISKEY + TokenUtil.TOKEN_PREFIX + accessToken;
        redisTemplate.delete(redisKey);
        responseData.put("result", "success");
        return responseData;
    }

    @Override
    public JSONObject validTokenByUser(String tokenKey) {
        if (StringUtils.isEmpty(tokenKey)) {
            throw new BusinessException(BusinessExceptionEnum.BUSINESS_DATA_ERROR, BusinessExceptionEnum.BUSINESS_DATA_ERROR.getMsg(), new Throwable());
        }
        JSONObject responseData = new JSONObject();
        String key = TokenUtil.ACCESS_REDISKEY + TokenUtil.TOKEN_PREFIX + tokenKey;
        Long expire = redisTemplate.getExpire(key);
        if (null != expire && expire > 0) {
            //有效的token
            responseData.put("result", "success");
        } else {
            //无效的token
            redisTemplate.delete(key);
            throw new BusinessException(BusinessExceptionEnum.BUSINESS_DATA_ERROR, ErrorCodeEnum.USER_TOKEN_INVALID.getErrDesc(), new Throwable());
        }
        return responseData;
    }

    @Override
    public JSONObject getUserInfo(String tokenKey) {
        String key = TokenUtil.ACCESS_REDISKEY + TokenUtil.TOKEN_PREFIX + tokenKey;
        Map<Object, Object> jsonData = redisTemplate.opsForHash().entries(key);
        JSONObject object = new JSONObject();
        if (null != jsonData) {
            String jsonStr = JSONObject.toJSONString(jsonData);
            object = JSONObject.parseObject(jsonStr);
            object.remove(TokenUtil.COOKIE_TOKEN_PREFIX);
        }
        return object;
    }
}
