package utils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class GetPostUtils {

    public static Response doGet(String resourceName) {
        return given()
                .when()
                .contentType(ContentType.JSON)
                .get(JsonUtils.getConfigValue("/typicode") + resourceName)
                .then()
                .extract()
                .response();
    }

    public static Response doPost(String resourceName, Object obj) {
        return given()
                .when()
                .contentType(ContentType.JSON)
                .body(obj)
                .post(JsonUtils.getConfigValue("/typicode") + resourceName)
                .then()
                .extract()
                .response();
    }
}
