package com.study.page.util;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;

/**
 * Cookie工具类
 * @author hjc
 */
@Slf4j
public class CookieUtil {

    private static Logger LOGGER = LoggerFactory.getLogger(CookieUtil.class);


    /**
     * 添加新的Cookie
     *
     * @param name
     */
    public static void setNewCookie(String name, String path, String value, HttpServletResponse response) {
        try {
            Cookie cookie = new Cookie(name, value);
            if(path != null && path.length() > 0 ){
                cookie.setPath(path);
            }else {
                cookie.setPath("/");
            }
            //设置24小时生存期，当设置为负值时，则为浏览器进程Cookie(内存中保存)，关闭浏览器就失效。
            cookie.setMaxAge(60 * 60 * 24 * 7);
            response.addCookie(cookie);
        } catch (RuntimeException e) {
            LOGGER.error("setNewCookie exception:{}",e);
        }
    }

    /**
     * 获取Cookie值
     *
     * @param name
     * @param request
     * @return
     */
    public static String getCookieValue(String name, HttpServletRequest request) {
        String value = "";
        try {
            Cookie[] cookies = request.getCookies();
            for (int i = 0; i < cookies.length; i++) {
                if (cookies[i].getName().equalsIgnoreCase(name)) {
                    value = cookies[i].getValue();
                }
            }
        } catch (RuntimeException e) {
        }
        return value;
    }

    /**
     * 删除cookie中对应数值
     *
     * @param name
     * @param request
     * @param response
     */
    public static void deleteCookies(String name, HttpServletRequest request, HttpServletResponse response) {
        try {
            Cookie[] cookies = request.getCookies();
            for (int i = 0; i < cookies.length; i++) {
                if (cookies[i].getName().equalsIgnoreCase(name)) {
                    cookies[i].setValue(null);
                    //设置为0为立即删除该Cookie
                    cookies[i].setMaxAge(0);
                    response.addCookie(cookies[i]);
                }
            }
        } catch (RuntimeException e) {
            LOGGER.error("delete cookie exception:{}",e);
        }
    }


    /**
     * 增加规定cookie中的数值
     *
     * @param name
     * @param value
     * @param request
     * @param response
     */
    public static void addnewCookieValue(String name, BigDecimal value, HttpServletRequest request, HttpServletResponse response) {
        try {
            Cookie[] cookies = request.getCookies();
            for (int i = 0; i < cookies.length; i++) {

                if (cookies[i].getName().equalsIgnoreCase(name)) {
                    cookies[i].setValue(new BigDecimal(cookies[i].getValue().trim()).add(value).toString());
                    response.addCookie(cookies[i]);
                }
            }
        } catch (RuntimeException e) {
            LOGGER.error("add new CookieValue exception:{}",e);
        }
    }

    /**
     * 减去规定cookie中的数值
     *
     * @param name
     * @param value
     * @param request
     * @param response
     */
    public static void subCookieValue(String name, BigDecimal value, HttpServletRequest request, HttpServletResponse response) {
        try {
            Cookie[] cookies = request.getCookies();
            for (int i = 0; i < cookies.length; i++) {
                if (cookies[i].getName().equalsIgnoreCase(name)) {
                    cookies[i].setValue(new BigDecimal(cookies[i].getValue()).subtract(value).toString());
                    response.addCookie(cookies[i]);
                }
            }
        } catch (RuntimeException e) {
        }
    }
}
