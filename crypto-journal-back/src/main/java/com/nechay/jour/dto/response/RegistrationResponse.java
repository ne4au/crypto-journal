package com.nechay.jour.dto.response;

import com.nechay.jour.dto.RegistrationError;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public record RegistrationResponse(
    @Nullable String accessToken,
    @Nullable RegistrationError error
) implements AccessTokenResponse {
    @Nonnull
    public static RegistrationResponse successful(@Nonnull String accessToken) {
        return new RegistrationResponse(accessToken, null);
    }

    @Nonnull
    public static RegistrationResponse error(@Nonnull RegistrationError error) {
        return new RegistrationResponse(null, error);
    }
}
