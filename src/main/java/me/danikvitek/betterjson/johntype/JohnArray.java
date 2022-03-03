package me.danikvitek.betterjson.johntype;

import com.google.gson.JsonArray;
import me.danikvitek.betterjson.johntype.primitive.JohnBoolean;
import me.danikvitek.betterjson.johntype.primitive.JohnNumber;
import me.danikvitek.betterjson.johntype.primitive.JohnString;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class JohnArray extends JohnElement implements Iterable<JohnElement>, FinalJohnElement {
    public static final JohnType TYPE = JohnType.ARRAY;

    private final @NotNull LinkedList<JohnElement> elements = new LinkedList<>();

    @Override
    public @NotNull JohnElement deepCopy() {
        if (!this.elements.isEmpty()) {
            JohnArray result = new JohnArray();
            this.elements.stream()
                    .map(JohnElement::deepCopy)
                    .forEach(result::add);
            return result;
        }
        return new JohnArray();
    }

    @Override
    public @NotNull JohnType getType() {
        return JohnArray.TYPE;
    }

    public void add(final @Nullable Boolean element) {
        this.elements.add(element == null ? JohnNull.INSTANCE : new JohnBoolean(element));
    }

    public void add(final @Nullable Number element) {
        this.elements.add(element == null ? JohnNull.INSTANCE : new JohnNumber(element));
    }

    public void add(final @Nullable String element) {
        this.elements.add(element == null ? JohnNull.INSTANCE : new JohnString(element));
    }

    public void add(final @Nullable Character element) {
        this.elements.add(element == null ? JohnNull.INSTANCE : new JohnString(element));
    }

    public void add(final @Nullable JohnElement element) {
        this.elements.add(element == null ? JohnNull.INSTANCE : element);
    }

    public void addNull() {
        this.elements.add(JohnNull.INSTANCE);
    }

    public void addAll(final @NotNull JohnArray array) {
        this.elements.addAll(array.elements);
    }

    public void addAll(final @NotNull Collection<JohnElement> collection) {
        this.elements.addAll(collection);
    }

    public void set(final int index, final @Nullable Boolean element) {
        this.elements.set(index, element == null ? JohnNull.INSTANCE : new JohnBoolean(element));
    }

    public void set(final int index, final @Nullable Number element) {
        this.elements.set(index, element == null ? JohnNull.INSTANCE : new JohnNumber(element));
    }

    public void set(final int index, final @Nullable String element) {
        this.elements.set(index, element == null ? JohnNull.INSTANCE : new JohnString(element));
    }

    public void set(final int index, final @Nullable Character element) {
        this.elements.set(index, element == null ? JohnNull.INSTANCE : new JohnString(element));
    }

    public void set(final int index, final @Nullable JohnElement element) {
        this.elements.set(index, element == null ? JohnNull.INSTANCE : element);
    }

    public void setNull(final int index) {
        this.elements.set(index, JohnNull.INSTANCE);
    }

    public void remove(final int index) {
        this.elements.remove(index);
    }

    public void remove(final @NotNull JohnElement element) {
        this.elements.remove(element);
    }

    public boolean contains(final @NotNull JohnElement element) {
        return this.elements.contains(element);
    }

    public int size() {
        return this.elements.size();
    }

    public boolean isEmpty() {
        return this.elements.isEmpty();
    }

    public JohnElement get(final int index) {
        return this.elements.get(index);
    }

    @NotNull
    @Override
    public Iterator<JohnElement> iterator() {
        return elements.iterator();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JohnArray that)) return false;

        return this.elements.equals(that.elements);
    }

    @Override
    public int hashCode() {
        return this.elements.hashCode();
    }

    @Override
    public JsonArray toJson() {
        final var result = new JsonArray(this.elements.size());
        this.elements.forEach(element -> result.add(element.toJson()));
        return result;
    }

    @Override
    public @NotNull Collection<JohnElement> values() {
        return this.elements;
    }

    @Override
    public @NotNull List<Map.Entry<String, JohnElement>> entries() {
        AtomicInteger i = new AtomicInteger();
        return this.elements.stream()
                .map(element -> Map.entry(i.getAndIncrement(), element))
                .map(entry -> Map.entry(entry.getKey().toString(), entry.getValue()))
                .toList();
    }
}
