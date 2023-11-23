package vip.fairy.config;

import org.springframework.security.oauth2.common.util.RandomValueStringGenerator;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Component
public class CustomAuthorizationCodeServicesImpl implements AuthorizationCodeServices {
    protected final ConcurrentHashMap<String, OAuth2Authentication> authorizationCodeStore = new ConcurrentHashMap();

    private RandomValueStringGenerator generator = new RandomValueStringGenerator(10);
    @Override
    public String createAuthorizationCode(OAuth2Authentication oAuth2Authentication) {
        String code = this.generator.generate();
        authorizationCodeStore.put(code, oAuth2Authentication);
        return code;
    }
    @Override
    public OAuth2Authentication consumeAuthorizationCode(String code)  {
        return authorizationCodeStore.remove(code);
    }

}
