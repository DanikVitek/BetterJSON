package me.danikvitek.betterjson.builder;

import me.danikvitek.betterjson.johntype.JohnElement;
import me.danikvitek.betterjson.johntype.JohnObject;
import org.jetbrains.annotations.NotNull;

public class JohnObjectBuilder {
    private final JohnObject johnObject = new JohnObject();

    public JohnObjectBuilder add(@NotNull String key, String value) {
        this.johnObject.add(key, value);
        return this;
    }

    public JohnObjectBuilder add(@NotNull String key, Number value) {
        this.johnObject.add(key, value);
        return this;
    }

    public JohnObjectBuilder add(@NotNull String key, Boolean value) {
        this.johnObject.add(key, value);
        return this;
    }

    public JohnObjectBuilder add(@NotNull String key, Character value) {
        this.johnObject.add(key, value);
        return this;
    }

    public JohnObjectBuilder add(@NotNull String key, JohnElement value) {
        this.johnObject.add(key, value);
        return this;
    }

    public JohnObject build() {
        return this.johnObject;
    }
}
