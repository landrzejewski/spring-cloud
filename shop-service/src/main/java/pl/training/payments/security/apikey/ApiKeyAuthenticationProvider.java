package pl.training.payments.security.apikey;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;

import static java.util.stream.Collectors.toSet;
import static org.springframework.security.authentication.UsernamePasswordAuthenticationToken.authenticated;

@RequiredArgsConstructor
public class ApiKeyAuthenticationProvider implements AuthenticationProvider {

    private static final Set<String> VALID_API_KEYS = Set.of("1234567890");
    private static final Set<GrantedAuthority> DEFAULT_ROLES = Set.of("ROLE_ADMIN")
            .stream()
            .map(SimpleGrantedAuthority::new)
            .collect(toSet());

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (authentication instanceof ApiKeyAuthentication apiKeyAuthentication) {
            var apiKey = apiKeyAuthentication.getApiKey();
            if (!VALID_API_KEYS.contains(apiKey)) {
                throw new BadCredentialsException("Invalid ApiKey");
            }
            return authenticated("", apiKey, DEFAULT_ROLES);
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return ApiKeyAuthentication.class.isAssignableFrom(authentication);
    }

}
