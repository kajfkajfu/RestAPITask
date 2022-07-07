package utils;

import models.UserData;

public class UserDataUtils {

    public static UserData getRandomUser() {
        return new UserData(101, 1, StringUtils.getRandomData(JsonUtils.getTestValue("/titleLength"))
                , JsonUtils.getTestValue("/bodyLength"));
    }
}
