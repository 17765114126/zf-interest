package com.example.springboot.service.other;

/**
 * @ClassName RedisCacheUserService
 * @Author zhaofu
 * @Date 2020/12/28
 * @Version V1.0
 **/

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.springboot.mapper.CmsUserMapper;
import com.example.springboot.model.entity.CmsUser;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * https://www.cnblogs.com/caizhaokai/p/11037610.html
 * <p>
 * 使用Redis缓存数据库数据
 * 　　Redis有很多使用场景，一个demo就是缓存数据库的数据。
 * Redis作为一个内存数据库，存取数据的速度比传统的数据库快得多。
 * 使用Redis缓存数据库数据，可以减轻系统对数据库的访问压力，及加快查询效率等好处。
 * 下面讲解如何使用 SpringBoot + Redis来缓存数据库数据(这里数据库使用MySql)。
 * <p>
 * 　　4.1 配置Redis作为Spring的缓存管理
 * 　　Spring支持多种缓存技术：RedisCacheManager、EhCacheCacheManager、GuavaCacheManager等，使用之前需要配置一个CacheManager的Bean。
 * 配置好之后使用三个注解来缓存数据：@Cacheable，@CachePut 和 @CacheEvict。
 * 这三个注解可以加Service层或Dao层的类名上或方法上(建议加在Service层的方法上)，加上类上表示所有方法支持该注解的缓存；
 * 三注解需要指定Key，以返回值作为value操作缓存服务。所以，如果加在Dao层，当新增1行数据时，返回数字1，会将1缓存到Redis，而不是缓存新增的数据。
 * <p>
 * 　　RedisCacheManager的配置：com.example.springboot.config.Shiro.security.RedisCacheManager
 */

/**
 * @author
 * 指定默认缓存区
 * 缓存区：key的前缀，与指定的key构成redis的key，如 user::10001
 */
@CacheConfig(cacheNames = "user")
@Service
public class RedisCacheUserService {

    @Resource
    private CmsUserMapper cmsUserMapper;

    /**
     * @Cacheable 缓存有数据时，从缓存获取；没有数据时，执行方法，并将返回值保存到缓存中
     * @Cacheable 一般在查询中使用
     * 1) cacheNames 指定缓存区，没有配置使用@CacheConfig指定的缓存区
     * 2) key 指定缓存区的key
     * 3) 注解的值使用SpEL表达式
     * eq ==
     * lt <
     * le <=
     * gt >
     * ge >=
     */
    @Cacheable(cacheNames = "tbl_user", key = "#id")
    public CmsUser selectUserById(String id) {
        return cmsUserMapper.selectById(id);
    }

    @Cacheable(key = "'list'")
    public List<CmsUser> selectUser() {
        return cmsUserMapper.selectList(new LambdaQueryWrapper<>());
    }

    /**
     * condition 满足条件缓存数据
     */
    @Cacheable(key = "#id", condition = "#number ge 20") // >= 20
    public CmsUser selectUserByIdWithCondition(String id, int number) {
        return cmsUserMapper.selectById(id);
    }

    /**
     * unless 满足条件时否决缓存数据
     */
    @Cacheable(key = "#id", unless = "#number lt 20") // < 20
    public CmsUser selectUserByIdWithUnless(String id, int number) {
        return cmsUserMapper.selectById(id);
    }

    /**
     　　　* @CachePut 一定会执行方法，并将返回值保存到缓存中
     * @CachePut 一般在新增和修改中使用
     */
    @CachePut(key = "#user.id")
    public CmsUser insertUser(CmsUser user) {
        cmsUserMapper.insert(user);
        return user;
    }

    @CachePut(key = "#user.id", condition = "#user.age ge 20")
    public CmsUser insertUserWithCondition(CmsUser user) {
        cmsUserMapper.insert(user);
        return user;
    }

    @CachePut(key = "#user.id")
    public CmsUser updateUser(CmsUser user) {
        cmsUserMapper.updateById(user);
        return user;
    }

    /**
     * 根据key删除缓存区中的数据
     */
    @CacheEvict(key = "#id")
    public void deleteUserById(String id) {
        cmsUserMapper.deleteById(id);
    }

    /**
     * allEntries = true ：删除整个缓存区的所有值，此时指定的key无效
     * beforeInvocation = true ：默认false，表示调用方法之后删除缓存数据；true时，在调用之前删除缓存数据(如方法出现异常)
     */
    @CacheEvict(key = "#id", allEntries = true)
    public void deleteUserByIdAndCleanCache(String id) {
        cmsUserMapper.deleteById(id);
    }

}
