import models.User;
import models.UserData;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.JsonUtils;
import utils.ListUtils;
import utils.RestAPIUtils;
import utils.Utils;
import java.util.List;

public class test {

    @Test
    private void shouldReturnSortedListWhenMakeGetRequest() {
        Assert.assertEquals(RestAPIUtils.getStatusCode(JsonUtils.getConfigValue("/postsURL")), 200);
        List<UserData> userData = RestAPIUtils.getUserDataList(JsonUtils.getConfigValue("/postsURL"));
        Assert.assertTrue(ListUtils.isUsersListSortedById(userData), "users list didn't sort by id");
        Assert.assertTrue(JsonUtils.isBodyListWithUsersDataInResponseJson(RestAPIUtils.getResponse(
                        JsonUtils.getConfigValue("/postsURL")))
                , "body list is not json");
    }

    @Test
    private void shouldReturnCorrectInformationAboutUserDataWhenMakeGetRequest() {
        Assert.assertEquals(RestAPIUtils.getStatusCode(JsonUtils.getConfigValue("/posts99URL"))
                , Integer.parseInt(JsonUtils.getTestValue("/response200")));
        Assert.assertEquals(RestAPIUtils.getUserData().getUserId()
                , Integer.parseInt(JsonUtils.getTestValue("/userDataUserId")));
        Assert.assertEquals(RestAPIUtils.getUserData().getId()
                , Integer.parseInt(JsonUtils.getTestValue("/userDataId")));
        Assert.assertFalse(RestAPIUtils.getUserData().getBody().isEmpty());
    }

    @Test
    private void shouldReturnEmptyBodyWhenMakeGetRequest() {
        Assert.assertEquals(RestAPIUtils.getStatusCode(JsonUtils.getConfigValue("/post150URL"))
                , Integer.parseInt(JsonUtils.getTestValue("/response404")));
        Assert.assertEquals(RestAPIUtils.getBodyResponse(JsonUtils.getConfigValue("/post150URL"))
                , JsonUtils.getTestValue("/emptyBody"));
    }

    @Test
    private void shouldReturnCorrectInformationAboutUserAfterMakePostRequest() {
        Assert.assertEquals(RestAPIUtils.postStatusCode(JsonUtils.getConfigValue("/postsURL"))
                , Integer.parseInt(JsonUtils.getTestValue("response201")));
        String randomTitle = Utils.getRandomData(JsonUtils.getTestValue("/titleLength"));
        String randomBody = Utils.getRandomData(JsonUtils.getTestValue("/bodyLength"));
        UserData expectedUser = new UserData(Integer.parseInt(JsonUtils.getTestValue("/userUserId"))
                , Integer.parseInt(JsonUtils.getTestValue("/userId")), randomTitle, randomBody);
        UserData actualUser = RestAPIUtils.postRequest(JsonUtils.getConfigValue("/postsURL"), expectedUser);
        Assert.assertEquals(actualUser, expectedUser);
    }

    @Test
    private void shouldReturnSpecifiedUserWhenMakeGetRequest() {
        Assert.assertEquals(RestAPIUtils.getStatusCode(JsonUtils.getConfigValue("/usersURL"))
                , Integer.parseInt(JsonUtils.getTestValue("/response200")));
        Assert.assertTrue(JsonUtils.isBodyListWithUsersInResponseJson(
                RestAPIUtils.getResponse(JsonUtils.getConfigValue("/usersURL"))));
        User user = RestAPIUtils.getUserList(JsonUtils.getConfigValue("/usersURL"))
                .get(Integer.parseInt(JsonUtils.getTestValue("/userIndex")));
        User userNew = Utils.getExpectedUser();
        Assert.assertEquals(user, userNew);
    }

    @Test
    private void shouldReturnSameUserLikeInPreviousStepWhenMakeGetRequest() {
        Assert.assertEquals(RestAPIUtils.getStatusCode(JsonUtils.getConfigValue("/users5URL"))
                , Integer.parseInt(JsonUtils.getTestValue("/response200")));
        User actualUser = RestAPIUtils.getUser(JsonUtils.getConfigValue("/users5URL"));
        User expectedUser = RestAPIUtils.getUserList("users").get(
                Integer.parseInt(JsonUtils.getTestValue("/userIndex")));
        Assert.assertEquals(actualUser, expectedUser);
    }
}
