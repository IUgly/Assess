package org.redrock.controller;

import com.alibaba.fastjson.JSONObject;
import org.redrock.bean.Friend;
import org.redrock.bean.Message;
import org.redrock.bean.User;
import org.redrock.service.FriendService;
import org.redrock.service.UserService;
import org.redrock.utils.WechatUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@WebServlet(name = "ChatServlet", urlPatterns = "/ChatServlet")
public class ChatServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException{
        UserService userService = new UserService();

        String sender = (String) req.getSession().getAttribute("openid");
        String message = req.getParameter("message");
        String receiver = req.getParameter("ToFriend");
        if (message==null){
            req.setAttribute("sendMsg", "输入错误");
        }else {
            User r = userService.getUser(receiver);
            if (r == null) {
                req.setAttribute("sendMsg", "无此用户");
            } else {
                Map<String, Object> fieldMap = new HashMap<String, Object>();
                fieldMap.put("sender", userService.getUser(sender).getOpenid());
                fieldMap.put("receiver", r.getOpenid());
                fieldMap.put("content", message);
                fieldMap.put("time", new Date());
                if (userService.sendMessage(fieldMap)){
                    req.setAttribute("sendMsg", "发送成功");
                }else {
                    req.setAttribute("sendMsg", "发送失败");
                }
            }
        }
        String msg = (String) req.getAttribute("sendMsg");
        System.out.println(msg);
        req.getRequestDispatcher("chat.jsp").forward(req, resp);
    }
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException,IOException {
        FriendService friendService = new FriendService();
        String sender = (String) req.getSession().getAttribute("openid");
        String message = req.getParameter("message");
        String receiver = req.getParameter("ToFriend");

        List<Message> messageList = friendService.getMessageList(sender, receiver);
        req.getSession().setAttribute("MessageList", messageList);
        req.setAttribute("MessageList", messageList);

        doPost(req, resp);
    }
}

