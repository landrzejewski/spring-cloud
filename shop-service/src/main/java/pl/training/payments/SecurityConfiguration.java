package pl.training.payments;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.oauth2.client.OAuth2LoginConfigurer;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.cors.CorsConfiguration;
import pl.training.payments.security.KeycloakGrantedAuthoritiesMapper;
import pl.training.payments.security.KeycloakJwtGrantedAuthoritiesConverter;
import pl.training.payments.security.KeycloakLogoutHandler;

import java.util.List;

@Configuration
public class SecurityConfiguration {

    @Bean
    public CorsConfiguration corsConfiguration() {
        var corsConfig = new CorsConfiguration();
        corsConfig.setAllowedOrigins(List.of("http://localhost:4800"));
        corsConfig.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
        corsConfig.setAllowedHeaders(List.of("*"));
        corsConfig.setAllowCredentials(true);
        return corsConfig;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .cors(config -> config.configurationSource(request -> corsConfiguration()))
                .csrf(AbstractHttpConfigurer::disable)
                .oauth2ResourceServer(config -> config.jwt(this::jwtConfig))
                .oauth2Login(config -> config.userInfoEndpoint(this::userInfoCustomizer))
                .authorizeHttpRequests(config ->
                        config.anyRequest().hasRole("ADMIN")
                )
                .logout(config -> config
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/actuator")
                        .invalidateHttpSession(true)
                        .addLogoutHandler(new KeycloakLogoutHandler(new RestTemplate()))
                )
                .build();
    }

    private void jwtConfig(OAuth2ResourceServerConfigurer<HttpSecurity>.JwtConfigurer jwtConfigurer) {
        var jwtConverter = new JwtAuthenticationConverter();
        jwtConverter.setJwtGrantedAuthoritiesConverter(new KeycloakJwtGrantedAuthoritiesConverter());
        jwtConfigurer.jwtAuthenticationConverter(jwtConverter);
    }

    // Client scopes -> Client scope details (roles) -> Mapper details (Realm access) -> Add to userinfo enabled (realm-roles)
    private void userInfoCustomizer(OAuth2LoginConfigurer<HttpSecurity>.UserInfoEndpointConfig userInfoEndpointConfig) {
        userInfoEndpointConfig.userAuthoritiesMapper(new KeycloakGrantedAuthoritiesMapper());
    }

    /*
    Definiowanie First login flow dla logowania przez GitHub
    Authentication -> Create flow (detect existing user flow, Basic flow) -> Add step (Detect existing broker user, Automatically set existing user)
    Identity providers -> github -> First login flow -> detect existing user flow
    */

}
