package com.nechay.jour.config;

import com.nechay.jour.config.security.JwtTokenAuthenticationFilter;
import com.nechay.jour.config.security.JwtTokenProvider;
import com.nechay.jour.utils.CjRoutes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.security.web.server.context.NoOpServerSecurityContextRepository;
import reactor.core.publisher.Mono;

import javax.annotation.Nonnull;

@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class CjWebSecurityConfigurerAdapter {

    @Bean
    public SecurityWebFilterChain springWebFilterChain(
        @Nonnull ServerHttpSecurity http,
        @Nonnull @Autowired JwtTokenProvider tokenProvider,
        @Nonnull @Autowired ReactiveAuthenticationManager reactiveAuthenticationManager)
    {
        return http.csrf(ServerHttpSecurity.CsrfSpec::disable)
            .cors(ServerHttpSecurity.CorsSpec::disable)
            .httpBasic(ServerHttpSecurity.HttpBasicSpec::disable)
            .formLogin(ServerHttpSecurity.FormLoginSpec::disable)
            .authenticationManager(reactiveAuthenticationManager)
            .securityContextRepository(NoOpServerSecurityContextRepository.getInstance())
            .authorizeExchange(it -> it
                .pathMatchers(HttpMethod.GET, CjRoutes.TEST_ROUTE).authenticated()
                .pathMatchers(HttpMethod.POST, CjRoutes.AUTH_ROUTE + "/**").permitAll()
                .pathMatchers("/users/{user}/**").access(this::currentUserMatchesPath)
                .anyExchange().permitAll()
            )
            .addFilterBefore(new JwtTokenAuthenticationFilter(tokenProvider), SecurityWebFiltersOrder.AUTHENTICATION)
            .build();
    }

    private Mono<AuthorizationDecision> currentUserMatchesPath(
        Mono<Authentication> authentication,
        AuthorizationContext context)
    {
        return authentication
            .map(a -> context.getVariables().get("user").equals(a.getName()))
            .map(AuthorizationDecision::new);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
//        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ReactiveAuthenticationManager reactiveAuthenticationManager(
        ReactiveUserDetailsService userDetailsService,
        PasswordEncoder passwordEncoder)
    {
        var authenticationManager = new UserDetailsRepositoryReactiveAuthenticationManager(userDetailsService);
        authenticationManager.setPasswordEncoder(passwordEncoder);
        return authenticationManager;
    }
}
