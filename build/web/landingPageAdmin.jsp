<%-- 
    Document   : landingPage
    Created on : Feb 19, 2021, 8:43:21 PM
    Author     : megap
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
    </head>
    <body>
        <h1>Doctor's room management system</h1>
        <h3>Welcome to doctor's room management website</h3>
        <p>You will find this site a convenient place to manage your booking system</p>
        <p>Below are some action you can do with the system. Please try it yourself: </p>
        <ul>
            <li><a href="UserController?action=viewList">View list of Users</a></li>
            <li><a href="ScheduleController?action=viewScheduleList">View list of schedules</a></li>
            <li><a href="ScheduleController?action=addSchedule">Add a new schedule</a></li>
            <li><a href="UserController?action=logOut">Logout</a></li>
        </ul>
    </body>
</html>
