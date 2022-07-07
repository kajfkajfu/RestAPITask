package utils;

import models.*;

public class UserUtils {

    public static User getExpectedUser() {
        return new User(5, "Chelsey Dietrich", "Kamren", "Lucio_Hettinger@annie.ca",
                new Address("Skiles Walks", "Suite 351", "Roscoeview", "33263",
                        new Geo("-31.8129", "62.5342"))
                , "(254)954-1289", "demarco.info"
                , new Company("Keebler LLC", "User-centric fault-tolerant solution"
                , "revolutionize end-to-end systems"));
    }
}
