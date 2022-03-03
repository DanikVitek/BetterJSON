package me.danikvitek.betterjson.johntype.primitive;

import com.google.gson.JsonPrimitive;
import me.danikvitek.betterjson.johntype.JohnType;
import org.jetbrains.annotations.NotNull;

import static me.danikvitek.betterjson.util.Preconditions.checkNotNull;

public class JohnBoolean extends JohnPrimitive<Boolean> {
    public static final JohnType TYPE = JohnType.BOOLEAN;

    private final @NotNull Boolean value;

    public JohnBoolean(@NotNull Boolean value) {
        super(JohnBoolean.TYPE);
        this.value = checkNotNull(value);
    }

    @Override
    public @NotNull Boolean get() {
        return this.value;
    }

    @Override
    public Boolean getAsBoolean() {
        return this.value;
    }

    @Override
    public JsonPrimitive toJson() {
        return new JsonPrimitive(get());
    }
}
