<%-- 
    Document   : bookedList
    Created on : Mar 16, 2021, 5:23:48 PM
    Author     : ADMIN
--%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Booking List</title>
    </head>
    <body>
        <h1>Your booking list</h1>
        <form action="BookingController?action=viewBooking&id=${sessionScope.user.userID}" method="POST">
            <table>
                <tr>
                    <td>From Day: </td>
                    <td><input type="date" placeholder="dd-mm-yyyy"  max="2021-12-31" name="fromDate"></td>
                    <td>To day: </td>
                    <td><input type="date" placeholder="dd-mm-yyyy"  max="2021-12-31" name="toDate"></td>
                    <td><button type="submit">Search</button></td>
                </tr>
            </table>
        </form>
    <c:choose>
        <c:when test="${empty requestScope.bookingList}">
            <h1>There is currently no booking</h1>
        </c:when>
        <c:otherwise>
            <table border="1">
                <tr>
                    <td>Room</td>
                    <td>Time</td>
                    <td>Date</td>
                    <td>Doctor</td>
                    <td>Diseases</td>
                    <td>Price</td>
                    <td>Status</td>
                </tr>
                <c:forEach var="booking" items="${requestScope.bookingList}">
                    <tr>
                        <td>${booking.schedule.room}</td>
                        <td>${booking.schedule.time}</td>
                        <td>${booking.schedule.date}</td>
                        <td>${booking.schedule.detail.doctor}</td>
                        <td>${booking.schedule.detail.content}</td>
                        <td>${booking.schedule.detail.price}</td>
                        <td>${booking.status}</td>
                    </tr>
                </c:forEach>
            </table>
        </c:otherwise>
    </c:choose>
    <a href="BookingController?action=viewScheduleByContent">Add a booking</a> <br>
    <a href="landingPageUser.jsp">Return to home page</a>
</body>
</html>
