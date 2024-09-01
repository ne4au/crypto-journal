package com.nechay.jour.dto.request;

import com.nechay.jour.config.entities.Credentials;
import jakarta.validation.constraints.NotBlank;

/**
 * @author anechaev
 * @since 02.06.2024
 */
public record AuthenticationRequest(
    @NotBlank String username,
    @NotBlank String password
) implements Credentials {}