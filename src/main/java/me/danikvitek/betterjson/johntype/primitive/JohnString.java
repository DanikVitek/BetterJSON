package me.danikvitek.betterjson.johntype.primitive;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.internal.LazilyParsedNumber;
import me.danikvitek.betterjson.johntype.JohnType;
import org.jetbrains.annotations.NotNull;

import static me.danikvitek.betterjson.util.Preconditions.checkNotNull;

public class JohnString extends JohnPrimitive<String> {
    public static final JohnType TYPE = JohnType.STRING;

    private final @NotNull String value;

    public JohnString(@NotNull String value) {
        super(JohnString.TYPE);
        this.value = checkNotNull(value);
    }

    public JohnString(@NotNull Character value) {
        super(JohnType.STRING);
        this.value = checkNotNull(value).toString();
    }

    @Override
    public @NotNull String get() {
        return this.value;
    }

    @Override
    public String getAsString() {
        return this.value;
    }

    @Override
    public Number getAsNumber() {
        return new LazilyParsedNumber(this.value);
    }

    @Override
    public Boolean getAsBoolean() {
        return Boolean.parseBoolean(this.value);
    }

    @Override
    public JsonPrimitive toJson() {
        return new JsonPrimitive(get());
    }
}
