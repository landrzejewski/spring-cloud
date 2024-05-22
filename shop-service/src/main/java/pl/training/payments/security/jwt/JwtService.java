package pl.training.payments.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toSet;

@Service
public class JwtService {

    private static final Algorithm ALGORITHM = Algorithm.HMAC256("secret");
    private static final String ISSUER = "https://localhost:20000";
    private static final String USER_CLAIM = "user";
    private static final String ROLES_CLAIM = "roles";
    private static final long EXPIRATION_PERIOD = 3600;

    public String get(String username, List<String> roles) {
        return JWT.create()
                .withIssuer(ISSUER)
                .withClaim(USER_CLAIM, username)
                .withClaim(ROLES_CLAIM, roles)
                .withExpiresAt(Instant.now().plusSeconds(EXPIRATION_PERIOD))
                .sign(ALGORITHM);
    }

    public Optional<Authentication> decode(String token) {
        try {
            var verifier = JWT.require(ALGORITHM)
                    .withIssuer(ISSUER)
                    .build();
            var decodedJwt = verifier.verify(token);
            var username = decodedJwt.getClaim(USER_CLAIM).asString();
            var roles = decodedJwt.getClaim(ROLES_CLAIM).asList(String.class)
                    .stream()
                    .map(SimpleGrantedAuthority::new)
                    .collect(toSet());
            var authentication = UsernamePasswordAuthenticationToken.authenticated(username, token, roles);
            return Optional.of(authentication);
        } catch (JWTVerificationException jwtVerificationException) {
            return Optional.empty();
        }
    }

}
