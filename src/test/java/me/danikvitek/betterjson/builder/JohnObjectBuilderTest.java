package me.danikvitek.betterjson.builder;

import com.google.gson.JsonObject;
import me.danikvitek.betterjson.BetterJsonAPI;
import me.danikvitek.betterjson.johntype.JohnClass;
import me.danikvitek.betterjson.johntype.JohnClassObject;
import me.danikvitek.betterjson.johntype.primitive.JohnNumber;
import me.danikvitek.betterjson.johntype.primitive.JohnString;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class JohnObjectBuilderTest {

    private static JsonObject johnObjectFinal;

    @BeforeAll
    static void buildJohnObject() {
        johnObjectFinal = BetterJsonAPI.objectBuilder()
                .add("testString", "testString")
                .add("testClass", new JohnClassObject(new Cat(
                        new JohnString("Tom"),
                        new JohnNumber(4))
                ))
                .build()
                .toJohnJsonObject();
        System.out.println(johnObjectFinal);
    }

    @Test
    void hasHeader() {
        assertTrue(johnObjectFinal.getAsJsonObject("JOHN_HEADER").has("classes"));
    }

    private static final class Cat implements JohnClass {
        private final @NotNull JohnString name;
        private final @NotNull JohnNumber age;

        @Contract(pure = true)
        public Cat(@NotNull JohnString name, @NotNull JohnNumber age) {
            this.name = name;
            this.age = age;
        }

        @Contract(pure = true)
        public @NotNull JohnString getName() {
            return name;
        }

        @Contract(pure = true)
        public @NotNull JohnNumber getAge() {
            return age;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Cat that)) return false;

            return this.name.equals(that.name) && this.age.equals(that.age);
        }

        @Override
        public int hashCode() {
            int result = name.hashCode();
            result = 31 * result + age.hashCode();
            return result;
        }

        @Contract(pure = true)
        @Override
        public @NotNull String toString() {
            return "Cat{" +
                    "name=" + name +
                    ", age=" + age +
                    '}';
        }
    }
}