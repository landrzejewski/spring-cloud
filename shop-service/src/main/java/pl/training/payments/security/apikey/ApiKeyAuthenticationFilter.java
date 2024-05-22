package pl.training.payments.security.apikey;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.naming.AuthenticationException;
import java.io.IOException;

import static jakarta.servlet.http.HttpServletResponse.SC_UNAUTHORIZED;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Component
@RequiredArgsConstructor
public class ApiKeyAuthenticationFilter extends OncePerRequestFilter {

    private static final String API_KEY_PREFIX = "API_KEY ";

    private final AuthenticationConfiguration authenticationConfiguration;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var authorizationHeader = request.getHeader(AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith(API_KEY_PREFIX)) {
            var apiKey = authorizationHeader.substring(API_KEY_PREFIX.length());
            var apiKeyAuthentication = new ApiKeyAuthentication(apiKey);
            try {
                var resultAuthentication = authenticationConfiguration.getAuthenticationManager()
                        .authenticate(apiKeyAuthentication);
                save(resultAuthentication);
            } catch (AuthenticationException authenticationException) {
                response.setStatus(SC_UNAUTHORIZED);
            } catch (Exception exception) {
                throw new ServletException(exception);
            }

        }
        filterChain.doFilter(request, response);
    }

    private void save(Authentication authentication) {
        var securityContext = SecurityContextHolder.createEmptyContext();
        securityContext.setAuthentication(authentication);
        SecurityContextHolder.setContext(securityContext);
    }

}
