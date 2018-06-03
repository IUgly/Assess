<%@ page import="java.util.ArrayList" %>
<%@ page import="org.redrock.bean.User" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<!doctype html>
<%
    ArrayList list = (ArrayList) request.getSession().getAttribute("info");
    User user = (User) list.get(0);
%>
<html>
<head>
    <title>添加好友</title>
</head>
<body>
<form action="/input" method="post" target="_blank" >
    <table>
        <tr>
            <td width="104" height="25" align="center">输入好友Openid:</td>
            <td><input type="text" name="bOpenid"></td>
        </tr>
        <tr align="center" >
            <td height="25" colspan="3">
                <input type="reset" name="resetBtn" value="重置">
                <input type="submit" name="submitBtn" value="提交">
            </td>
        </tr>
    </table>
</form>
<tr>
    <th>昵称</th>
</tr>
</tr>
<th><%=user.getNickname() %></th>
<tr>
    <br>
    <img src="<%=user.getImgurl()%>" alt="1"/>
</body>
</html>