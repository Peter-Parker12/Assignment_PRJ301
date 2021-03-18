<%-- 
    Document   : scheduleForm
    Created on : Feb 20, 2021, 10:28:57 AM
    Author     : megap
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="DTO.Schedule"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Form Page</title>
    </head>
    <body>
    <h1>${requestScope.message}</h1>
    <form action="ScheduleController?action=${requestScope.action}&scheduleID=${requestScope.scheduleItem.scheduleID}" method="post">
            <table>
                <tr>
                    <td>Room </td>
                    <td>:</td>
                    <td><input type="text" name="scheduleRoom" id="" value="${requestScope.scheduleItem.room}"></td>
                </tr>
                <tr>
                    <td></td>
                    <td></td>
                    <td>${requestScope.roomAlert}</td>
                </tr>
                <tr>
                    <td>Time </td>
                    <td>:</td>
                    <td><select name="scheduleTime" id="">
                            <option value="${requestScope.scheduleItem.time}">${requestScope.scheduleItem.time}</option>
                            <c:forEach var="listItem" items="${requestScope.timeList}"> 
                                <c:choose >
                                    <c:when test="${listItem != requestScope.scheduleItem.time}">
                                        <option>${listItem}</option>
                                    </c:when>
                                </c:choose>
                            </c:forEach>
                    </select></td>
                </tr>
                <tr>
                    <td></td>
                    <td></td>
                    <td>${requestScope.timeAlert}</td>
                </tr>
                <tr>
                    <td>Date</td>
                    <td>:</td>
                    <td><input type="date" name="date" placeholder="DD-MM-YYYY" min="${requestScope.minDate}" max="2021-12-31" value="${requestScope.scheduleItem.date}"></td>
                </tr>
                <tr>
                    <td></td>
                    <td></td>
                    <td>${requestScope.dateAlert}</td>
                </tr>
                <tr>
                    <td>Status </td>
                    <td>:</td>
                    <td><select name="scheduleStatus" id="">
                            <c:choose>
                                <c:when test="${requestScope.shceduleItem.status == 'False'}">
                                    <option value="False">False/option>
                                    <option value="True">True</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="True">True</option>
                                    <option value=False"">False</option>
                                </c:otherwise>
                            </c:choose>
                    </select></td>
                </tr>
                <tr>
                    <td></td>
                    <td></td>
                    <td>${requestScope.statusAlert}</td>
                </tr>
                <tr>
                    <td>Doctor </td>
                    <td>:</td>
                    <td>
                        <select name="doctor" id="">
                            <option value="${requestScope.scheduleItem.detail.doctor}">${requestScope.scheduleItem.detail.doctor}</option>
                            <c:forEach var="listItem" items="${requestScope.doctorList}"> 
                                <c:choose >
                                    <c:when test="${listItem != requestScope.scheduleItem.detail.doctor}">
                                        <option>${listItem}</option>
                                    </c:when>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td></td>
                    <td>${requestScope.doctorAlert}</td>
                </tr>
                <tr>
                    <td>Content </td>
                    <td>:</td>
                    <td><select name="content" id="">
                            <option value="${requestScope.scheduleItem.detail.content}">${requestScope.scheduleItem.detail.content}</option>
                            <c:forEach var="listItem" items="${requestScope.diseaseList}"> 
                                <c:choose >
                                    <c:when test="${listItem != requestScope.scheduleItem.detail.content}">
                                        <option>${listItem}</option>
                                    </c:when>
                                </c:choose>
                            </c:forEach>
                    </select></td>
                </tr>
                <tr>
                    <td></td>
                    <td></td>
                    <td>${requestScope.contentAlert}</td>
                </tr>
                <tr>
                    <td>Price </td>
                    <td>:</td>
                    <td><input type="text" name="price" id="" value="${requestScope.scheduleItem.detail.price}"></td>
                </tr>
                <tr>
                    <td></td>
                    <td></td>
                    <td>${requestScope.priceAlert}</td>
                </tr>
                <tr>
                    <td><a href="landingPageAdmin.jsp">Return to home page</a></td>
                </tr>
                <tr>
                    <td><button type="submit">${requestScope.action}</button></td>
                </tr>
            </table>
        </form>
    </body>
</html>
