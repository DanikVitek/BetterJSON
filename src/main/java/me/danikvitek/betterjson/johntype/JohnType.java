package me.danikvitek.betterjson.johntype;

import me.danikvitek.betterjson.johntype.primitive.JohnBoolean;
import me.danikvitek.betterjson.johntype.primitive.JohnNumber;
import me.danikvitek.betterjson.johntype.primitive.JohnString;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public enum JohnType {
    STRING(JohnString.class),
    NUMBER(JohnNumber.class),
    BOOLEAN(JohnBoolean.class),
    NULL(JohnNull.class),
    OBJECT(JohnObject.class),
    ARRAY(JohnArray.class),
    JOHN_CLASS(JohnClassObject.class);

    private static final Map<Class<? extends JohnElement>, JohnType> associations;

    static {
        associations = new HashMap<>();
        associations.put(JohnString.class, STRING);
        associations.put(JohnNumber.class, NUMBER);
        associations.put(JohnBoolean.class, BOOLEAN);
        associations.put(JohnNull.class, NULL);
        associations.put(JohnObject.class, OBJECT);
        associations.put(JohnArray.class, ARRAY);
        associations.put(JohnClassObject.class, JOHN_CLASS);
    }

    private final Class<? extends JohnElement> elementClass;

    JohnType(Class<? extends JohnElement> elementClass) {
        this.elementClass = elementClass;
    }

    public Class<? extends JohnElement> getElementClass() {
        return this.elementClass;
    }

    public static JohnType getByElementClass(@NotNull Class<? extends JohnElement> elementClass) {
        return associations.get(elementClass);
    }
}
