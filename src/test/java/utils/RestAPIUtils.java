package utils;

import models.User;
import models.UserData;
import java.util.List;

public class RestAPIUtils {

    public static List<UserData> getUserDataList(String resourceName) {
        return GetPostUtils.doGet(resourceName).jsonPath().getList(".", UserData.class);
    }

    public static List<User> getUserList(String resourceName) {
        return GetPostUtils.doGet(resourceName).jsonPath().getList(".", User.class);
    }

    public static UserData getUserData(String recourseName) {
        return GetPostUtils.doGet(recourseName).as(UserData.class);
    }

    public static User getUser(String recourseName) {
        return GetPostUtils.doGet(recourseName).as(User.class);
    }

    public static int getStatusCode(String resourceName) {
        return GetPostUtils.doGet(resourceName).statusCode();
    }

    public static int postStatusCode(String resourceName, Object obj) {
        return GetPostUtils.doPost(resourceName, obj).statusCode();
    }

    public static String getBodyResponse(String recourseName) {
        return GetPostUtils.doGet(recourseName).then().log().body().extract().asString();
    }

    public static UserData postRequest(String resourceName, Object obj) {
        return GetPostUtils.doPost(resourceName, obj).as(UserData.class);
    }
}
