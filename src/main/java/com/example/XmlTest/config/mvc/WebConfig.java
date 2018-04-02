package com.example.XmlTest.config.mvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.io.File;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


/**
 * @author liuwei liuwei@flksec.com
 * @version 1.0
 * @date 18-4-2
 */
@Configuration
public class WebConfig {

    @Value("${jsplocaltion}")
    private String jsplocaltion;


    @Bean
    public EmbeddedServletContainerFactory embeddedServletContainerFactory() {
        ConfigurableEmbeddedServletContainer factory = new TomcatEmbeddedServletContainerFactory();
        factory.setDocumentRoot(new File("E:\\ideaWorkspace\\XmlTest\\src\\main\\resources"));
        return (EmbeddedServletContainerFactory) factory;
    }


    /**
     * 设置json数据中date类型转换格式
     *
     * @return
     */
    @Bean
    public MappingJackson2HttpMessageConverter getMappingJackson2HttpMessageConverter() {
        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        //设置日期格式
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleDateFormat smt = new SimpleDateFormat("yyyy-MM-dd");
        objectMapper.setDateFormat(smt);
        //设置null数据不进行序列化
        //objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        mappingJackson2HttpMessageConverter.setObjectMapper(objectMapper);
        //设置中文编码格式
        List<MediaType> list = new ArrayList<>();
        list.add(MediaType.APPLICATION_JSON_UTF8);
        mappingJackson2HttpMessageConverter.setSupportedMediaTypes(list);
        return mappingJackson2HttpMessageConverter;
    }

    /**
     * 设置字符串的编码格式
     *
     * @return
     */
    @Bean
    public StringHttpMessageConverter getStringHttpMessageConverter() {
        StringHttpMessageConverter messageConverter = new StringHttpMessageConverter(Charset.forName("UTF-8"));
        return messageConverter;
    }
}
