<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*, com.instacram.dto.User, com.instacram.dto.*, com.instacram.dao.*"%>
    
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>

<html>
<head>
    <title>Instacram</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>

<body>
    <div class = "header">
        <div id="header-title">Instacram</div>
        <div id="header-nav">
            <ul>
                <li><a href="create">Create a Course</a></li>
                <li><a href="browse">Browse All Courses</a></li>
                <li><div id="active"><a href="mycourses">My Courses</a></div></li>
            </ul>
        </div>
    </div>
<div class="wrapper">
    <div class="courseBar">
        <div id="courseTitle"><h1>My Courses</h1></div>
        <div class="courseList">
            <ul>
            <li style="text-align: center"><a href="myCourses.jsp">All Courses</a></li>
                <c:forEach var="course" items="${user.courses}">
                	<li <c:if test="${param.coursename == course.name}">id ="selected"</c:if>>
                		<a href="./mycourses?coursename=${course.name}">${course.name}</a>
                	</li>
                </c:forEach>
                <li></li>
            </ul>
        </div>
    </div>

        <form action = "mycourses" id = "noteUpdater" method="post">
            <input id="update-field" type="text" name="postNoteText" placeholder="Post a note">
                <select name="postNoteClass">
                    <option>--Choose a class--</option>
                    
                	<c:forEach var="course" items="${user.courses}">
                		<option <c:if test="${param.coursename == course.name}"> selected="selected"</c:if>>${course.name}</option>
                	</c:forEach>
                	
                </select>
                <input type="submit" value="Post">
        </form>
        <form action="mycourses" method="get">
                <input id="search" type="text" name="s" placeholder="Search">
                <input type="submit" value="Go">
        </form>
    

<div id = "feed">
	<c:forEach var="msg" items="${messages}">
    <div class = "feedElement">
        <div class = "posterName">${msg.username}</div>
        <div class = "timeStamp">${msg.date}</div>
        <div class = "message">${msg.content }</div>
        <div class = "className">${msg.coursename}</div>
    </div>
    </c:forEach>
</div>
<div id = "clear"></div>
</div>
    <div class="footer">
        <p>© 2012 Instacram, Inc.</p>
        <p>Logged in as: ${user.username}. <a href="index.jsp?loginattempt=">Logout</a></p>
    </div>

</body>
</html>
