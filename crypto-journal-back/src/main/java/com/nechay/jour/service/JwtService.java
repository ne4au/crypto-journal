package com.nechay.jour.service;

import com.nechay.jour.domain.UserModel;
import com.nechay.jour.utils.EnvProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static com.nechay.jour.utils.FunctionUtils.call;
import static com.nechay.jour.utils.FunctionUtils.mapOpt;
import static com.nechay.jour.utils.FunctionUtils.orElse;
import static com.nechay.jour.utils.PipeUtils.pipe;

/**
 * @author anechaev
 * @since 05.05.2024
 */
@Component
public class JwtService {

    private static final int MILLIS_IN_MINUTE = 1000 * 60;


    private final Environment environment;
    private final byte[] keyBytes;

    public JwtService(@Nonnull @Autowired Environment environment) {
        this.environment = environment;
        this.keyBytes = pipe(
            EnvProperties.JWT_SECRET,
            environment::getProperty,
            Decoders.BASE64::decode
        );
    }

    @Nonnull
    public Optional<String> extractUsername(String token) {
        return pipe(
            token,
            this::extractAllClaims,
            Claims::getSubject,
            Optional::ofNullable);
    }

    @Nonnull
    private Claims extractAllClaims(String token) {
        return Jwts
            .parser()
            .verifyWith(getSignKey())
            .build()
            .parseSignedClaims(token)
            .getPayload();
    }

    public boolean validateToken(String token, UserModel userModel) {
        Claims claims = extractAllClaims(token);
        String subject = claims.getSubject();
        boolean isSubjectMatched = pipe(userModel,
            UserModel::getLogin,
            call(String::equals, subject));
        boolean isTokenExpired = pipe(
            claims,
            Claims::getExpiration,
            Optional::ofNullable,
            mapOpt(call(Date::before, new Date())),
            orElse(true)
        );
        return isSubjectMatched && !isTokenExpired;
    }

    @Nonnull
    public String generateToken(UserModel userModel) {
        return createToken(new HashMap<>(), userModel.getLogin());
    }

    private String createToken(Map<String, Object> claims, String login) {
        long currentTimeMillis = System.currentTimeMillis();
        return Jwts.builder()
            .claims(claims)
            .subject(login)
            .issuedAt(new Date(currentTimeMillis))
            .expiration(new Date(currentTimeMillis + MILLIS_IN_MINUTE * 2))
            .signWith(getSignKey(), Jwts.SIG.HS256)
            .compact();
    }

    private SecretKey getSignKey() {
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
