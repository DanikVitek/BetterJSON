package me.danikvitek.betterjson;

import me.danikvitek.betterjson.builder.JohnArrayBuilder;
import me.danikvitek.betterjson.builder.JohnObjectBuilder;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public final class BetterJsonAPI {
    private BetterJsonAPI() {
    }

    @Contract(" -> new")
    public static @NotNull JohnObjectBuilder objectBuilder() {
        return new JohnObjectBuilder();
    }

    @Contract(" -> new")
    public static @NotNull JohnArrayBuilder arrayBuilder() {
        return new JohnArrayBuilder();
    }
}
