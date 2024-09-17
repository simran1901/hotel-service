package com.hrs.hotel_service.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.lang.NonNullApi;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.concurrent.Executor;

@Configuration
@EnableWebMvc
@EnableCaching
@Component
@ComponentScan(basePackages = "com.hrs")
public class AppConfig implements WebMvcConfigurer {

    // timeout in ms to establish a connection to a upstream server. Actual
    // value: 10s
    @Value("${http.connection.timeout:10000}")
    private int CONNECTION_TIMEOUT ;

    // timeout in ms to read next packet from socket(connection). Actual value:
    // 10s
    @Value("${http.socket.timeout:10000}")
    private int SOCKET_TIMEOUT;

    @Value("${allowed.origins:*}")
    String[] allowedOrigins;

    @Autowired
    private Environment environment;

    @Value("${thread.max.pool.size:150}")
    int maxPoolSize;

    @Value("${thread.core.pool.size:10}")
    int corePoolSize;

    @Value("${read.timeout.millisec:10000}")
    private int READ_TIMEOUT;

    @Bean
    public RestTemplate restTemplate() {
        SimpleClientHttpRequestFactory clientHttpRequestFactory = new SimpleClientHttpRequestFactory();
        clientHttpRequestFactory.setConnectTimeout(CONNECTION_TIMEOUT);
        clientHttpRequestFactory.setReadTimeout(READ_TIMEOUT);
        return new RestTemplate(clientHttpRequestFactory);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        WebMvcConfigurer.super.addCorsMappings(registry);

        if (allowedOrigins.length == 1) {
            registry.addMapping("/**").allowedOrigins(allowedOrigins[0]).allowedHeaders("*").allowedMethods("GET",
                    "POST", "PUT", "PATCH", "DELETE", "OPTIONS", "HEAD");
        } else if (allowedOrigins.length == 2) {
            registry.addMapping("/**").allowedOrigins(allowedOrigins[0], allowedOrigins[1]).allowedHeaders("*")
                    .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS", "HEAD");
        } else if (allowedOrigins.length == 3) {
            registry.addMapping("/**").allowedOrigins(allowedOrigins[0], allowedOrigins[1], allowedOrigins[2])
                    .allowedHeaders("*").allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS", "HEAD");
        }

    }

    @Bean
    public Executor syncCallExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(10000);
        executor.setThreadNamePrefix("SyncExecutor-");
        executor.initialize();
        return executor;
    }

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper;
    }

    @Bean(name = "asyncExecutor")
    public Executor asyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(9000);
        executor.setThreadNamePrefix("AsycExecutor-");
        executor.initialize();
        return executor;
    }

    @Bean(name = "asyncExecutorProcessEvent")
    public Executor asyncExecutorProcessEvent() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(2);
        executor.setQueueCapacity(9000);
        executor.setThreadNamePrefix("asyncExecutorProcessEvent-");
        executor.initialize();
        return executor;
    }
}