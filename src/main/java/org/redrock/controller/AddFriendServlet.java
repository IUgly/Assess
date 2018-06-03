package org.redrock.controller;

import org.redrock.bean.User;
import org.redrock.service.FriendService;
import org.redrock.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ugly
 */
@WebServlet(name = "AddFriendServlet", urlPatterns = "/AddFriendServlet")
public class AddFriendServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        UserService userService = new UserService();
        FriendService friendService = new FriendService();
        HttpSession session = req.getSession();

        String bOpenid = req.getParameter("bOpenid");
        String aOpenid = (String) req.getSession().getAttribute("openid");

        if (friendService.friendRelation(aOpenid, bOpenid)==false){
            User userA = userService.getUser(aOpenid);
            User userB = userService.getUser(bOpenid);
            if (userB == null) {
            req.getSession().setAttribute("AddFriendMsg", "查无此用户");
        } else {
            Map<String, Object> fieldMap = new HashMap<String, Object>();
            fieldMap.put("anickname", userA.getNickname());
            fieldMap.put("bnickname", userB.getNickname());
            fieldMap.put("aopenid", userA.getOpenid());
            fieldMap.put("bopenid", userB.getOpenid());
            fieldMap.put("aimgurl", userA.getImgurl());
            fieldMap.put("bimgurl", userB.getImgurl());

            if (friendService.addFriend(fieldMap)){
                req.getSession().setAttribute("AddFriendMsg", "添加成功");
            }else {
                req.getSession().setAttribute("AddFriendMsg", "添加失败");
            }
        }
    }
    else {
            req.getSession().setAttribute("AddFriendMsg", "已经是好友，无需重复添加");
        }

        String msg= (String) req.getSession().getAttribute("AddFriendMsg");
        System.out.println(msg);

        req.getRequestDispatcher("Servlet").forward(req, resp);
    }
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws IOException,ServletException{
        doPost(req, resp);
    }
}
