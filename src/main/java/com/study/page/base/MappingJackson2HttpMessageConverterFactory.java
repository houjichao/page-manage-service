package com.study.page.base;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.Charset;

@Slf4j
public class MappingJackson2HttpMessageConverterFactory {
    public MappingJackson2HttpMessageConverter init() {
        return new MappingJackson2HttpMessageConverter() {
            /**
             * 重写Jackson消息转换器的writeInternal方法
             * SpringMVC选定了具体的消息转换类型后,会调用具体类型的write方法,将Java对象转换后写入返回内容
             */
            @Override
            protected void writeInternal(Object object, Type type, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
                if (object instanceof String) {
                    logger.info("在MyResponseBodyAdvice进行转换时返回值变成String了,不能用原来选定消息转换器进行转换,直接使用StringHttpMessageConverter转换");
                    //StringHttpMessageConverter中就是用以下代码写的
                    Charset charset = this.getContentTypeCharset(outputMessage.getHeaders().getContentType());
                    StreamUtils.copy((String) object, charset, outputMessage.getBody());
                } else {
                    logger.info("返回值不是String类型,还是使用之前选择的转换器进行消息转换");
                    super.writeInternal(object, type, outputMessage);
                }
            }

            private Charset getContentTypeCharset(MediaType contentType) {
                return contentType != null && contentType.getCharset() != null ? contentType.getCharset() : this.getDefaultCharset();
            }
        };
    }
}
