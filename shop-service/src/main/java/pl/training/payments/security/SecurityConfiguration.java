package pl.training.payments.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.oauth2.client.OAuth2LoginConfigurer;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.client.RestTemplate;

@Configuration
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .oauth2ResourceServer(config -> config.jwt(this::jwtConfig))
                .oauth2Login(config -> config.userInfoEndpoint(this::userInfoConfig))
                .authorizeHttpRequests(config -> config
                        .anyRequest().hasRole("ADMIN")
                )
                .logout(config -> config
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true)
                        .addLogoutHandler(new KeycloakLogoutHandler(new RestTemplate()))
                )
                .build();
    }

    private void jwtConfig(OAuth2ResourceServerConfigurer<HttpSecurity>.JwtConfigurer jwtConfigurer) {
        var jwtConverter = new JwtAuthenticationConverter();
        jwtConverter.setJwtGrantedAuthoritiesConverter(new KeycloakAuthoritiesConverter());
        jwtConfigurer.jwtAuthenticationConverter(jwtConverter);
    }

    private void userInfoConfig(OAuth2LoginConfigurer<HttpSecurity>.UserInfoEndpointConfig userInfoEndpointConfig) {
        userInfoEndpointConfig.userAuthoritiesMapper(new DefaultRoleMapper());
    }

}
