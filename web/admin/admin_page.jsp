<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <title>User Management Application</title>
</head>
<body>
<div align="center">
  <h1>User Management Application</h1>
  <h2>
    <a href="/admin/add">Add User</a>
    &nbsp;&nbsp;&nbsp;
    <a href="/admin">List All Users</a>

  </h2>
</div>
<div align="center">
  <table border="1" cellpadding="5">
    <caption><h2>List of Users</h2></caption>
    <tr>
      <th>ID</th>
      <th>Name</th>
      <th>Email</th>
      <th>Password</th>
      <th>Role</th>
      <th>Actions</th>
    </tr>
    <c:forEach var="user" items="${users}">
      <tr>
        <td><c:out value="${user.id}" /></td>
        <td><c:out value="${user.name}" /></td>
        <td><c:out value="${user.email}" /></td>
        <td><c:out value="${user.password}" /></td>
        <td><c:out value="${user.role}" /></td>
        <td>
          <a href="/admin/edit?id=<c:out value='${user.id}' />">Edit</a>
          &nbsp;&nbsp;&nbsp;&nbsp;
          <a href="/admin/delete?id=<c:out value='${user.id}' />">Delete</a>
        </td>
      </tr>
    </c:forEach>
  </table>
  <tr></tr><tr><td></td><td></td><td><a href="/logout"><b>Logout</b></a></td></tr>
</div>
</body>
</html>