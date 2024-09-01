package com.nechay.jour.controllers;

import com.nechay.jour.config.entities.Credentials;
import com.nechay.jour.config.security.JwtTokenAuthenticationFilter;
import com.nechay.jour.config.security.JwtTokenProvider;
import com.nechay.jour.domain.UserModel;
import com.nechay.jour.dto.RegistrationError;
import com.nechay.jour.dto.request.AuthenticationRequest;
import com.nechay.jour.dto.request.RegistrationRequest;
import com.nechay.jour.dto.response.AuthenticationResponse;
import com.nechay.jour.dto.response.RegistrationResponse;
import com.nechay.jour.service.UserService;
import com.nechay.jour.utils.CjRoutes;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.annotation.Nonnull;

import java.util.function.Function;

import static com.nechay.jour.utils.PipeUtils.pipe;

@RestController
@RequestMapping(CjRoutes.AUTH_ROUTE)
public class AuthController {

    private final JwtTokenProvider tokenProvider;
    private final ReactiveAuthenticationManager authenticationManager;
    private final UserService userService;

    public AuthController(
        @Nonnull @Autowired JwtTokenProvider tokenProvider,
        @Nonnull @Autowired ReactiveAuthenticationManager authenticationManager,
        @Nonnull @Autowired UserService userService)
    {
        this.tokenProvider = tokenProvider;
        this.authenticationManager = authenticationManager;
        this.userService = userService;
    }

    @PostMapping("/login")
    public Mono<ResponseEntity<AuthenticationResponse>> login(
        @Valid @RequestBody Mono<AuthenticationRequest> authRequest)
    {
        return authRequest.flatMap(creds ->
            authenticate(creds, AuthenticationResponse::successful));
    }

    @PostMapping("/register")
    public Mono<ResponseEntity<RegistrationResponse>> registerUser(
        @Valid @RequestBody Mono<RegistrationRequest> authRequest)
    {

        return authRequest
            .map(request -> new UserModel.Builder()
                .setLogin(request.username())
                .setPassword(request.password())
                .setEmail(request.email())
                .setActive(true)
                .build())
            .flatMap(userModel -> userService
                .checkExistence(userModel)
                .flatMap(exists -> exists
                    ? userExistsResponse()
                    : authenticate(userModel, RegistrationResponse::successful)
                ));
    }

    private Mono<ResponseEntity<RegistrationResponse>> userExistsResponse() {
        return pipe(
            ResponseEntity.badRequest(),
            builder -> builder.body(RegistrationResponse.error(RegistrationError.LOGIN_ALREADY_EXISTS)),
            Mono::just);
    }

    private <T> ResponseEntity<T> toResponse(@Nonnull String jwt, @Nonnull Function<String, T> creator) {
        return ResponseEntity.ok()
            .header(HttpHeaders.AUTHORIZATION, JwtTokenAuthenticationFilter.HEADER_PREFIX + jwt)
            .body(creator.apply(jwt));
    }

    /**
     * @param credentials creds to authenticate
     * @return token
     */
    @Nonnull
    private <T>  Mono<ResponseEntity<T>> authenticate(@Nonnull Credentials credentials,  @Nonnull Function<String, T> creator) {
        return this.authenticationManager
            .authenticate(new UsernamePasswordAuthenticationToken(
                credentials.username(), credentials.password()))
            .map(this.tokenProvider::createToken)
            .map(jwt -> toResponse(jwt, creator);

    }
}
