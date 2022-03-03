package me.danikvitek.betterjson.johntype.primitive;

import com.google.gson.JsonPrimitive;
import me.danikvitek.betterjson.johntype.JohnType;
import org.jetbrains.annotations.NotNull;

import static me.danikvitek.betterjson.util.Preconditions.checkNotNull;

public class JohnNumber extends JohnPrimitive<Number> {
    public static final JohnType TYPE = JohnType.NUMBER;

    private final @NotNull Number value;

    public JohnNumber(@NotNull Number value) {
        super(JohnNumber.TYPE);
        this.value = checkNotNull(value);
    }

    @Override
    public @NotNull Number get() {
        return this.value;
    }

    @Override
    public Number getAsNumber() {
        return this.value;
    }

    @Override
    public JsonPrimitive toJson() {
        return new JsonPrimitive(get());
    }
}
