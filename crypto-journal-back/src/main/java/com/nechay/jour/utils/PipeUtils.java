package com.nechay.jour.utils;

import javax.annotation.Nonnull;
import java.util.function.Function;

/**
 * @author anechaev
 * @since 05.05.2024
 */
public final class PipeUtils {

    private PipeUtils() {}

    public static <T0, T1, T2> T2 pipe(@Nonnull T0 arg,
        @Nonnull Function<T0, T1> function1,
        @Nonnull Function<T1, T2> function2)
    {
        return function1
            .andThen(function2)
            .apply(arg);
    }

    public static <T0, T1, T2, T3> T3 pipe(@Nonnull T0 arg,
        @Nonnull Function<T0, T1> function1,
        @Nonnull Function<T1, T2> function2,
        @Nonnull Function<T2, T3> function3)
    {
        return function1
            .andThen(function2)
            .andThen(function3)
            .apply(arg);
    }

    public static <T0, T1, T2, T3, T4> T4 pipe(@Nonnull T0 arg,
        @Nonnull Function<T0, T1> function1,
        @Nonnull Function<T1, T2> function2,
        @Nonnull Function<T2, T3> function3,
        @Nonnull Function<T3, T4> function4)
    {
        return function1
            .andThen(function2)
            .andThen(function3)
            .andThen(function4)
            .apply(arg);
    }

    public static <T0, T1, T2, T3, T4, T5> T5 pipe(@Nonnull T0 arg,
        @Nonnull Function<T0, T1> function1,
        @Nonnull Function<T1, T2> function2,
        @Nonnull Function<T2, T3> function3,
        @Nonnull Function<T3, T4> function4,
        @Nonnull Function<T4, T5> function5)
    {
        return function1
            .andThen(function2)
            .andThen(function3)
            .andThen(function4)
            .andThen(function5)
            .apply(arg);
    }

    public static <T0, T1, T2, T3, T4, T5, T6> T6 pipe(@Nonnull T0 arg,
        @Nonnull Function<T0, T1> function1,
        @Nonnull Function<T1, T2> function2,
        @Nonnull Function<T2, T3> function3,
        @Nonnull Function<T3, T4> function4,
        @Nonnull Function<T4, T5> function5,
        @Nonnull Function<T5, T6> function6)
    {
        return function1
            .andThen(function2)
            .andThen(function3)
            .andThen(function4)
            .andThen(function5)
            .andThen(function6)
            .apply(arg);
    }

    public static <T0, T1, T2, T3, T4, T5, T6, T7> T7 pipe(@Nonnull T0 arg,
        @Nonnull Function<T0, T1> function1,
        @Nonnull Function<T1, T2> function2,
        @Nonnull Function<T2, T3> function3,
        @Nonnull Function<T3, T4> function4,
        @Nonnull Function<T4, T5> function5,
        @Nonnull Function<T5, T6> function6,
        @Nonnull Function<T6, T7> function7)
    {
        return function1
            .andThen(function2)
            .andThen(function3)
            .andThen(function4)
            .andThen(function5)
            .andThen(function6)
            .andThen(function7)
            .apply(arg);
    }

    public static <T0, T1, T2, T3, T4, T5, T6, T7, T8> T8 pipe(@Nonnull T0 arg,
        @Nonnull Function<T0, T1> function1,
        @Nonnull Function<T1, T2> function2,
        @Nonnull Function<T2, T3> function3,
        @Nonnull Function<T3, T4> function4,
        @Nonnull Function<T4, T5> function5,
        @Nonnull Function<T5, T6> function6,
        @Nonnull Function<T6, T7> function7,
        @Nonnull Function<T7, T8> function8)
    {
        return function1
            .andThen(function2)
            .andThen(function3)
            .andThen(function4)
            .andThen(function5)
            .andThen(function6)
            .andThen(function7)
            .andThen(function8)
            .apply(arg);
    }

    public static <T0, T1, T2, T3, T4, T5, T6, T7, T8, T9> T9 pipe(@Nonnull T0 arg,
        @Nonnull Function<T0, T1> function1,
        @Nonnull Function<T1, T2> function2,
        @Nonnull Function<T2, T3> function3,
        @Nonnull Function<T3, T4> function4,
        @Nonnull Function<T4, T5> function5,
        @Nonnull Function<T5, T6> function6,
        @Nonnull Function<T6, T7> function7,
        @Nonnull Function<T7, T8> function8,
        @Nonnull Function<T8, T9> function9)
    {
        return function1
            .andThen(function2)
            .andThen(function3)
            .andThen(function4)
            .andThen(function5)
            .andThen(function6)
            .andThen(function7)
            .andThen(function8)
            .andThen(function9)
            .apply(arg);
    }
}
