package me.danikvitek.betterjson.johntype;

import com.google.gson.JsonNull;
import org.jetbrains.annotations.NotNull;

public class JohnNull extends JohnElement {
    public static final JohnType TYPE = JohnType.NULL;

    public static final JohnNull INSTANCE = new JohnNull();

    private JohnNull() {
    }

    @Override
    public @NotNull JohnElement deepCopy() {
        return JohnNull.INSTANCE;
    }

    @Override
    public @NotNull JohnType getType() {
        return JohnNull.TYPE;
    }

    @Override
    public JsonNull toJson() {
        return JsonNull.INSTANCE;
    }
}
