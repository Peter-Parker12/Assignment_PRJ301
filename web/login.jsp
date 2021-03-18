<%-- 
    Document   : login
    Created on : Mar 10, 2021, 3:53:42 PM
    Author     : Dang Minh Quan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Login Page</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <style>
            h2{
                text-align: center;
            }
            table{
                margin-left: 40%;
            }
            .fault{
                color: red;
                padding-left: 10px;
            }
        </style>
    </head>
    <body>
        <div>
            <h2>Login</h2>
            <form action="LoginServlet" method="POST">
                <table>
                    <tbody>
                    <input type="hidden" name="submitted" value="true"  />
                        <tr>
                            <th>Username:</th>
                            <c:set var="value" value=""></c:set>
                            <c:if test="${param.submitted && requestScope.incorrect && !empty param.userName }">
                                <c:set var="value" value="${param.userName}"></c:set>
                            </c:if>
                            <th><input type="text" name="userName" value="${value}"></th>
                        </tr>
                        
                        <tr>
                            <td></td>
                            <c:if test="${param.submitted && empty param.userName}">
                                <td class="fault">Username should not be null !!</td>
                            </c:if>
                            <c:if test="${param.submitted && requestScope.incorrect && !empty param.userName && !empty param.password }">
                                <td class="fault">Incorrect username !</td>
                            </c:if>
                        </tr>
                        <tr>
                            <th>Password:</th>
                            <th><input type="password" name="password"></th>
                        </tr>
                        <tr>
                            <td></td>
                            <c:if test="${param.submitted && empty param.pasword}">
                                <td class="fault">Password should not be null !!</td>
                            </c:if>
                            <c:if test="${param.submitted && requestScope.incorrect && !empty param.pasword  }">
                                <td class="fault">Incorrect password !</td>
                            </c:if>
                        </tr>
                        <tr>
                            <th><a href="register.jsp">Create new account</a></th>
                            <th><input type="submit" value="Login"></th>
                        </tr>
                    </tbody>
                </table>
            </form>
        </div>
    </body>
</html>
