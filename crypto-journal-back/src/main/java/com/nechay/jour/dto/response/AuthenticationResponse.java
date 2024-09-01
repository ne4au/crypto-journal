package com.nechay.jour.dto.response;

import com.nechay.jour.dto.AuthenticationError;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public record AuthenticationResponse(
    @Nullable String accessToken,
    @Nullable AuthenticationError error
) implements  AccessTokenResponse {
    @Nonnull
    public static AuthenticationResponse successful(@Nonnull String accessToken) {
        return new AuthenticationResponse(accessToken, null);
    }

    @Nonnull
    public static AuthenticationResponse error(@Nonnull AuthenticationError error) {
        return new AuthenticationResponse(null, error);
    }
}
