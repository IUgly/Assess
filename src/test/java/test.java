import com.google.gson.Gson;
import org.redrock.bean.Friend;
import org.redrock.bean.Message;
import org.redrock.service.FriendService;
import org.redrock.service.UserService;

import java.util.*;

import static java.net.URLDecoder.decode;

public class test {
    public static void main(String[] args) {
        UserService userService = new UserService();
        FriendService friendService = new FriendService();
        List<Message> list = friendService.getMessageList
                ("ov3380_5cdMfMP0SqJfZmcMqmg18","ov33805O1uXHKO2IiqmDwv0PVbp0");

        Gson gson = new Gson();
        String json = gson.toJson(list);
        System.out.println(json);


    }
}
