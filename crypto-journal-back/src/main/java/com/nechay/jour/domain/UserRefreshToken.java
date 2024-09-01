package com.nechay.jour.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;

/**
 * @author anechaev
 * @since 05.05.2024
 */
@Table(name = "user_refresh_tokens")
public class UserRefreshToken {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
//    @Column
    private String token;
//    @Column
    private Instant expiryDate;
//    @OneToOne
//    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserModel user;

    public UserRefreshToken() {
    }

    public UserRefreshToken(long id, String token, Instant expiryDate, UserModel user) {
        this.id = id;
        this.token = token;
        this.expiryDate = expiryDate;
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public String getToken() {
        return token;
    }

    public Instant getExpiryDate() {
        return expiryDate;
    }

    public UserModel getUser() {
        return user;
    }
}
