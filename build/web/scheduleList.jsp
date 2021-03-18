<%-- 
    Document   : ScheduleList
    Created on : Feb 19, 2021, 8:30:46 PM
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
        <title>Schedule Listing Page</title>
    </head>
    <body>
        <form action="ScheduleController?action=viewScheduleList" method="POST">
            <table>
                <tr>
                    <td>From day: </td>
                    <td><input type="date" placeholder="dd-mm-yyyy" min="${requestScope.minDate}" max="2021-12-31" name="fromDate"></td>
                    <td>To day: </td>
                    <td><input type="date" placeholder="dd-mm-yyyy" min="${requestScope.minDate}" max="2021-12-31" name="toDate"></td>
                    <td><button type="submit">Search</button></td>
                </tr>
            </table>
        </form>
        <c:choose>
            <c:when test="${empty requestScope.scheduleList}">
                <h1>There is currently no data to display</h1>
            </c:when>
            <c:otherwise>
                <table border="1">
                    <tr>
                        <td>Room</td>
                        <td>Date</td>
                        <td>Time</td>
                        <td>Status</td>
                        <td>Doctor</td>
                        <td>Diseases</td>
                        <td>Price</td>
                    </tr>
                    <c:forEach var="schedule" items="${requestScope.scheduleList}">
                        <tr>
                            <td>${schedule.room}</td>
                            <td>${schedule.date}</td>
                            <td>${schedule.time}</td>
                            <td>${schedule.status}</td>
                            <td>${schedule.detail.doctor}</td>
                            <td>${schedule.detail.content}</td>
                            <td>${schedule.detail.price}</td>
                            <td><a href="ScheduleController?action=deleteSchedule&scheduleID=${schedule.scheduleID}" onclick="return confirm('Do you want to delte the chosen Schedule')">Delete</a> </td>
                            <td><a href="ScheduleController?action=updateSchedule&scheduleID=${schedule.scheduleID}">Update</a></td>
                        </tr>
                    </c:forEach>
                </table>
            </c:otherwise>
        </c:choose>
        <a href="landingPageAdmin.jsp">Return to home page</a>
    </body>
</html>
