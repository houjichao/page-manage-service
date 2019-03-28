package com.study.page.base;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.Charset;
import java.security.Principal;
import java.util.*;

@ControllerAdvice
@Configuration
@Slf4j
public class GlobalResponseHandler extends WebMvcConfigurationSupport implements ResponseBodyAdvice<Object> {

    private static Logger LOGGER = LoggerFactory.getLogger(BusinessException.class);

    /**
     * 用fastjson替换jackson,解决循环引用的问题
     *
     * @param converters
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);
        //1、定义一个convert转换消息的对象
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        //2、添加fastjson的配置信息
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        SerializerFeature[] serializerFeatures = new SerializerFeature[]{
                //循环引用
                SerializerFeature.DisableCircularReferenceDetect,
        };
        fastJsonConfig.setSerializerFeatures(serializerFeatures);
        fastJsonConfig.setCharset(Charset.forName("UTF-8"));
        //3、在convert中添加配置信息
        fastConverter.setFastJsonConfig(fastJsonConfig);

        List<MediaType> supportedMediaTypes = new ArrayList<>();
        supportedMediaTypes.add(MediaType.APPLICATION_JSON);
        supportedMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        supportedMediaTypes.add(MediaType.APPLICATION_ATOM_XML);
        supportedMediaTypes.add(MediaType.APPLICATION_FORM_URLENCODED);
        supportedMediaTypes.add(MediaType.APPLICATION_OCTET_STREAM);
        supportedMediaTypes.add(MediaType.APPLICATION_PDF);
        supportedMediaTypes.add(MediaType.APPLICATION_RSS_XML);
        supportedMediaTypes.add(MediaType.APPLICATION_XHTML_XML);
        supportedMediaTypes.add(MediaType.APPLICATION_XML);
        supportedMediaTypes.add(MediaType.IMAGE_GIF);
        supportedMediaTypes.add(MediaType.IMAGE_JPEG);
        supportedMediaTypes.add(MediaType.IMAGE_PNG);
        supportedMediaTypes.add(MediaType.TEXT_EVENT_STREAM);
        supportedMediaTypes.add(MediaType.TEXT_HTML);
        supportedMediaTypes.add(MediaType.TEXT_MARKDOWN);
        supportedMediaTypes.add(MediaType.TEXT_PLAIN);
        supportedMediaTypes.add(MediaType.TEXT_XML);
        fastConverter.setSupportedMediaTypes(supportedMediaTypes);

        converters.add(0, fastConverter);
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
    }

    private ResponseEntityExceptionHandler exceptionHandler = new ResponseEntityExceptionHandler() {
    };

    private BaseWebRequest tempReq = new BaseWebRequest();

    @ExceptionHandler(Exception.class)
    @ResponseBody
    @RequestMapping("/dataerror")
    public Object handleException(Exception ex, HttpServletRequest req) {
        if (ex instanceof BusinessException) {
            ((BusinessException) ex).log();
            return ((BusinessException) ex).toBaseResponse();
        } else {
            ResponseEntity<Object> obs = null;
            try {
                obs = exceptionHandler.handleException(ex,tempReq);
            }catch (Exception e){
            }
            LOGGER.info("===================异常====================", ex);
            return new BaseResponse(obs.getStatusCodeValue(), ex.getMessage(), null);
        }
    }

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        if (returnType.getMethodAnnotation(UnpackedResponse.class) != null) {
            return false;
        }
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
                                  ServerHttpResponse response) {
        List<String> referers = request.getHeaders().get("Referer");
        if(referers!=null&&referers.size()>0&&referers.get(0).indexOf(BaseConstant.swaggerUrlSuffix)>=0){
            return body;
        }

        Object wrap;
        if (body instanceof BusinessException) {
            wrap = ((BusinessException) body).toBaseResponse();
        } else if (body instanceof Exception) {
            wrap = body;
        } else if (body instanceof BaseResponse) {
            wrap = body;
        } else {
            wrap = new BaseResponse(200, null, body);
        }
        return wrap;
    }

    class BaseWebRequest implements WebRequest {

        @Override
        public String getHeader(String headerName) {
            return null;
        }

        @Override
        public String[] getHeaderValues(String headerName) {
            return new String[0];
        }

        @Override
        public Iterator<String> getHeaderNames() {
            return null;
        }

        @Override
        public String getParameter(String paramName) {
            return null;
        }

        @Override
        public String[] getParameterValues(String paramName) {
            return new String[0];
        }

        @Override
        public Iterator<String> getParameterNames() {
            return null;
        }

        @Override
        public Map<String, String[]> getParameterMap() {
            return null;
        }

        @Override
        public Locale getLocale() {
            return null;
        }

        @Override
        public String getContextPath() {
            return null;
        }

        @Override
        public String getRemoteUser() {
            return null;
        }

        @Override
        public Principal getUserPrincipal() {
            return null;
        }

        @Override
        public boolean isUserInRole(String role) {
            return false;
        }

        @Override
        public boolean isSecure() {
            return false;
        }

        @Override
        public boolean checkNotModified(long lastModifiedTimestamp) {
            return false;
        }

        @Override
        public boolean checkNotModified(String etag) {
            return false;
        }

        @Override
        public boolean checkNotModified(String etag, long lastModifiedTimestamp) {
            return false;
        }

        @Override
        public String getDescription(boolean includeClientInfo) {
            return null;
        }

        @Override
        public Object getAttribute(String name, int scope) {
            return null;
        }

        @Override
        public void setAttribute(String name, Object value, int scope) {

        }

        @Override
        public void removeAttribute(String name, int scope) {

        }

        @Override
        public String[] getAttributeNames(int scope) {
            return new String[0];
        }

        @Override
        public void registerDestructionCallback(String name, Runnable callback, int scope) {

        }

        @Override
        public Object resolveReference(String key) {
            return null;
        }

        @Override
        public String getSessionId() {
            return null;
        }

        @Override
        public Object getSessionMutex() {
            return null;
        }
    }
}
