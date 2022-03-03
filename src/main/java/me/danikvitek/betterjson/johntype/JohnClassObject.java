package me.danikvitek.betterjson.johntype;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;

import static me.danikvitek.betterjson.util.Preconditions.checkNotNull;

public class JohnClassObject extends JohnElement {
    public static final JohnType TYPE = JohnType.JOHN_CLASS;

    private final @NotNull Class<? extends JohnClass> associatedClass;
    private final @NotNull JohnClass value;

    public JohnClassObject(@NotNull JohnClass value) {
        this.associatedClass = value.getClass();
        this.value = checkNotNull(value);
    }

    @Override
    public @NotNull JohnType getType() {
        return JohnClassObject.TYPE;
    }

    public @NotNull JohnClass getValue() {
        return this.value;
    }

    public @NotNull Class<? extends JohnClass> getAssociatedClass() {
        return this.associatedClass;
    }

    @Override
    public @NotNull JohnElement deepCopy() {
        return this;
    }

    @Override
    public JsonElement toJson() {
        final Field[] fields = associatedClass.getDeclaredFields();
        var result = new JsonArray(fields.length);
        for (Field field : fields) {
            try {
                if (field.trySetAccessible()) {
                    Object fieldValue = field.get(this.value);
                    if (fieldValue == null || fieldValue == JohnNull.INSTANCE)
                        result.add(JsonNull.INSTANCE);
                    else if (fieldValue instanceof JohnElement johnElement) {
                        switch (johnElement.getType()) {
                            case STRING -> result.add(johnElement.getAsString());
                            case NUMBER -> result.add(johnElement.getAsNumber());
                            case BOOLEAN -> result.add(johnElement.getAsBoolean());
                            case NULL -> result.add(JsonNull.INSTANCE);
                            case OBJECT, JOHN_CLASS, ARRAY -> result.add(johnElement.toJson());
                            default -> throw new IllegalStateException("Unexpected value: " + johnElement.getType());
                        }
                    }
                }
            } catch (IllegalAccessException | SecurityException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
