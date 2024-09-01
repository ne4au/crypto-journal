package com.nechay.jour.service;

import com.nechay.jour.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * @author anechaev
 * @since 05.05.2024
 */
@Component
public class UserDetailsServiceImpl implements ReactiveUserDetailsService {
    @Autowired private UserRepo userRepo;

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        return userRepo.findByLogin(username)
            .next()
            .map(userModel -> (UserDetails) userModel)
            .switchIfEmpty(Mono.error(new UsernameNotFoundException("User with username `" + username + "` does not exist!")));
    }
}
