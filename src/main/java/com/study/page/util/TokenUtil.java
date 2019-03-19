package com.study.page.util;

import com.google.common.collect.Maps;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.study.page.enums.TokenStateEnum;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.Map;

@Slf4j
public class TokenUtil {

    private static Logger LOGGER = LoggerFactory.getLogger(TokenUtil.class);

    /**
     * token和用户信息进行映射的redisKey
     */
    public static final String ACCESS_REDISKEY = "user_tokens:";

    public static final String TOKEN_PREFIX = "token#";

    public static final String COOKIE_TOKEN_PREFIX = "tokenId";


    /**
     * 30分钟
     */
    public static final long TOKEN_EXPIRED_TIME = 1000*60*30;
    /**
     * 秘钥
     */
    private static final byte[] SECRET="YANGZHIMING".getBytes();

    /**
     * 初始化head部分的数据为
     * {
     *         "alg":"HS256",
     *         "type":"JWT"
     * }
     */
    private static final JWSHeader HEADER =new JWSHeader(JWSAlgorithm.HS256, JOSEObjectType.JWT, null, null, null, null, null, null, null, null, null, null, null);

    /**
     * 生成token，该方法只在用户登录成功后调用
     *
     * @param userId，可以存储用户id，token生成时间，token过期时间等自定义字段
     * @return token字符串,若失败则返回null
     */
    public static String createToken(String userId) {
        String tokenString=null;

        Map<String , Object> payload= Maps.newHashMap();
        Date date=new Date();
        //用户id
        payload.put("uid", userId);
        //生成时间
        payload.put("iat", date.getTime());
        //过期时间10分钟
        payload.put("ext",date.getTime()+TOKEN_EXPIRED_TIME);

        // 创建一个 JWS object
        JWSObject jwsObject = new JWSObject(HEADER, new Payload(new JSONObject(payload)));
        try {
            // 将jwsObject 进行HMAC签名
            jwsObject.sign(new MACSigner(SECRET));
            tokenString=jwsObject.serialize();
        } catch (JOSEException e) {
            System.err.println("签名失败:" + e.getMessage());
            e.printStackTrace();
        }
        return tokenString;
    }



    /**
     * 校验token是否合法，返回Map集合,集合中主要包含    state状态码   data鉴权成功后从token中提取的数据
     * 该方法在过滤器中调用，每次请求API时都校验
     * @param token
     * @return  Map<String, Object>
     */
    public static Map<String, Object> validToken(String token) {
        Map<String, Object> resultMap = Maps.newHashMap();
        try {
            JWSObject jwsObject = JWSObject.parse(token);
            Payload payload = jwsObject.getPayload();
            JWSVerifier verifier = new MACVerifier(SECRET);

            if (jwsObject.verify(verifier)) {
                JSONObject jsonOBj = payload.toJSONObject();
                // token校验成功（此时没有校验是否过期）
                resultMap.put("state", TokenStateEnum.VALID.toString());
                // 若payload包含ext字段，则校验是否过期
                if (jsonOBj.containsKey("ext")) {
                    long extTime = Long.valueOf(jsonOBj.get("ext").toString());
                    long curTime = System.currentTimeMillis();
                    // 过期了
                    if (curTime > extTime) {
                        resultMap.clear();
                        resultMap.put("state", TokenStateEnum.EXPIRED.toString());
                    }
                }
                resultMap.put("data", jsonOBj);

            } else {
                // 校验失败
                resultMap.put("state", TokenStateEnum.INVALID.toString());
            }

        } catch (Exception e) {
            // token格式不合法导致的异常
            LOGGER.error("validToken exception:{},token is:{}",e,token);
            resultMap.clear();
            resultMap.put("state", TokenStateEnum.INVALID.toString());

        }
        return resultMap;
    }
    public static void main(String[] args) {
        String token=TokenUtil.createToken("3620d7d6-7f26-46fa-8288-8610ba9cad74");
        System.out.println(token);
        String tstr = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHQiOjE1MzQxNDM0NDQxNTksInVpZCI6IjM2MjBkN2Q2LTdmMjYtNDZmYS04Mjg4LTg2MTBiYTljYWQ3NCIsImlhdCI6MTUzNDE0Mjg0NDE1OX0.35xHD4THG3Ya3_VkKtoo0KJJOVcTRCjkt0AJwgtzL6I";

        Map<String, Object>   map =  TokenUtil.validToken(tstr);
        System.out.println(map.get("state"));
    }
}
