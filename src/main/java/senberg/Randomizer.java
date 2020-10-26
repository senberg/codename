package senberg;

import java.util.Random;

public class Randomizer {
    private static final Random source = new Random();

    public static int getInt() {
        return source.nextInt();
    }

    public static int getInt(int bound) {
        return source.nextInt(bound);
    }

    public static boolean getBoolean() {
        return source.nextBoolean();
    }

    public static float getFloat() {
        return source.nextFloat();
    }

    public static float nextFloat(float bound) {
        return source.nextFloat() * bound;
    }
}