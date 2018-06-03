<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.*" %>
<%@ page import="org.redrock.bean.User" %>
<%@ page import="org.redrock.bean.Friend" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>AccessTest</title>
    <style type="text/css">
        .clearfix:after{content:"\20";display:block;height:0;clear:both;visibility:hidden}
        .clearfix{zoom:3}
.tabBar {border-bottom: 2px solid #222}
.tabBar span {background-color: #e8e8e8;cursor: pointer;display: inline-block;float: left;font-weight: bold;height: 30px;line-height: 30px;padding: 0 15px}
.tabBar span.current{background-color: #222;color: #fff}
.tabCon {display: none}
    </style>
    <script src="https://cdn.bootcss.com/jquery/1.10.2/jquery.min.js">
</script>
    <script>
        $(function(){
$.Huitab("#tab_demo .tabBar span","#tab_demo .tabCon","current","click","1")});
      jQuery.Huitab =function(tabBar,tabCon,class_name,tabEvent,i){
var $tab_menu=$(tabBar);
  // 初始化操作
  $tab_menu.removeClass(class_name);
  $(tabBar).eq(i).addClass(class_name);
  $(tabCon).hide();
  $(tabCon).eq(i).show();
  
  $tab_menu.bind(tabEvent,function(){
    $tab_menu.removeClass(class_name);
      $(this).addClass(class_name);
      var index=$tab_menu.index(this);
      $(tabCon).hide();
      $(tabCon).eq(index).show()})}  
    </script>
</head>
<body>
    <div id="tab_demo" class="HuiTab">
  <div class="tabBar clearfix"><span><a href="index.jsp"> 聊天室</a></span><span>好友</span><span>个人信息</span></div>
<%
    ArrayList list = (ArrayList) request.getAttribute("info");
    ArrayList friendList = (ArrayList) request.getAttribute("friendList");
%>
<div class="tabCon">  </div>
<div class="tabCon">
<table   border = 5px align = "center" style="height: 100%;
width: 100%">
    <tr width=450px>
        <th height="60">昵称(点击昵称单聊)</th>
        <th height="60">头像</th><br>
    </tr>
    <%
        if (friendList!= null){
        for(int i = 0;i<friendList.size();i++){
            Friend friend =(Friend) friendList.get(i);%>
    <tr>
        <th><a href=chat.jsp?ToFriend=<%=friend.getBopenid()%>><%=friend.getBnickname() %></a></th>
        <th><br><img src="<%=friend.getBimgurl()%>" alt="1" /></th>
        </tr>
            <% }}%>
</table>
</div>
<div class="tabCon">
<table border = 5px align = "center" style="height: 100%;
width:100%">
    <tr height=40>
        <th>昵称</th>
        <th>头像</th>
        <th>城市</th>
        <th>性别</th>
    </tr>
    <%
        if (list!=null){
        User user=(User) list.get(0);%>
    </tr>
    <th><%=user.getNickname() %></th>
    <th><br><img src="<%=user.getImgurl()%>" alt="1" /></th>
    <th><%=user.getCity()%></th>
    <th><%=user.getSex()%></th><br>
    <%}%>
    <tr>
        <%--<button style="width:100px;height:100px"><a href="input.html">添加好友</a></button>--%>
        <button style="width:100px;height:100px"><a href="input.jsp">添加好友</a></button>
    </tr>
</table>
</div>
</body>
</html>
