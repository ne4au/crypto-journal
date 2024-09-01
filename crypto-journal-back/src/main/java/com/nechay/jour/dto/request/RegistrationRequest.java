package com.nechay.jour.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/**
 * @author anechaev
 * @since 28.06.2024
 */
public record RegistrationRequest(
    @NotBlank String username,
    @NotBlank @Email String email,
    @NotBlank String password
) {}
