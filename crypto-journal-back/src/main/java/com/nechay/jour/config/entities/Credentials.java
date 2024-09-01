package com.nechay.jour.config.entities;

import javax.annotation.Nonnull;

public interface Credentials {
    @Nonnull String username();
    @Nonnull String password();
}
