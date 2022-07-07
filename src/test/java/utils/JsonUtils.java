package utils;

import io.restassured.http.ContentType;
import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;

public class JsonUtils {
    private static ISettingsFile settings;
    private static final String TitleFromPosts = "sunt aut facere repellat provident occaecati excepturi optio reprehenderit";
    private static final String EmailFromUsers = "Lucio_Hettinger@annie.ca";

    public static boolean isContentTypeIsJson(String resource, ContentType contentType) {
        return GetPostUtils.doGet(resource).getContentType().contains(contentType.toString());
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