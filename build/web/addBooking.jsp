<%-- 
    Document   : addBooking
    Created on : Mar 17, 2021, 12:32:29 AM
    Author     : ADMIN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="DTO.Schedule"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Schedule Listing Page</title>
    </head>
    <body>
        <form action="BookingController?action=viewScheduleByContent" method="POST">
            <table>
                <tr>
                    <td>Day: </td>
                    <td><input type="date" placeholder="dd-mm-yyyy" min="${requestScope.minDate}" max="2021-12-31" name="fromDate"></td>
                    <td>
                        Disease: 
                        <select name="content" id="">
    
                            <option value="${requestScope.scheduleItem.detail.content}">${requestScope.scheduleItem.detail.content}</option>
                            <c:forEach var="listItem" items="${requestScope.diseaseList}"> 
                                <c:choose >
                                    <c:when test="${listItem != requestScope.scheduleItem.detail.content}">
                                        <option value="${listItem}">${listItem}</option>
                                    </c:when>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </td>
                    <td><button type="submit">Search</button></td>
                </tr>
            </table>
        </form>
        <c:choose>
            <c:when test="${empty requestScope.scheduleList}">
                <h1>${requestScope.msg}</h1>
            </c:when>
            <c:otherwise>
                <table border="1">
                    <tr>
                        <td>Room</td>
                        <td>Date</td>
                        <td>Time</td>
                        <td>Status</td>
                        <td>Doctor</td>
                        <td>Price</td>
                    </tr>
                    <c:forEach var="schedule" items="${requestScope.scheduleList}">
                        <tr>
                            <td>${schedule.room}</td>
                            <td>${schedule.date}</td>
                            <td>${schedule.time}</td>
                            <td>${schedule.status}</td>
                            <td>${schedule.detail.content}</td>
                            <td>${schedule.detail.price}</td>
                            <td><a href="BookingController?action=addBooking&sid=${schedule.scheduleID}&uid=${sessionScope.user.userID}">Book</a></td>
                        </tr>
                    </c:forEach>
                </table>
            </c:otherwise>
        </c:choose>
        <a href="landingPageUser.jsp">Return to home page</a>
    </body>
</html>
