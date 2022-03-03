package me.danikvitek.betterjson.johntype.primitive;

import me.danikvitek.betterjson.johntype.JohnElement;
import me.danikvitek.betterjson.johntype.JohnType;
import org.jetbrains.annotations.NotNull;

public abstract class JohnPrimitive<T> extends JohnElement {
    protected final @NotNull JohnType type;

    protected JohnPrimitive(@NotNull JohnType type) {
        this.type = type;
    }

    public abstract @NotNull T get();

    @Override
    public @NotNull JohnType getType() {
        return this.type;
    }

    @Override
    public @NotNull JohnElement deepCopy() {
        return this;
    }


    @Override
    public String getAsString() {
        return this.get().toString();
    }
}
