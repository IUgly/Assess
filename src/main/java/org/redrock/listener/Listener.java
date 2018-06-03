package org.redrock.listener;

import org.redrock.bean.User;
import org.redrock.service.UserService;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.HashSet;
import java.util.Set;

@WebListener
public class Listener implements ServletContextListener,
        HttpSessionAttributeListener,HttpSessionListener{
    private ServletContext app;
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        servletContextEvent.getServletContext().
                setAttribute("allUsers", new HashSet<String>());
        this.app = servletContextEvent.getServletContext();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent httpSessionBindingEvent) {
        UserService userService = new UserService();
        Set<String> all = (Set<String>) this.app.getAttribute("allUsers");
        User user = userService.getUser(httpSessionBindingEvent.getValue().toString());
        if (user!=null) {
            all.add("<th><a href=input.jsp?bOpenid=" +
                    httpSessionBindingEvent.getValue().toString() + ">" +
                    user.getNickname()+"（OpenID为："+
                    httpSessionBindingEvent.getValue().toString()+")"+ "</a></th>");
            this.app.setAttribute("allUsers", all);
            System.out.println("+++++++++" + user.getNickname());
        }
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent httpSessionBindingEvent) {

    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent httpSessionBindingEvent) {

    }

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        Set<String> all = (Set<String>) this.app.getAttribute("allUsers");
        all.remove(httpSessionEvent.getSession().getAttribute("userid"));
        this.app.setAttribute("allUsers", all);
    }
}
