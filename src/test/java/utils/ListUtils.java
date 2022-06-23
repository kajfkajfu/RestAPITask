package utils;
import models.UserData;
import java.util.List;

public class ListUtils {

    public static boolean isUsersListSortedById(List<UserData> list) {
        int tmp = 0;
        for (UserData elem : list) {
            if (tmp < elem.getId()) {
                tmp = elem.getId();
            } else {
                return false;
            }
        }
        return true;
    }
}
