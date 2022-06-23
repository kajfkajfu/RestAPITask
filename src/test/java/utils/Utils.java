package utils;
import models.Address;
import models.Company;
import models.Geo;
import models.User;

import java.util.Random;

public class Utils {

    public static User getExpectedUser() {
        return new User(5, "Chelsey Dietrich", "Kamren", "Lucio_Hettinger@annie.ca",
                new Address("Skiles Walks", "Suite 351", "Roscoeview", "33263",
                        new Geo("-31.8129", "62.5342"))
                , "(254)954-1289", "demarco.info"
                , new Company("Keebler LLC", "User-centric fault-tolerant solution"
                , "revolutionize end-to-end systems"));
    }

    public static String getRandomData(String dataLength) {
        String alphabet = " 0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIKLMNOPQRSTUVWXYZ";
        StringBuilder str = new StringBuilder();
        Random random = new Random();
        String result = "";

        for (int i = 0; i < Integer.parseInt(dataLength); i++) {
            int index = random.nextInt(alphabet.length());
            char randomChar = alphabet.charAt(index);
            str.append(randomChar);
        }
        result = str.toString();
        return result;
    }
}
