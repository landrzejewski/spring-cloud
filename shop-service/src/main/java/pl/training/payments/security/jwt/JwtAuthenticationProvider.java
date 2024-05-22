package pl.training.payments.security.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

@RequiredArgsConstructor
public class JwtAuthenticationProvider implements AuthenticationProvider {

    private static final String INVALID_TOKEN = "Invalid token";

    private final JwtService jwtService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (authentication instanceof JwtAuthentication jwtAuthentication) {
            var token = jwtAuthentication.getToken();
            return jwtService.decode(token)
                    .orElseThrow(() -> new BadCredentialsException(INVALID_TOKEN));
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtAuthentication.class.isAssignableFrom(authentication);
    }

}
