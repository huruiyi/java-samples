package com.savindu.user_management.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimNames;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;


public class JwtAuthConverter implements Converter<Jwt, AbstractAuthenticationToken> {

  private final JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();

  @Value("${jwt.auth.converter.resource-id}")
  private String resourceId;


  @Override
  public AbstractAuthenticationToken convert(Jwt jwt) {
    Stream<GrantedAuthority> authorityStream = jwtGrantedAuthoritiesConverter.convert(jwt).stream();
    Collection<GrantedAuthority> authorities = Stream.concat(authorityStream, extractResourceRoles(jwt).stream()).collect(Collectors.toSet());
    return new JwtAuthenticationToken(jwt, authorities, getPrincipalClaimName(jwt));
  }

  private String getPrincipalClaimName(Jwt jwt) {
    String claimName = JwtClaimNames.SUB;
    return jwt.getClaim(claimName);
  }

  private Collection<? extends GrantedAuthority> extractResourceRoles(Jwt jwt) {
    Map<String, Object> realmAccess = jwt.getClaim("realm_access");
    Map<String, Object> resourceAccess = jwt.getClaim("resource_access");

    Collection<String> allRoles = new ArrayList<>();
    Collection<String> resourceRoles;
    Collection<String> realmRoles;

    if (resourceAccess != null && resourceAccess.get("account") != null) {
      Map<String, Object> account = (Map<String, Object>) resourceAccess.get("account");
      if (account.containsKey("roles")) {
        resourceRoles = (Collection<String>) account.get("roles");
        allRoles.addAll(resourceRoles);
      }
    }

    if (realmAccess != null && realmAccess.containsKey("roles")) {
      realmRoles = (Collection<String>) realmAccess.get("roles");
      allRoles.addAll(realmRoles);
    }
    if (allRoles.isEmpty() || !Objects.equals(resourceId, jwt.getClaim("azp"))) {
      return Set.of();
    }
    return allRoles.stream()
        .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
        .collect(Collectors.toSet());
  }
}
