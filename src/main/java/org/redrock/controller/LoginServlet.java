package org.redrock.controller;

import com.alibaba.fastjson.JSONObject;
import org.redrock.bean.Friend;
import org.redrock.bean.User;
import org.redrock.service.FriendService;
import org.redrock.service.UserService;
import org.redrock.utils.WechatUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author ugly
 */
@WebServlet(value = "/Servlet")
public class LoginServlet extends HttpServlet{
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException{
        UserService userService = new UserService();
        FriendService friendService = new FriendService();
        HttpSession session = req.getSession();

        String openid = (String) req.getSession().getAttribute("openid");
        String access_token = (String) req.getSession().getAttribute("access_token");

        if (userService.getUser(openid)==null){
            req.getSession().setAttribute("register", "No");
            JSONObject userInfo = WechatUtil.getUserInfo(openid, access_token);

            Map<String, Object> beanMap = WechatUtil.createUser(userInfo);
            boolean info = userService.createUser(beanMap);
            if (info){
                req.setAttribute("register", "register success");
            }else {
                req.setAttribute("register","register fail");
            }
        }else {
            req.setAttribute("register", "Yes");
            session.setAttribute("userid", openid);
        }
        User user = userService.getUser(openid);
        List<Friend> friendList = friendService.getFriendList(openid);

        List<User> list = new ArrayList<User>();
        list.add(user);
        req.setAttribute("info", list);
        req.getSession().setAttribute("info",list);

        req.setAttribute("friendList", friendList);
        req.getSession().setAttribute("friendList", friendList);

        String msg = (String) req.getAttribute("register");
        System.out.println(msg);
        req.getRequestDispatcher("info.jsp").forward(req, resp);
    }
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException{
        try {
            doPost(req, resp);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
