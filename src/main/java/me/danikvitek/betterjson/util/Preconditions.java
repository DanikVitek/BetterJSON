package me.danikvitek.betterjson.util;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class Preconditions {
    private Preconditions() {
    }

    @Contract(value = "null -> fail; !null -> param1", pure = true)
    public static <T> @NotNull T checkNotNull(T obj) throws NullPointerException {
        if (obj == null) throw new NullPointerException();
        return obj;
    }
}
