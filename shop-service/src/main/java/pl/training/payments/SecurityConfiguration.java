package pl.training.payments;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import pl.training.payments.security.KeycloakJwtGrantedAuthoritiesConverter;

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
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity

                .cors(config -> config.configurationSource(request -> corsConfiguration()))

                .csrf(AbstractHttpConfigurer::disable)

                .oauth2ResourceServer(config -> config.jwt(this::jwtConfig))

                .oauth2Login(config -> {})

                /*.logout(config -> config
                        //.logoutRequestMatcher(new AntPathRequestMatcher("/logout.html"))
                        .logoutUrl("/logout.html")
                        .logoutSuccessUrl("/login.html")
                        .invalidateHttpSession(true)
                )*/

                .authorizeHttpRequests(config -> config
                        .requestMatchers("/orders").authenticated()
                        .anyRequest().authenticated()
                )

                .build();
    }

    private void jwtConfig(OAuth2ResourceServerConfigurer<HttpSecurity>.JwtConfigurer jwtConfigurer) {
        var jwtConverter = new JwtAuthenticationConverter();
        jwtConverter.setJwtGrantedAuthoritiesConverter(new KeycloakJwtGrantedAuthoritiesConverter());
        jwtConfigurer.jwtAuthenticationConverter(jwtConverter);
    }

}
