package cn.netinnet.ninzuul.authentication.cache;

import cn.netinnet.ninzuul.utils.RedisUtil;
import cn.netinnet.cloudcommon.constant.CacheConstant;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * \* @Author: Linjj
 * \* @Date: 2019/9/2 17:28
 * \* @Description: shiro使用redis进行缓存
 * \
 */
public class ShiroRedisCache<K, V> implements Cache<K, V> {

    // 默认缓存时间60分钟
    private static long DEFAULT_CACHE_EXPIRE = 60;

    /**
     * @Author Linjj
     * @Date 2019/9/3 9:37
     * @Description 获取缓存key
     */
    private String getKey(Object principal) {
        if (principal instanceof PrincipalCollection) {
            Object primaryPrincipal = ((PrincipalCollection) principal).getPrimaryPrincipal();
            // 存在shiro主体 且 主体的类型为UserInfo, 则可取得缓存Key
            if (primaryPrincipal != null) {
                Long userId = ((JSONObject) primaryPrincipal).getLong("userId");
                return RedisUtil.getKey(CacheConstant.R_SHIRO_CACHE, userId);
            }
        }
        return null;
    }

    /**
     * @Author Linjj
     * @Date 2019/9/3 9:37
     * @Description 获取缓存（授权内容-角色/权限）
     */
    @Override
    public Object get(Object principal) throws CacheException {
        String redisKey = this.getKey(principal);
        if (StringUtils.isBlank(redisKey)) {
            return null;
        }
        return RedisUtil.get(redisKey);
    }

    /**
     * @Author Linjj
     * @Date 2019/9/3 9:37
     * @Description 设置缓存（授权内容-角色/权限）
     */
    @Override
    public Object put(Object principal, Object authorizationInfo) throws CacheException {
        String redisKey = this.getKey(principal);
        if (StringUtils.isBlank(redisKey)) {
            return null;
        }
        // 设置Redis的Shiro缓存
        RedisUtil.set(redisKey, authorizationInfo, DEFAULT_CACHE_EXPIRE);
        return authorizationInfo;
    }

    /**
     * @Author Linjj
     * @Date 2019/9/3 9:37
     * @Description 移除缓存
     */
    @Override
    public Object remove(Object principal) throws CacheException {
        String redisKey = this.getKey(principal);
        if (StringUtils.isBlank(redisKey)) {
            return null;
        }
        RedisUtil.del(redisKey);
        return principal;
    }

    /**
     * @Author Linjj
     * @Date 2019/9/3 9:37
     * @Description 获取所有的key
     */
    @Override
    public Set keys() {
        Set<String> keys = RedisUtil.keys(RedisUtil.getKey(CacheConstant.R_SHIRO_CACHE, "*"));
        return keys;
    }

    /**
     * @Author Linjj
     * @Date 2019/9/3 9:37
     * @Description 清空所有缓存
     */
    @Override
    public void clear() throws CacheException {
        Set<String> keys = this.keys();
        for (String key : keys) {
            RedisUtil.del(key);
        }
    }

    /**
     * @Author Linjj
     * @Date 2019/9/3 9:37
     * @Description 缓存的个数
     */
    @Override
    public int size() {
        Set<String> keys = this.keys();
        return keys.size();
    }

    /**
     * @Author Linjj
     * @Date 2019/9/3 9:37
     * @Description 获取所有的value
     */
    @Override
    public Collection values() {
        Set<String> keys = this.keys();
        Set<Object> values = new HashSet<Object>(this.size());
        for (String key : keys) {
            values.add(RedisUtil.get(key));
        }
        return values;
    }
}