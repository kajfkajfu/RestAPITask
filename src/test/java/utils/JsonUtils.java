package utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.restassured.response.ResponseBody;
import models.User;
import models.UserData;

import java.lang.reflect.Type;
import java.util.List;

import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;

public class JsonUtils {
    private static ISettingsFile settings;

    public static boolean isBodyListWithUsersDataInResponseJson(ResponseBody responseBody) {
        Gson gson = new Gson();
        Type type = new TypeToken<List<UserData>>() {
        }.getType();
        List<UserData> userDataList = gson.fromJson(responseBody.asString(), type);
        UserData firstUser = userDataList.get(0);
        return firstUser.getId().equals(1) && firstUser.getTitle()
                .equals("sunt aut facere repellat provident occaecati excepturi optio reprehenderit");
    }

    public static boolean isBodyListWithUsersInResponseJson(ResponseBody responseBody) {
        Gson gson = new Gson();
        Type type = new TypeToken<List<User>>() {
        }.getType();
        List<User> userList = gson.fromJson(responseBody.asString(), type);
        User firstUser = userList.get(4);
        return (firstUser.getId().equals(5) && firstUser.getEmail().equals("Lucio_Hettinger@annie.ca"));
    }

    public static String getConfigValue(String key) {
        settings = new JsonSettingsFile("configData.json");
        return settings.getValue(key).toString();
    }

    public static String getTestValue(String key) {
        settings = new JsonSettingsFile("testData.json");
        return settings.getValue(key).toString();
    }

}