package com.config;

import com.custom.CustomTokenEnhancer;
import com.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    RedisConnectionFactory redisConnectionFactory;

    /**
     * InMemoryTokenStore 直接使用
     */
//    @Autowired
//    private TokenStore inMemoryTokenStore;

    /**
     * JwtTokenStore
     */
//    @Bean
//    public TokenStore tokenStore() {
//        return new JwtTokenStore(jwtAccessTokenConverter());
//    }
//
//    @Bean
//    public JwtAccessTokenConverter jwtAccessTokenConverter() {
//        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
//        converter.setSigningKey("key");
//        return converter;
//    }

    /**
     * RedisTokenStore
     */
    @Bean
    public TokenStore tokenStore() {
        return new RedisTokenStore(redisConnectionFactory);
    }

    /**
     * 自定义生成令牌
     */
    @Bean
    public TokenEnhancer tokenEnhancer() {
        return new CustomTokenEnhancer();
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory().
                withClient("client_credential").
//                resourceIds(Constant.DEMO_RESOURCE_ID).
                authorizedGrantTypes("client_credentials", "refresh_token").
                scopes("select").
                authorities("client").
                secret("123456").
                and().
                withClient("client_password").
//                resourceIds(Constant.DEMO_RESOURCE_ID).
                authorizedGrantTypes("password", "refresh_token").
                scopes("select").
                authorities("client").
                secret("123456");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        /**
         * JwtTokenStore
         */
//        endpoints.tokenStore(tokenStore()).
//                accessTokenConverter(jwtAccessTokenConverter()).
//                authenticationManager(authenticationManager);

        /**
         * RedisTokenStore
         */
        endpoints.tokenStore(tokenStore()).
                authenticationManager(authenticationManager);

        /**
         * InMemoryTokenStore
         */
//        endpoints.tokenStore(inMemoryTokenStore).
//                authenticationManager(authenticationManager);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        // 允许表单认证
        security.allowFormAuthenticationForClients();
    }
}
