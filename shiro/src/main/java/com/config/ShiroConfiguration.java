package com.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.shiro.realm.UserRealm;
import net.sf.ehcache.CacheManager;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfiguration {
    private static final Logger LOGGER = LoggerFactory.getLogger(ShiroConfiguration.class);

    /**
     * shiro的web过滤器Factory
     */
    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        LOGGER.info("注入Shiro的Web过滤器-->shiroFilter", ShiroFilterFactoryBean.class);

        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        // shiro的核心安全接口
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        shiroFilterFactoryBean.setLoginUrl("/login");
        shiroFilterFactoryBean.setSuccessUrl("/index");
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");

        /**
         * 过滤器链定义 从上向下顺序执行 一般/**放最下边
         * anon: 所有url都可以匿名访问 authc: 所有url都必须认证通过才可以访问
         *
         */
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        filterChainDefinitionMap.put("/logout", "logout"); // 配置退出过滤器 其中的退出代码shiro已经做好了
        filterChainDefinitionMap.put("/a", "anon");
        filterChainDefinitionMap.put("/b/*", "anon");
        filterChainDefinitionMap.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        return shiroFilterFactoryBean;
    }

    /**
     * shiro缓存管理器
     * 需要加入到安全管理器SecurityManager中
     */
    @Bean
    public EhCacheManager ehCacheManager() {
        LOGGER.info("注入Shiro的缓存管理器-->ehCacheManager", EhCacheManager.class);
        EhCacheManager ehCacheCacheManager = new EhCacheManager();
        ehCacheCacheManager.setCacheManager(cacheManager());
        return ehCacheCacheManager;
    }

    @Bean
    public CacheManager cacheManager() {
        return CacheManager.create();
    }

    @Bean
    public SecurityManager securityManager(UserRealm userRealm) {
        LOGGER.info("注入Shiro的Web过滤器-->securityManager", ShiroFilterFactoryBean.class);
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 设置realm，用于获取认证凭证
        securityManager.setRealm(userRealm);
        // 注入缓存管理器
        securityManager.setCacheManager(ehCacheManager());
        return securityManager;
    }

    /**
     * Shiro Realm 继承自AuthorizingRealm的自定义Realm,即指定Shiro验证用户登录的类为自定义的
     */
    @Bean
    public UserRealm userRealm() {
        UserRealm userRealm = new UserRealm();
        userRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        userRealm.setCachingEnabled(true);

        // 启用身份验证缓存 缓存AuthenticationInfo信息
//        userRealm.setAuthenticationCachingEnabled(true);
//        userRealm.setAuthenticationCacheName("authenticationCache");
//
//        // 启用授权缓存 缓存AuthorizationInfo信息，默认false
//        userRealm.setAuthorizationCachingEnabled(true);
//        userRealm.setAuthorizationCacheName("authorizationCache");
        return userRealm;
    }

    /**
     * 凭证匹配器
     */
    @Bean(name = "credentialsMatcher")
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        LOGGER.info("注入Shiro的Web过滤器-->credentialsMatcher", HashedCredentialsMatcher.class);
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        matcher.setHashAlgorithmName("md5"); // 散列算法 使用md5
        matcher.setHashIterations(2); // 散列的次数
        matcher.setStoredCredentialsHexEncoded(true); // storedCredentialsHexEncoded默认为true 此时密码加密用的是Hex编码；false时用Base64编码
        return matcher;
    }

    /**
     * shiro声明周期处理器
     */
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * 开启shiro的注解
     */
    @Bean
    @DependsOn({"lifecycleBeanPostProcessor"})
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }

    /**
     * 添加ShiroDialect 为了在thymeleaf里使用shiro的标签的bean
     */
    @Bean(name = "shiroDialect")
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }
}
