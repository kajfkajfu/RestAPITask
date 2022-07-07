package utils;

import java.util.Random;

public class StringUtils {
    private static final String ALPHABET =  " 0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIKLMNOPQRSTUVWXYZ";

    public static String getRandomData(String dataLength) {
        StringBuilder str = new StringBuilder();
        Random random = new Random();
        String result = "";
        for (int i = 0; i < Integer.parseInt(dataLength); i++) {
            int index = random.nextInt(ALPHABET.length());
            char randomChar = ALPHABET.charAt(index);
            str.append(randomChar);
        }
        result = str.toString();
        return result;
    }
}
