package com.rt.base.framework.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * restTemplate 配置
 *
 * @author Mark initPlatform
 */
@Configuration
public class RestTemplateConfig {
    @Autowired
    private RestTemplateBuilder restTemplateBuilder;
    @Bean
    public RestTemplate restTemplate(){
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(180000);
        requestFactory.setReadTimeout(180000);
        RestTemplate restTemplate = new RestTemplate(requestFactory);
        return restTemplate;
//        return restTemplateBuilder.build();
    }
}
