package com.example.application.config;

/**
 * @ClassName RedisCacheConfig
 * @Author zhaofu
 * @Date 2020/12/28
 * @Version V1.0
 **/
//@Configuration
////开启缓存
//@EnableCaching
public class RedisCacheConfig  /*extends CachingConfigurerSupport */{
//    @Bean
//    public CacheManager cacheManager(RedisConnectionFactory factory){
//        //创建FastJson对象，用于序列化
//        GenericFastJsonRedisSerializer fastJsonRedisSerializer = new GenericFastJsonRedisSerializer();
//        //创建一个RedisCache的配置对象
//        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig();
//        //过期时间设置为一天
//        //对存的的key,value进行序列化
//        config = config.entryTtl(Duration.ofDays(1))
//                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
//                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(fastJsonRedisSerializer))
//                .disableCachingNullValues();
//        //自己定义要缓存的内存空间名字
//        Set<String> cacheNames = new HashSet<>();
//        cacheNames.add("user");
//        //对缓存空间设置单独的配置，在这个可根据业务，如果数据很久才会变一次时间就可设成永久
//        Map<String,RedisCacheConfiguration> configMap = new HashMap(16);
//        configMap.put("user",config);
//        //创建RedisCacheManage对象，将上面的配置导入
//        RedisCacheManager redisCacheManager = RedisCacheManager.builder(factory)
//                .initialCacheNames(cacheNames)
//                .withInitialCacheConfigurations(configMap)
//                .build();
//        return redisCacheManager;
//    }
}
