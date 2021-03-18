<%-- 
    Document   : userDetails
    Created on : Mar 12, 2021, 5:10:41 PM
    Author     : ADMIN
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Details</title>
    </head>
    <body>
        <h1>User Details</h1>
        <table border="0" width="3" cellspacing="20">
            <thead>
                <tr>
                    <th></th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>User ID</td>
                    <td>${user.user.userID}</td>
                </tr>
                <tr>
                    <td>Full Name</td>
                    <td>${user.user.fullName}</td>
                </tr>
                <tr>
                    <td>Address</td>
                    <td>${user.user.address}</td>
                </tr>
                <tr>
                    <td>Phone Number</td>
                    <td>${user.user.phoneNumber}</td>
                </tr>
                <tr>
                    <td>Email</td>
                    <td>${user.user.email}</td>
                </tr>
            </tbody>
        </table>
                <a href="UserController?action=viewList">Return to list User</a>
    </body>
</html>
