package com.nechay.jour.repo;

import com.nechay.jour.domain.UserModel;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Nonnull;

public interface UserRepo extends R2dbcRepository<UserModel, Long> {


    @Query("insert into users(version, login, password, email, is_active) "
        + "values (:version, :login, :password, :email, :isActive)")
    <S extends UserModel> Mono<S> save(long version, String login, String password, String email, boolean isActive);

    default <S extends UserModel> Mono<S> save(@Nonnull S userModel) {
        return save(
            userModel.getVersion(),
            userModel.getLogin(),
            userModel.getPassword(),
            userModel.getEmail(),
            userModel.getActive()
        );
    }

    @Nonnull
    Flux<UserModel> findByEmail(@Nonnull String email);

    @Nonnull
    Flux<UserModel> findByLogin(@Nonnull String login);

}
