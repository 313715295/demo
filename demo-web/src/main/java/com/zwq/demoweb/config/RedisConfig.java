package com.zwq.demoweb.config;

import com.zwq.demoweb.serializer.FastJsonRedisSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.session.SessionRepository;
import org.springframework.session.data.redis.RedisOperationsSessionRepository;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * created by zwq on 2018/5/24
 */
@EnableRedisHttpSession
@Configuration
public class RedisConfig {



    /*
    当我们的数据存储到Redis的时候，我们的键（key）和值（value）都是通过Spring提供的Serializer序列化到数据库的。
    RedisTemplate默认使用的是JdkSerializationRedisSerializer，StringRedisTemplate默认使用的是StringRedisSerializer。
    这里用的是自定义的序列化类，本来想用protostuff，但是反序列化需要一个类模板，暂时怎么不知道设置object~所以未采用.
    设置序列化的对象需要实现RedisSerializer<T>接口
     */

    /**
     * 该方法主要是设置session调用的redisTemplate的序列化
     *
     * @param redisTemplate 由spring自动注入
     * @return
     */
    private static RedisTemplate<Object, Object> setFastJsonSerialize(RedisTemplate<Object, Object> redisTemplate) {
        FastJsonRedisSerializer<Object> fastJsonRedisSerializer = new FastJsonRedisSerializer<>(Object.class);
        //         设置值（value）的序列化采用FastJsonRedisSerializer。
        redisTemplate.setValueSerializer(fastJsonRedisSerializer);
        redisTemplate.setHashValueSerializer(fastJsonRedisSerializer);
        //         设置键（key）的序列化采用StringRedisSerializer。
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setDefaultSerializer(fastJsonRedisSerializer);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }



    /**
     * @param redisTemplate 由spring自动注入，然后经由setSerialize()方法设置序列化
     * @return sessionRepostitory还是需要再设置序列化，要不会报错
     */
    @Bean
    public SessionRepository sessionRepository(RedisTemplate<Object, Object> redisTemplate) {
        RedisOperationsSessionRepository sessionRepository = new RedisOperationsSessionRepository(setFastJsonSerialize(redisTemplate));
        FastJsonRedisSerializer<Object> fastJsonRedisSerializer = new FastJsonRedisSerializer<>(Object.class);
        sessionRepository.setDefaultSerializer(fastJsonRedisSerializer);
        sessionRepository.setDefaultMaxInactiveInterval(1800);
        return sessionRepository;
    }


}
