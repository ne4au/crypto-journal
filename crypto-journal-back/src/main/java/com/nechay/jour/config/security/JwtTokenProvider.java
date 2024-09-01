package com.nechay.jour.config.security;

import com.nechay.jour.utils.EnvProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;

import static com.nechay.jour.utils.FunctionUtils.call;
import static com.nechay.jour.utils.FunctionUtils.mapOpt;
import static com.nechay.jour.utils.FunctionUtils.orElse;
import static com.nechay.jour.utils.PipeUtils.pipe;
import static java.util.stream.Collectors.joining;

/**
 * @author anechaev
 * @since 05.05.2024
 */
@Component
public class JwtTokenProvider {
    private static final Logger log = LoggerFactory.getLogger(JwtTokenProvider.class);
    private static final String AUTHORITIES_KEY = "perms";
    private static final int MILLIS_IN_MINUTE = 1000 * 60;

    private final Environment environment;
    private final SecretKey secretKey;

    public JwtTokenProvider(@Nonnull @Autowired Environment environment) {
        this.environment = environment;
        this.secretKey = pipe(
            EnvProperties.JWT_SECRET,
            environment::getProperty,
            Base64.getDecoder()::decode,
            Keys::hmacShaKeyFor
        );
    }

    @Nonnull
    public String createToken(@Nonnull Authentication authentication) {
        var claims = createClaims(authentication);
        Date now = new Date();
        Date validity = new Date(now.getTime() + MILLIS_IN_MINUTE * 5);
        return Jwts
            .builder()
            .claims(claims)
            .issuedAt(now)
            .expiration(validity)
            .signWith(this.secretKey, Jwts.SIG.HS256)
            .compact();

    }


    @Nonnull
    private Claims createClaims(@Nonnull Authentication authentication) {
        var claimsBuilder = Jwts
            .claims()
            .subject( authentication.getName());
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        if (authorities.isEmpty()) {
            return claimsBuilder.build();
        }
        claimsBuilder.add(AUTHORITIES_KEY, wrapAuthorities(authorities));
        return claimsBuilder.build();
    }

    @Nonnull
    private String wrapAuthorities(@Nonnull Collection<? extends GrantedAuthority> authorities) {
        return authorities
            .stream()
            .map(GrantedAuthority::getAuthority)
            .collect(joining(","));
    }

    @Nonnull
    public Authentication getAuthentication(String token) {
        Claims claims = Jwts.parser()
            .verifyWith(this.secretKey)
            .build()
            .parseSignedClaims(token)
            .getPayload();
        Collection<? extends GrantedAuthority> authorities = pipe(
            claims,
            call(Claims::get, AUTHORITIES_KEY),
            Optional::ofNullable,
            mapOpt(Object::toString),
            mapOpt(AuthorityUtils::commaSeparatedStringToAuthorityList),
            orElse(AuthorityUtils.NO_AUTHORITIES)
        );
        User principal = new User(claims.getSubject(), "", authorities);
        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
    }

    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts
                .parser()
                .verifyWith(this.secretKey)
                .build()
                .parseSignedClaims(token);
            // parseClaimsJws will check expiration date. No need do here.
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            log.info("Invalid JWT token: {}", e.getMessage());
            log.trace("Invalid JWT token trace.", e);
            return false;
        }
    }

}