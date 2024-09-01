package com.nechay.jour.service;

import com.nechay.jour.domain.UserModel;
import com.nechay.jour.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import javax.annotation.Nonnull;
import java.util.List;

import static com.nechay.jour.utils.PipeUtils.pipe;

/**
 * @author anechaev
 * @since 05.05.2024
 */
@Component
public record UserService(
    @Nonnull @Autowired UserRepo userRepo,
    @Nonnull @Autowired PasswordEncoder passwordEncoder)
    implements IUserService
{

    @Nonnull
    public Mono<UserModel> create(@Nonnull UserModel model) {
        return pipe(model,
            UserModel::getPassword,
            passwordEncoder::encode,
            encoded -> UserModel.from(model, encoded),
            userRepo::save);
    }

    @Nonnull
    public Mono<Boolean> checkExistence(@Nonnull UserModel model) {
        return userRepo
            .findByLogin(model.getLogin())
            .collectList()
            .map(List::isEmpty);
    }
}
