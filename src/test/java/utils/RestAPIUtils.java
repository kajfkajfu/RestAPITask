package utils;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import models.User;
import models.UserData;
import java.util.List;

import static io.restassured.RestAssured.given;

public class RestAPIUtils {

    public static List<UserData> getUserDataList(String resourceName) {
        return given()
                .when()
                .contentType(ContentType.JSON)
                .get("https://jsonplaceholder.typicode.com/" + resourceName)
                .then()
                .extract()
                .body()
                .jsonPath().getList(".", UserData.class);
    }

    public static List<User> getUserList(String resourceName) {
        return given()
                .when()
                .contentType(ContentType.JSON)
                .get("https://jsonplaceholder.typicode.com/" + resourceName)
                .then()
                .extract()
                .body()
                .jsonPath().getList(".", User.class);
    }

    public static int getStatusCode(String resourceName) {
        return given()
                .when()
                .get("https://jsonplaceholder.typicode.com/" + resourceName)
                .statusCode();
    }

    public static UserData getUserData() {
        return given()
                .when()
                .contentType(ContentType.JSON)
                .get("https://jsonplaceholder.typicode.com/posts/99")
                .as(UserData.class);
    }

    public static User getUser(String recourseName) {
        Response response = given()
                .contentType(ContentType.JSON)
                .get("https://jsonplaceholder.typicode.com/" + recourseName);
        return response.then().extract().as(User.class);
    }

    public static String getBodyResponse(String recourseName) {
        return given()
                .when()
                .contentType(ContentType.JSON)
                .get("https://jsonplaceholder.typicode.com/" + recourseName)
                .then().log().body()
                .extract().asString();
    }

    public static int postStatusCode(String resourceName) {
        return given()
                .body(new UserData(101, 1, "Random title", "Random body"))
                .when()
                .post("https://jsonplaceholder.typicode.com/" + resourceName)
                .statusCode();
    }

    public static ResponseBody getResponse(String resourceName) {
        return given()
                .when()
                .contentType(ContentType.JSON)
                .get("https://jsonplaceholder.typicode.com/" + resourceName)
                .then()
                .extract()
                .response().body();
    }

    public static UserData postRequest(String resourceName, UserData user) {
        Response response = given().contentType(ContentType.JSON)
                .body(user)
                .post("https://jsonplaceholder.typicode.com/" + resourceName);
        return response.then().extract().as(UserData.class);
    }
}
