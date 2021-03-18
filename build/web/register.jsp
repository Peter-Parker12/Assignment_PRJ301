<%-- 
    Document   : register
    Created on : Mar 11, 2021, 10:25:12 PM
    Author     : Dang Minh Quan
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Document</title>
        <style>
            .fault{
                color: red;
            }
        </style>
    </head>
    <body>
        <h2>Enter your information: </h2>
        <form action="RegisterServlet" method="POST">
            <input type="hidden" name="submited" value="true">
            <table>
                <tbody>
                    <tr>
                        <td>Full name: </td>
                        <td><input type="text" name="fullName" value="${param.fullName}" ></td>

                    </tr>
                    <tr>
                        <td></td>
                        <c:if test="${param.submited && empty param.fullName}">
                            <td class="fault">Full name is not allowed to be null </td>
                        </c:if>
                    </tr>
                    <tr>
                        <td>Address: </td>
                        <td><input type="text" name="address" value="${param.address}" ></td>

                    </tr>
                    <tr>
                        <td></td>
                        <c:if test="${param.submited && empty param.address}">
                            <td class="fault">Address is not allowed to be null </td>

                        </c:if>
                    </tr>
                    <tr>
                        <td>Phone number:</td>
                        <td><input type="number" name="phoneNumber" value="${param.phoneNumber}"></td>

                    </tr>
                    <tr>
                        <td></td>
                        <c:if test="${param.submited && empty param.phoneNumber}">
                            <td class="fault">Phone number is not allowed to be null </td>
                        </c:if>
                    </tr>
                    <tr>
                        <td>Email: </td>
                        <td><input type="email" name="email" value="${param.email}"></td>

                    </tr>
                    <tr>
                        <td></td>
                        <c:if test="${param.submited && empty param.email}">
                            <td class="fault">Email is not allowed to be null </td>
                        </c:if>
                    </tr>
                    <tr>
                        <td>Username: </td>
                        <td><input type="text" name="userName" value="${param.userName}"></td>
                    </tr>
                    <tr>
                        <td></td>
                        <c:if test="${param.submited && requestScope.existed}">
                            <td class="fault">User name existed !</td>
                        </c:if>
                        <c:if test="${param.submited && empty param.userName}">
                            <td class="fault">User name is not allowed to be null </td>
                        </c:if>
                    </tr>
                    <tr>
                        <td>Password: </td>
                        <td><input type="password" name="password" ></td>   
                    </tr>
                    <tr>
                        <td></td>
                         <c:if test="${param.submited && empty param.password}">
                             <td class="fault">Password is not allowed to be null </td>
                        </c:if>
                    </tr>
                    <tr>
                        <td></td>
                        <td><button type="submit">Register</button></td>
                    </tr>
                </tbody>
            </table>
        </form>
    </body>
</html>
