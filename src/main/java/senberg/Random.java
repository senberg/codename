package senberg;

public class Random {
    private static java.util.Random source = new java.util.Random();

    public static int nextInt(){
        return source.nextInt();
    }

    public static int nextInt(int bound){
        return source.nextInt(bound);
    }

    public static boolean nextBoolean(){
        return source.nextBoolean();
    }

    public static float nextFloat(){
        return source.nextFloat();
    }

    public static float nextFloat(float min, float interval){
        return min + source.nextFloat() * interval;
    }
}