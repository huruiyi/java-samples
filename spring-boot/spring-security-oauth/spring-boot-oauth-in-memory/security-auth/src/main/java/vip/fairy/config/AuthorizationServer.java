package vip.fairy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import vip.fairy.service.SpringDataUserDetailsService;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServer extends AuthorizationServerConfigurerAdapter {

    private final TokenStore tokenStore;
    private final ClientDetailsService clientDetailsService;
    private final SpringDataUserDetailsService springDataUserDetailsService;
    private final AuthorizationCodeServices authorizationCodeServices;
    private final AuthenticationManager authenticationManager;

    public AuthorizationServer(
            TokenStore tokenStore,
            ClientDetailsService clientDetailsService,
            SpringDataUserDetailsService springDataUserDetailsService,
            AuthorizationCodeServices authorizationCodeServices,
            AuthenticationManager authenticationManager) {
        this.tokenStore = tokenStore;
        this.clientDetailsService = clientDetailsService;
        this.springDataUserDetailsService = springDataUserDetailsService;
        this.authorizationCodeServices = authorizationCodeServices;
        this.authenticationManager = authenticationManager;
    }

    @Bean
    public AuthorizationServerTokenServices tokenService() {
        DefaultTokenServices service = new DefaultTokenServices();
        service.setClientDetailsService(clientDetailsService);
        service.setSupportRefreshToken(true);
        service.setTokenStore(tokenStore);
        service.setAccessTokenValiditySeconds(7200); // 令牌默认有效期2小时
        service.setRefreshTokenValiditySeconds(259200); // 刷新令牌默认有效期3天
        return service;
    }

    // 用来配置令牌端点的安全约束
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
                // tokenkey这个endpoint当使用JwtToken且使用非对称加密时，资源服务用于获取公钥而开放的，这里指这个endpoint完全公开
                .tokenKeyAccess("permitAll()")
                // checkToken这个endpoint完全公开
                .checkTokenAccess("permitAll()")
                // 允许表单认证
                .allowFormAuthenticationForClients();
    }

    // 用来配置客户端详情服务（ClientDetailsService），客户端详情信息在
    //这里进行初始化，你能够把客户端详情信息写死在这里或者是通过数据库来存储调取详情信息
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // clients.withClientDetails(clientDetailsService);
        clients.inMemory()
                .withClient("client_1")//
                .secret(new BCryptPasswordEncoder().encode("secret_1"))
                .resourceIds("res1")
                .authorizedGrantTypes("authorization_code", "password", "client_credentials", "implicit", "refresh_token")
                .scopes("all")
                .autoApprove(false)
                .redirectUris("https://www.baidu.com");
    }


    /**
     * 默认： return new InMemoryAuthorizationCodeServices();
     */
    @Bean
    public AuthorizationCodeServices authorizationCodeServices() {
        return new CustomAuthorizationCodeServicesImpl();
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .userDetailsService(springDataUserDetailsService)
                .authenticationManager(authenticationManager)
                .authorizationCodeServices(authorizationCodeServices)
                .tokenServices(tokenService())
                .allowedTokenEndpointRequestMethods(HttpMethod.POST);
    }
}
