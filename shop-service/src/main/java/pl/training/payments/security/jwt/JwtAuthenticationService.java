package pl.training.payments.security.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.training.payments.security.JpaUserDetailsService;

import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class JwtAuthenticationService {

    private final JwtService jwtService;
    private final JpaUserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    public Optional<String> authenticate(String username, String password) {
        try {
            var userDetails = userDetailsService.loadUserByUsername(username);
            if (passwordEncoder.matches(password, userDetails.getPassword())) {
                var roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(toList());
                var token = jwtService.get(username, roles);
                return Optional.of(token);
            }
        } catch (UsernameNotFoundException usernameNotFoundException) {
            passwordEncoder.matches(password, password);
        }
        return Optional.empty();
    }

}
