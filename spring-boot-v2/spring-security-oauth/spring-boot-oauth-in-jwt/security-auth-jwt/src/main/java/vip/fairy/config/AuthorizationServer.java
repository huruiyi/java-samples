package vip.fairy.config;

import vip.fairy.service.SpringDataUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.Collections;

@Configuration
// 配置授权服务器
@EnableAuthorizationServer
public class AuthorizationServer extends AuthorizationServerConfigurerAdapter {

  private final TokenStore tokenStore;

  private final JwtAccessTokenConverter accessTokenConverter;

  private final ClientDetailsService clientDetailsService;

  private final AuthorizationCodeServices authorizationCodeServices;

  private final AuthenticationManager authenticationManager;

  private final SpringDataUserDetailsService springDataUserDetailsService;

  public AuthorizationServer(
      TokenStore tokenStore,
      JwtAccessTokenConverter accessTokenConverter,
      ClientDetailsService clientDetailsService,
      AuthorizationCodeServices authorizationCodeServices,
      AuthenticationManager authenticationManager,
      SpringDataUserDetailsService springDataUserDetailsService) {
    this.tokenStore = tokenStore;
    this.accessTokenConverter = accessTokenConverter;
    this.clientDetailsService = clientDetailsService;
    this.authorizationCodeServices = authorizationCodeServices;
    this.authenticationManager = authenticationManager;
    this.springDataUserDetailsService = springDataUserDetailsService;
  }

  /**
   * 默认： return new InMemoryAuthorizationCodeServices();
   */
  @Bean
  public AuthorizationCodeServices authorizationCodeServices() {
    return new CustomAuthorizationCodeServicesImpl();
  }

  @Bean
  public AuthorizationServerTokenServices tokenService() {
    DefaultTokenServices service = new DefaultTokenServices();
    service.setClientDetailsService(clientDetailsService);
    service.setSupportRefreshToken(true);
    service.setTokenStore(tokenStore);
    TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
    tokenEnhancerChain.setTokenEnhancers(Collections.singletonList(accessTokenConverter));
    service.setTokenEnhancer(tokenEnhancerChain);
    service.setAccessTokenValiditySeconds(7200); // 令牌默认有效期2小时
    service.setRefreshTokenValiditySeconds(259200); // 刷新令牌默认有效期3天
    return service;
  }

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
        .withClient("client_1")
        .secret(new BCryptPasswordEncoder().encode("secret_1"))
        .resourceIds("res1")
        .authorizedGrantTypes("authorization_code", "password", "client_credentials", "implicit", "refresh_token")
        .scopes("all")
        .autoApprove(false)
        //加上验证回调地址
        .redirectUris("https://www.baidu.com");
  }

  @Override
  public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
    endpoints
        .userDetailsService(springDataUserDetailsService)
        .authenticationManager(authenticationManager)
        .authorizationCodeServices(authorizationCodeServices)
        .tokenServices(tokenService())
        .allowedTokenEndpointRequestMethods(HttpMethod.POST);
  }
}
