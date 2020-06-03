<%@ page import="model.User" %><%--
  Created by IntelliJ IDEA.
  User: Mari_ish
  Date: 26.05.2020
  Time: 16:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User</title>

</head>

<div align="center">
    <%
        User user = (User) request.getAttribute("user");
        out.println("<li>");
        out.println("ID: " + user.getId());
        out.println("<li>");
        out.println("Name: " + user.getName());
        out.println("<li>");
        out.println("Email: " + user.getEmail());
        out.println("<li>");
        out.println("Password: " + user.getPassword());
        out.println("<li>");
        out.println("Role: " + user.getRole());
        out.println("</ul>");

    %>
</div>
<div align="center">
    <p><tr></tr><tr><td></td><td></td><td><a href="/logout"><b>Logout</b></a></td></tr><p>
</div>
