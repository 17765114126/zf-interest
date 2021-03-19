package com.example.springboot.config.Shiro.security;

import com.example.springboot.config.Shiro.exception.MyExceptionHandler;
import com.example.springboot.config.Shiro.filter.SecurityFilter;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.servlet.Filter;
import java.time.Duration;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration//容器启动初始化...
public class ShiroConfig {


//    @Value("${spring.redis.host}:${spring.redis.port}")
    private String host = "39.101.184.47:6379";
//    @Value("${spring.redis.password}")
    private String password;

    @Bean
    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String, Filter> filters = shiroFilterFactoryBean.getFilters();
        filters.put("securityFilter",new SecurityFilter());
        /**
         * anon：无需认证就可访问
         * authc：必须认证才可访问
         * user：必须拥有 记住我 功能才能访问
         * role：拥有某个角色才能访问
         * */
        Map<String, String> filterMap = new LinkedHashMap<>();
        filterMap.put("/hello", "anon");
        filterMap.put(" /password/*", "authc");
        filterMap.put("/logout", "logout");
        filterMap.put("/**", "securityFilter");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        //登陆页面
        shiroFilterFactoryBean.setLoginUrl("/login");
        //未授权页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/notlogin");
        return shiroFilterFactoryBean;
    }

    /**
     *
     * @return
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName(SecurityConstants.HASH_ALGORITHMNAME);
//        hashedCredentialsMatcher.setHashIterations(SecurityConstants.HASH_ITERATIONS);
        return hashedCredentialsMatcher;
    }

    @Bean
    public MyShiroRealm myShiroRealm() {
        MyShiroRealm myShiroRealm = new MyShiroRealm();
        //凭证匹配器（密码匹配）
        myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return myShiroRealm;
    }

    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联ShiroRealm
        securityManager.setRealm(myShiroRealm());
        // 自定义session管理 使用redis
        securityManager.setSessionManager(sessionManager());
        // 自定义缓存实现 使用redis
        securityManager.setCacheManager(cacheManager());
        return securityManager;
    }

    //自定义sessionManager
    @Bean
    public SessionManager sessionManager() {
        MySessionManager mySessionManager = new MySessionManager();
        mySessionManager.setSessionDAO(redisSessionDAO());
        mySessionManager.setGlobalSessionTimeout(SecurityConstants.SESSION_TIMEOUT);
        return mySessionManager;
    }

    /**
     * 配置shiro redisManager
     * <p>
     * 使用的是shiro-redis开源插件
     *
     * @return
     */
    public RedisManager redisManager() {
        RedisManager redisManager = new RedisManager();
        redisManager.setHost(host);
        //redisManager.setTimeout(timeout);
        redisManager.setPassword(password);
        return redisManager;
    }

    /**
     * cacheManager 缓存 redis实现
     * <p>
     * 使用的是shiro-redis开源插件
     *
     * @return
     */
    @Bean
    public RedisCacheManager cacheManager() {
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        return redisCacheManager;
    }

    /**
     * RedisSessionDAO shiro sessionDao层的实现 通过redis
     * <p>
     * 使用的是shiro-redis开源插件
     */
    @Bean
    public RedisSessionDAO redisSessionDAO() {
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager());
        return redisSessionDAO;
    }

    /**
     * 开启shiro aop注解支持.
     * 使用代理方式;所以需要开启代码支持;
     *
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    @Bean
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    /**
     * 注册全局异常处理
     * @return
     */
    @Bean(name = "exceptionHandler")
    public HandlerExceptionResolver handlerExceptionResolver() {
        return new MyExceptionHandler();
    }

//    @Bean
//    public CacheManager cacheManager(RedisTemplate<String, Object> template) {
//        RedisCacheConfiguration defaultCacheConfiguration =
//                RedisCacheConfiguration
//                        .defaultCacheConfig()
//                        // 设置key为String
//                        .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(template.getStringSerializer()))
//                        // 设置value 为自动转Json的Object
//                        .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(template.getValueSerializer()))
//                        // 不缓存null
//                        .disableCachingNullValues()
//                        // 缓存数据保存1小时
//                        .entryTtl(Duration.ofHours(1));
//        RedisCacheManager redisCacheManager =
//                RedisCacheManagerBuilder
//                        // Redis 连接工厂
//                        .fromConnectionFactory(template.getConnectionFactory())
//                        // 缓存配置
//                        .cacheDefaults(defaultCacheConfiguration)
//                        // 配置同步修改或删除 put/evict
//                        .transactionAware()
//                        .build();
//        return redisCacheManager;
//    }
}
