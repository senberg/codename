package senberg.island;

import java.util.Random;

public class RandomHelper {
    static Random source = new Random();

    static boolean getRandomBoolean(){
        return source.nextBoolean();
    }
    static int getRandomInt(int max){
        return source.nextInt(max);
    }
}
