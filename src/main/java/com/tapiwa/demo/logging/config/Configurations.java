package com.tapiwa.demo.logging.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class Configurations {

    @Bean
    public SimpleMessageConverter simpleMessageConverter() {

        SimpleMessageConverter simpleMessageConverter = new SimpleMessageConverter();
        simpleMessageConverter.setAllowedListPatterns(List.of("com.tapiwa.demo.logging.dto.*", "java.util.*"));
        return simpleMessageConverter;
    }
    @Bean
    public Queue queue() {
        return new Queue("petQueue", false);
    }
}
