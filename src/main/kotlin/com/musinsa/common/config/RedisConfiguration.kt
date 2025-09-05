package com.musinsa.common.config

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.cache.annotation.CachingConfigurer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.HashOperations
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.StringRedisSerializer

@Configuration
class RedisConfiguration(
    @Value("\${redis.musinsa.host}") private val redisHost: String,
    @Value("\${redis.musinsa.port}") private val redisPort: Int,
    @Value("\${redis.musinsa.password}") private val password: String,
) : CachingConfigurer {

    @Primary
    @Bean
    fun redisConnectionFactory(): RedisConnectionFactory {
        val config = RedisStandaloneConfiguration(redisHost, redisPort)
        config.setPassword(password)
        return LettuceConnectionFactory(config)
    }

    @Primary
    @Bean
    fun <T> redisTemplate(@Qualifier("redisConnectionFactory") connectionFactory: RedisConnectionFactory): RedisTemplate<String, T> {
        val template = RedisTemplate<String, T>()
        template.setConnectionFactory(connectionFactory)
        template.keySerializer = StringRedisSerializer()
        template.valueSerializer = GenericJackson2JsonRedisSerializer()
        template.hashKeySerializer = StringRedisSerializer()
        template.hashValueSerializer = GenericJackson2JsonRedisSerializer()
        return template
    }

    @Bean
    fun opsForHash(redisTemplate: RedisTemplate<String, String>): HashOperations<String, String, String> {
        return redisTemplate.opsForHash()
    }
}
