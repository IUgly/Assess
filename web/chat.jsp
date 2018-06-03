<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="org.redrock.bean.Friend" %>
<%@ page import="org.redrock.bean.Message" %>
<%@ page import="org.redrock.service.UserService" %>
<%
    response.setHeader("refresh", "10");
    String ToFriend = request.getParameter("ToFriend");
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<%
    List<Message> Msg = (List<Message>) request.getAttribute("MessageList");
    List<Message> list = (List<Message>) request.getSession().getAttribute("MessageList");
    UserService userService = new UserService();
    String friendID = request.getParameter("ToFriend");
%>
<head>
    <title>chat</title>
</head>
<table   border = 5px align = "center" style="height: 60%;
width: 100%">
    <tr width=100px>
        <td height="10">内容</td>
        <td height="10">发送人</td>
        <td height="10">时间</td><br>
    </tr>
    <%
        if (Msg!=null){
        for(int i = 0;i<Msg.size();i++){
    %>
    <tr>
        <td><%=Msg.get(i).getContent()%></td>
        <td><%=userService.getUser(Msg.get(i).getSender())%></td>
        <td><%=Msg.get(i).getTime()%></td>
    </tr>
    <% }}%>
    <body>
    <form action="/ChatServlet" method="get">
        输入信息:<input type="text" name="message" style="width:111px;height:60px"/>
        <input type="submit" value="发送" style="width:50px;height:30px"/>
        <input type="hidden" name="ToFriend" value="<%=ToFriend%>">
    </form>
    <th><%=userService.getUser(friendID).getNickname()%></th>>
    <th><br><img src="<%=userService.getUser(friendID).getImgurl()%>" alt="1" /></th>
    <button style="width:100px;height:100px"><a href="info.jsp">返回主页</a></button>
    </body>
</table>

</html>