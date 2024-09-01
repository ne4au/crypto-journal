package com.nechay.jour.config.security;

import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import javax.annotation.Nonnull;
import java.util.Optional;

import static com.nechay.jour.utils.FunctionUtils.call;
import static com.nechay.jour.utils.FunctionUtils.predicate;

/**
 * @author anechaev
 * @since 05.05.2024
 */
public class JwtTokenAuthenticationFilter implements WebFilter {
    public static final String HEADER_PREFIX = "Bearer ";
    public static final String ACCESS_TOKEN_HEADER = "accessToken";
    private final JwtTokenProvider tokenProvider;

    public JwtTokenAuthenticationFilter(@Nonnull JwtTokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        return resolveToken(exchange.getRequest())
            .filter(this.tokenProvider::validateToken)
            .map(validatedToken -> writeValidatedTokenToContext(exchange, chain, validatedToken))
            .orElseGet(() -> chain.filter(exchange));
    }

    private Mono<Void> writeValidatedTokenToContext(ServerWebExchange exchange, WebFilterChain chain, String validatedToken) {
        return Mono.fromCallable(() -> this.tokenProvider.getAuthentication(validatedToken))
            .subscribeOn(Schedulers.boundedElastic())
            .flatMap(authentication -> chain.filter(exchange)
                .contextWrite(ReactiveSecurityContextHolder.withAuthentication(authentication)));
    }

    @Nonnull
    private Optional<String> resolveToken(ServerHttpRequest request) {
        return Optional.ofNullable(request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION))
            .filter(StringUtils::hasText)
            .filter(predicate(String::startsWith, HEADER_PREFIX))
            .map(call(String::substring, HEADER_PREFIX.length()));
    }
}
