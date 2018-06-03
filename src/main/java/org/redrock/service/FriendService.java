package org.redrock.service;

import org.redrock.bean.Friend;
import org.redrock.bean.Message;
import org.redrock.helper.DatabaseHelper;

import java.util.List;
import java.util.Map;

/**
 * @author ugly
 */
public class FriendService {

    public boolean addFriend(Map<String, Object> fieldMap){
        return DatabaseHelper.insertEntity(Friend.class, fieldMap);
    }

    public boolean friendRelation(String aOpenid, String bOpenid){
        FriendService friendService = new FriendService();
        boolean a = false;
        List<Friend> friend = friendService.getFriendList(aOpenid);
        for (int i=0; i< friend.size(); i++) {
            if (friend.get(i).getBopenid()==bOpenid){
                a=true;
            }
        }
        return a;
    }

    public List<Message> getMessageList(String sender, String receiver){
        String sql = "select content,time,sender from message where ((sender="
                +"\""+sender+"\""+"and receiver="
                +"\""+receiver+"\""+
                ") or (sender ="
                +"\""+receiver+"\""+"and receiver ="
                +"\""+sender+"\"))";
        return DatabaseHelper.queryEntityList(Message.class, sql);
    }

    public List<Friend> getFriendList(String openid){
        String sql = "select bnickname,bimgurl,bopenid from friend where aopenid ="
                + "\""+openid +"\"";
        return DatabaseHelper.queryEntityList(Friend.class, sql);
    }

}
