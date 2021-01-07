package cn.netinnet.ninzuul.authentication.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;

/**
 \* @Author: Linjj
 \* @Date: 2019/9/3 9:58
 \* @Description: 设置自定义的缓存
 \*/
public class ShiroRedisCacheManager implements CacheManager {

    @Override
    public <K, V> Cache<K, V> getCache(String s) throws CacheException {
        return new ShiroRedisCache<K, V>();
    }
}