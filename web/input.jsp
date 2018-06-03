<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>添加好友</title>
</head>
<body>
<table height="60" align="center">

</table>
<form action="/AddFriendServlet" method="get">
    <table border="1" align="center"  style="height: 100%;width: 100%" >
        <tr>
            <td height="40">查询好友（输入openid）</td>
            <td><input type="text"  name="bOpenid"
                style="height: 40px;width:300px"></td>
        </tr>
        <tr align="center">
            <td  height="40" colspan="3">
                <input type="submit" value="Submit"
                       style="height: 35px;width:70px " />
                <input type="reset"  value="Reset"
                       style="height: 35px;width:70px ">
            </td>
            <button style="width:100px;height:100px"><a href="info.jsp">返回主页</a></button>
        </tr>
    </table>
</form>
</body>
</html>
