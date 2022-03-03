package me.danikvitek.betterjson.builder;

import me.danikvitek.betterjson.johntype.JohnArray;
import me.danikvitek.betterjson.johntype.JohnElement;
import me.danikvitek.betterjson.johntype.primitive.JohnBoolean;
import me.danikvitek.betterjson.johntype.primitive.JohnNumber;
import me.danikvitek.betterjson.johntype.primitive.JohnString;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;
import java.util.List;

import static me.danikvitek.betterjson.util.Preconditions.checkNotNull;

public class JohnArrayBuilder {
    private final List<JohnElement> elements = new LinkedList<>();

    public JohnArrayBuilder addElement(final @NotNull JohnElement element) {
        this.elements.add(checkNotNull(element));
        return this;
    }

    public JohnArrayBuilder addElement(final @NotNull String element) {
        this.elements.add(new JohnString(checkNotNull(element)));
        return this;
    }

    public JohnArrayBuilder addElement(final @NotNull Character element) {
        this.elements.add(new JohnString(checkNotNull(element)));
        return this;
    }

    public JohnArrayBuilder addElement(final @NotNull Boolean element) {
        this.elements.add(new JohnBoolean(checkNotNull(element)));
        return this;
    }

    public JohnArrayBuilder addElement(final @NotNull Number element) {
        this.elements.add(new JohnNumber(checkNotNull(element)));
        return this;
    }

    public JohnArrayBuilder addNull() {
        elements.add(null);
        return this;
    }

    public JohnArray build() {
        var result = new JohnArray();
        result.addAll(this.elements);
        return result;
    }
}
