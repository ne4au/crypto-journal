package com.nechay.jour.utils;

import javax.annotation.Nonnull;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @author anechaev
 * @since 05.05.2024
 */
public final class FunctionUtils {

    private FunctionUtils() {}

    @Nonnull
    public static <T, A, R> Function<T, R> call(@Nonnull BiFunction<T, A, R> method, @Nonnull A arg) {
        return t -> method.apply(t, arg);
    }

    @Nonnull
    public static <T, A, R> Predicate<T> predicate(@Nonnull BiFunction<T, A, Boolean> method, @Nonnull A arg) {
        return t -> method.apply(t, arg);
    }


    @Nonnull
    public static <T, R> Function<Optional<T>, Optional<R>> mapOpt(@Nonnull Function<T, R> mapper) {
        return optional -> optional.map(mapper);
    }

    @Nonnull
    public static <T> Function<Optional<T>, Optional<T>> filterOpt(@Nonnull Predicate<T> predicate) {
        return optional -> optional.filter(predicate);
    }

    @Nonnull
    public static <T, A> Function<Optional<T>, Optional<T>> filterOpt(@Nonnull BiFunction<T, A, Boolean> method, @Nonnull A arg) {
        return optional -> optional.filter(content -> method.apply(content, arg));
    }

    @Nonnull
    public static <T> Function<Optional<T>, T> orElse(@Nonnull T elseR) {
        return optional -> optional.orElse(elseR);
    }
}
