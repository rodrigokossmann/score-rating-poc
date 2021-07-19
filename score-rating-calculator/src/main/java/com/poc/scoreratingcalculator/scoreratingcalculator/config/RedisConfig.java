package com.poc.scoreratingcalculator.scoreratingcalculator.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class RedisConfig {

  @Bean
  public RedisTemplate<String, Double> redisTemplate(RedisConnectionFactory connectionFactory) {
    RedisTemplate<String, Double> template = new RedisTemplate<>();
    template.setConnectionFactory(connectionFactory);
    // Add some specific configuration here. Key serializers, etc.
    return template;
  }

}
