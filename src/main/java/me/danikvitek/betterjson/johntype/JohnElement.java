package me.danikvitek.betterjson.johntype;

import com.google.gson.JsonElement;
import me.danikvitek.betterjson.johntype.primitive.JohnPrimitive;
import org.jetbrains.annotations.NotNull;

public abstract class JohnElement {
    public boolean isJohnObject() {
        return this instanceof JohnObject;
    }

    public boolean isJohnClassObject() {
        return this instanceof JohnClassObject;
    }

    public boolean isJohnArray() {
        return this instanceof JohnArray;
    }

    public boolean isJohnPrimitive() {
        return this instanceof JohnPrimitive;
    }

    public boolean isJohnNull() {
        return this instanceof JohnNull;
    }

    public JohnArray getAsJohnArray() throws IllegalStateException {
        if (this.isJohnArray()) return (JohnArray) this;
        else throw new IllegalStateException("Not a JOHN array");
    }

    public JohnObject getAsJohnObject() throws IllegalStateException {
        if (this.isJohnObject()) return (JohnObject) this;
        else throw new IllegalStateException("Not a JOHN object");
    }

    public JohnClassObject getAsJohnClassObject() throws IllegalStateException {
        if (this.isJohnClassObject()) return (JohnClassObject) this;
        else throw new IllegalStateException("Not a JOHN class object");
    }

    @NotNull
    public abstract JohnElement deepCopy();

    @NotNull
    public abstract JohnType getType();

    public String getAsString() {
        throw new UnsupportedOperationException();
    }

    public Number getAsNumber() {
        throw new UnsupportedOperationException();
    }

    public Boolean getAsBoolean() {
        throw new UnsupportedOperationException();
    }

    public abstract JsonElement toJson();

    @Override
    public String toString() {
        return this.toJson().toString();
    }
}
