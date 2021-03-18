<%-- 
    Document   : updateUserInfo
    Created on : Mar 13, 2021, 1:12:21 PM
    Author     : Dang Minh Quan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <h2>Update your information</h2>
        <form action="UpdateUserInfoServlet" method="POST">
            <input type="hidden" name="submitted" value="true">
            <table>
                <tbody>
                    <tr>
                        <td>ID:</td>
                        <td><input type="text" name="id" value="${sessionScope.user.userID}" readonly ></td>
                    </tr>
                    <tr>
                        <td>Full name: </td>
                        <td><input type="text" name="fullName" value="${sessionScope.user.fullName}"></td>
                    </tr>
                    <tr>
                        <td></td>
                        <c:if test="${param.submitted && empty param.fullName}">
                            <td class="fault">Full name is not allowed to be null !!</td>
                        </c:if>
                    </tr>
                    <tr>
                        <td>Address: </td>
                        <td><input type="text" name="address" value="${sessionScope.user.address}"></td>                           
                    </tr>
                    <tr>
                        <td></td>
                        <c:if test="${param.submitted && empty param.address}">
                            <td class="fault">Address is not allowed to be null !!</td>
                        </c:if>
                    </tr>
                    <tr>
                        <td>Email: </td>
                        <td><input type="email" name="email" value="${sessionScope.user.email}"></td>
                    </tr>
                    <tr>
                        <td></td>
                        <c:if test="${param.submitted && empty param.email}">
                            <td class="fault">Email is not allowed to be null !!</td>
                        </c:if>
                    </tr>
                    <tr>
                        <td>Phone number: </td>
                        <td><input type="number" name="phoneNumber" value="${sessionScope.user.phoneNumber}"></td>
                    </tr>
                    <tr>
                        <td></td>
                        <c:if test="${param.submitted && empty param.phoneNumber}">
                            <td class="fault">Phone number is not allowed to be null !!</td>
                        </c:if>
                    </tr>
                    <tr>
                        <td></td>
                        <td><button id="submit" type="submit">Update</button></td>
                    </tr>
                </tbody>
            </table>
        </form>
    </body>
</html>
<script>
    const submitBtn = document.getElementById("submit");
    submitBtn.addEventListener('click',(e)=>{
        if(!confirm("Are you sure to update information ?")){
            e.preventDefault();
        }
    })
</script>