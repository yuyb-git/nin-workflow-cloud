package cn.netinnet.ninzuul.authentication;

import cn.netinnet.ninzuul.authentication.cache.ShiroRedisCacheManager;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;

/**
 * Shiro 配置类
 *
 * @author MrBird
 */
@Configuration
public class ShiroConfig {

    /**
     * @Description 配置shiro过滤器
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 设置 securityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        // 在 Shiro过滤器链上加入 JWTFilter
        LinkedHashMap<String, Filter> filters = new LinkedHashMap<>();
        filters.put("jwt", new JWTFilter());
        shiroFilterFactoryBean.setFilters(filters);

        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        // 免认证路径（一般用于资源）
        filterChainDefinitionMap.put("/favicon.ico", "anon");
        filterChainDefinitionMap.put("/cdn/**", "anon");
        filterChainDefinitionMap.put("/templates/**", "anon");
        filterChainDefinitionMap.put("/user/downloadData", "anon");
        filterChainDefinitionMap.put("/sysLog/debug", "anon");
        filterChainDefinitionMap.put("/druid/**", "anon");
        filterChainDefinitionMap.put("/login", "anon");
        //下面4个是对接dif、考试打包的放行接口
        filterChainDefinitionMap.put("/admin/dologin", "anon");
        filterChainDefinitionMap.put("/teacher/diflogin", "anon");
        filterChainDefinitionMap.put("/diflogin", "anon");
        filterChainDefinitionMap.put("/home", "anon");

        filterChainDefinitionMap.put("/api/**", "anon");
        filterChainDefinitionMap.put("/nin-zuul/user/**", "anon");
        filterChainDefinitionMap.put("/zbyapi/**", "anon");
        filterChainDefinitionMap.put("/sysLog/queryLoginLog", "anon");
        filterChainDefinitionMap.put("/assist/**", "anon");
        filterChainDefinitionMap.put("/", "anon");
        // 配置退出过滤器，其中具体的退出代码Shiro已经替我们实现了
        filterChainDefinitionMap.put("/logout", "anon");
        // 所有请求都要经过 jwt过滤器
        filterChainDefinitionMap.put("/**", "jwt");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    /**
     * @Description 设置SecurityManager，并设置自我实现的realm类
     */
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 配置 SecurityManager，并注入 shiroRealm（与安全数据交互，即用户信息/权限/角色等数据的来源）
        securityManager.setRealm(shiroRealm());
        // 关闭shiro自带的session
        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
        subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
        securityManager.setSubjectDAO(subjectDAO);
        // 设置自定义Cache缓存
        securityManager.setCacheManager(new ShiroRedisCacheManager());
        return securityManager;
    }

    /**
     * @Description 自我实现的realm类
     */
    @Bean
    public ShiroRealm shiroRealm() {
        // 配置 Realm，需自己实现
        return new ShiroRealm();
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }


}
