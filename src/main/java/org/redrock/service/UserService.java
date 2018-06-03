package org.redrock.service;

import org.redrock.bean.Friend;
import org.redrock.bean.Message;
import org.redrock.bean.User;
import org.redrock.helper.DatabaseHelper;

import java.util.List;
import java.util.Map;

public class UserService {
    public User getUser(String openid){
        String sql = "select nickname,imgurl,city,sex,openid from user where openid = ?";
        return DatabaseHelper.queryEntity(User.class, sql, openid);
    }

    public boolean createUser(Map<String, Object> fieldMap){
        return DatabaseHelper.insertEntity(User.class, fieldMap);
    }

    public boolean sendMessage(Map<String, Object> fieldMap){
        return DatabaseHelper.insertEntity(Message.class, fieldMap);
    }

}
