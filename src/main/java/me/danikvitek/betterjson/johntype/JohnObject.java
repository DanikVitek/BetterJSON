package me.danikvitek.betterjson.johntype;

import com.google.gson.JsonObject;
import com.google.gson.internal.LinkedTreeMap;
import me.danikvitek.betterjson.johntype.primitive.JohnBoolean;
import me.danikvitek.betterjson.johntype.primitive.JohnNumber;
import me.danikvitek.betterjson.johntype.primitive.JohnString;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import static me.danikvitek.betterjson.util.Preconditions.checkNotNull;

public class JohnObject extends JohnElement implements FinalJohnElement {
    public static final JohnType TYPE = JohnType.OBJECT;

    private final LinkedTreeMap<String, JohnElement> members = new LinkedTreeMap<>();

    public void add(@NotNull String key, JohnElement value) {
        this.members.put(key, value == null ? JohnNull.INSTANCE : value);
    }

    public void add(@NotNull String key, String value) {
        add(checkNotNull(key), value == null ? JohnNull.INSTANCE : new JohnString(value));
    }

    public void add(@NotNull String key, Number value) {
        add(checkNotNull(key), value == null ? JohnNull.INSTANCE : new JohnNumber(value));
    }

    public void add(@NotNull String key, Boolean value) {
        add(checkNotNull(key), value == null ? JohnNull.INSTANCE : new JohnBoolean(value));
    }

    public void add(@NotNull String key, Character value) {
        add(checkNotNull(key), value == null ? JohnNull.INSTANCE : new JohnString(value));
    }

    public JohnElement remove(@NotNull String key) {
        return this.members.remove(key);
    }

    public JohnElement get(@NotNull String key) {
        return this.members.get(key);
    }

    public JohnObject getAsJohnObject(@NotNull String key) throws ClassCastException {
        return (JohnObject) this.members.get(key);
    }

    public JohnArray getAsJohnArray(@NotNull String key) throws ClassCastException {
        return (JohnArray) this.members.get(key);
    }

    public JohnClassObject getAsJohnClassObject(@NotNull String key) throws ClassCastException {
        return (JohnClassObject) this.members.get(key);
    }

    public JohnString getAsJohnString(@NotNull String key) throws ClassCastException {
        return (JohnString) this.members.get(key);
    }

    public JohnBoolean getAsJohnBoolean(@NotNull String key) throws ClassCastException {
        return (JohnBoolean) this.members.get(key);
    }

    public JohnNumber getAsJohnNumber(@NotNull String key) throws ClassCastException {
        return (JohnNumber) this.members.get(key);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JohnObject that)) return false;

        return this.members.equals(that.members);
    }

    @Override
    public int hashCode() {
        return this.members.hashCode();
    }

    @Override
    public @NotNull JohnElement deepCopy() {
        final var result = new JohnObject();
        this.members.forEach((key, value) -> result.add(key, value.deepCopy()));
        return result;
    }

    @Override
    public @NotNull JohnType getType() {
        return JohnObject.TYPE;
    }

    @Override
    public JsonObject toJson() {
        final var result = new JsonObject();
        this.members.forEach((key, value) -> result.add(key, value.toJson()));
        return result;
    }

    @Override
    public @NotNull Collection<JohnElement> values() {
        return this.members.values();
    }

    @Override
    public @NotNull List<Map.Entry<String, JohnElement>> entries() {
        return this.members.entrySet().stream().toList();
    }
}
