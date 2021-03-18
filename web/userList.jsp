<%-- 
    Document   : userList
    Created on : Mar 12, 2021, 2:49:23 PM
    Author     : ADMIN
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>List of User</title>
    </head>
    <body>
        <h1>List of User</h1>
        <table border="1">
            <c:choose>
                <c:when test="${empty requestScope.listU}">
                    <h1>There is currently no data to display</h1>
                </c:when>
                <c:otherwise>
                    <thead>
                        <tr>
                            <th>User ID</th>
                            <th>UserName</th>
                            <th>Password</th>
                            <th>Edit</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="user" items="${requestScope.listU}">
                            <tr>
                                <td>${user.user.userID}</td>
                                <td>${user.userName}</td>
                                <td>${user.password}</td>                               
                                <td><a href="UserController?action=viewDetails&id=${user.user.userID}">Details</a><a onclick="return conf()" href="UserController?action=delete&id=${user.user.userID}">Delete</a></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </c:otherwise>         
            </c:choose>
        </table>
        <a href="landingPageAdmin.jsp">Return to homepage</a>
        <script>
            function conf() {
                var r= confirm("Do you really want to DELETE?")
                return r;
            }
        </script>
    </body>
</html>
