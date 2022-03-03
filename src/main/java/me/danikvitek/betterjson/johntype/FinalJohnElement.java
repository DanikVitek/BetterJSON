package me.danikvitek.betterjson.johntype;

import com.google.gson.JsonObject;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface FinalJohnElement {
    private static @NotNull JsonObject extractClassesFromJohnClassObject(@NotNull Class<? extends JohnClass> associatedClass) {
        final var johnClassObjectJson = new JsonObject();
        try {
            Arrays.stream(associatedClass.getDeclaredFields())
                    .forEach(field -> johnClassObjectJson.addProperty(
                            field.getName(),
                            field.getType().getSimpleName()
                    ));
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        return johnClassObjectJson;
    }

    default @NotNull JsonObject toJohnJsonObject() {
        final var result = new JsonObject();

        // BUILD HEADERS
        final var header = new JsonObject();

        final var classes = extractClasses();
        header.add("classes", classes);

        final var classRelations = extractClassRelations();
        header.add("classRelations", classRelations);
        // END BUILDING HEADERS

        result.add("JOHN_HEADER", header);
        result.add("BODY", ((JohnElement) this).toJson());
        return result;
    }

    private @NotNull JsonObject extractClasses() {
        final JsonObject classes = new JsonObject();
        this.values().stream()
                .filter(memberValue -> {
                    final var type = memberValue.getType();
                    return type == JohnType.JOHN_CLASS || type == JohnType.OBJECT || type == JohnType.ARRAY;
                })
                .forEach(johnElement -> {
                    switch (johnElement.getType()) {
                        case JOHN_CLASS -> {
                            final var associatedClass = ((JohnClassObject) johnElement).getAssociatedClass();
                            final var className = associatedClass.getSimpleName();
                            final var extracted = FinalJohnElement.extractClassesFromJohnClassObject(associatedClass);
                            classes.add(className, extracted);
                        }
                        case OBJECT, ARRAY -> ((FinalJohnElement) johnElement)
                                .extractClasses()
                                .entrySet()
                                .forEach(entry -> classes.add(entry.getKey(), entry.getValue()));
                        default -> throw new IllegalStateException("Unexpected value: " + johnElement.getType());
                    }
                });
        return classes;
    }

    private @NotNull JsonObject extractClassRelations() {
        final var classRelations = new JsonObject();
        this.entries().stream()
                .filter(entry -> {
                    final JohnType type = entry.getValue().getType();
                    return type == JohnType.JOHN_CLASS || type == JohnType.OBJECT || type == JohnType.ARRAY;
                })
                .forEach(entry -> {
                    final String key = entry.getKey();
                    final JohnElement value = entry.getValue();
                    switch (value.getType()) {
                        case JOHN_CLASS -> classRelations.addProperty(
                                key,
                                ((JohnClassObject) value).getAssociatedClass().getSimpleName()
                        );
                        case OBJECT, ARRAY -> classRelations.add(
                                key,
                                ((FinalJohnElement) value).extractClassRelations()
                        );
                        default -> throw new IllegalStateException("Unexpected value: " + value.getType());
                    }
                });
        return classRelations;
    }

    @NotNull Collection<JohnElement> values();

    @NotNull List<Map.Entry<String, JohnElement>> entries();
}
