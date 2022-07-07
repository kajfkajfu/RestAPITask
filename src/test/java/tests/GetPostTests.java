package tests;

import io.restassured.http.ContentType;
import models.User;
import models.UserData;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.*;

import java.util.List;

public class GetPostTests {
    private final int SuccessStatusCode200 = 200;
    private final int SuccessStatusCode201 = 201;
    private final int UnsuccessfulStatusCode404 = 404;

    @Test
    private void shouldReturnSortedListWhenMakeGetRequest() {
        Assert.assertEquals(RestAPIUtils.getStatusCode(JsonUtils.getConfigValue("/postsURL")), SuccessStatusCode200);
        List<UserData> userData = RestAPIUtils.getUserDataList(JsonUtils.getConfigValue("/postsURL"));
        Assert.assertTrue(ListUtils.isUsersListSortedById(userData), "users list didn't sort by id");
        Assert.assertTrue(JsonUtils.isContentTypeIsJson(JsonUtils.getConfigValue("/postsURL")
                , ContentType.JSON), "body list is not json");
    }

    @Test
    private void shouldReturnCorrectInformationAboutUserDataWhenMakeGetRequest() {
        Assert.assertEquals(RestAPIUtils.getStatusCode(JsonUtils.getConfigValue("/postsURL")
                + JsonUtils.getTestValue("/URL99")), SuccessStatusCode200);
        Assert.assertEquals(RestAPIUtils.getUserData(JsonUtils.getConfigValue("/postsURL")
                        + JsonUtils.getTestValue("/URL99")).getUserId()
                , Integer.parseInt(JsonUtils.getTestValue("/userDataUserId")));
        Assert.assertEquals(RestAPIUtils.getUserData(JsonUtils.getConfigValue("/postsURL")
                        + JsonUtils.getTestValue("/URL99")).getId()
                , Integer.parseInt(JsonUtils.getTestValue("/userDataId")));
        Assert.assertFalse(RestAPIUtils.getUserData(JsonUtils.getConfigValue("/postsURL")
                + JsonUtils.getTestValue("/URL99")).getBody().isEmpty());
    }

    @Test
    private void shouldReturnEmptyBodyWhenMakeGetRequest() {
        Assert.assertEquals(RestAPIUtils.getStatusCode(JsonUtils.getConfigValue("/postsURL")
                + JsonUtils.getTestValue("/URL150")), UnsuccessfulStatusCode404);
        Assert.assertEquals(RestAPIUtils.getBodyResponse(JsonUtils.getConfigValue("/postsURL")
                + JsonUtils.getTestValue("/URL150")), JsonUtils.getTestValue("/emptyBody"));
    }

    @Test
    private void shouldReturnCorrectInformationAboutUserAfterMakePostRequest() {
        Assert.assertEquals(RestAPIUtils.postStatusCode(JsonUtils.getConfigValue("/postsURL")
                , UserDataUtils.getRandomUser()), SuccessStatusCode201);
        String randomTitle = StringUtils.getRandomData(JsonUtils.getTestValue("/titleLength"));
        String randomBody = StringUtils.getRandomData(JsonUtils.getTestValue("/bodyLength"));
        UserData expectedUser = new UserData(Integer.parseInt(JsonUtils.getTestValue("/userUserId"))
                , Integer.parseInt(JsonUtils.getTestValue("/userId")), randomTitle, randomBody);
        UserData actualUser = RestAPIUtils.postRequest(JsonUtils.getConfigValue("/postsURL"), expectedUser);
        Assert.assertEquals(actualUser, expectedUser);
    }

    @Test
    private void shouldReturnSpecifiedUserWhenMakeGetRequest() {
        Assert.assertEquals(RestAPIUtils.getStatusCode(JsonUtils.getConfigValue("/usersURL")), SuccessStatusCode200);
        Assert.assertTrue(JsonUtils.isContentTypeIsJson(JsonUtils.getConfigValue("/usersURL"), ContentType.JSON));
        User user = RestAPIUtils.getUserList("users")
                .get(Integer.parseInt(JsonUtils.getTestValue("/userIndex")));
        User userNew = UserUtils.getExpectedUser();
        Assert.assertEquals(user, userNew);
    }

    @Test
    private void shouldReturnSameUserLikeInPreviousStepWhenMakeGetRequest() {
        Assert.assertEquals(RestAPIUtils.getStatusCode(JsonUtils.getConfigValue("/usersURL")
                + JsonUtils.getTestValue("/URL5")), SuccessStatusCode200);
        User actualUser = RestAPIUtils.getUser(JsonUtils.getConfigValue("/usersURL")
                + JsonUtils.getTestValue("/URL5"));
        System.out.println(actualUser);
        User expectedUser = RestAPIUtils.getUserList("users").get(
                Integer.parseInt(JsonUtils.getTestValue("/userIndex")));
        Assert.assertEquals(actualUser, expectedUser);
    }
}
