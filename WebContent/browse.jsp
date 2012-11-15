<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*, com.instacram.dto.*, com.instacram.dao.*"%>
    
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
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
                <li><div id="active"><a href="browse">Browse All Courses</a></div></li>
                <li><a href="mycourses">My Courses</a></li>
            </ul>
        </div>
    </div>
<div class="wrapper">
    <div class="courseBar">
        <div id="courseTitle"><h1>All Courses</h1></div>
        <div class="courseList">
            <ul>
                
                <c:forEach var="course" items="${allCourses}">
                	<li <c:if test="${param.coursename == course.name}">id ="selected"</c:if>>
                		<a href="./browse?coursename=${course.name}">${course.name}</a>
                	</li>
                </c:forEach>
                
                <li class = "clear"></li>
            </ul>
        </div>
    </div>

        <form id = "joinCourse" action="browse" method="post" name="join">
            <c:if test="${param.coursename != null}">
            		${param.coursename}
            		<input type="hidden" name="joinCourse" value="${param.coursename}" >
            		<input type="submit" name="join" value="Join Course">
            </c:if>
        </form>
    	<form>
                <input id="search" type="text" name="mySearchBox" placeholder="Search">
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
</div>    
    <div class="footer">
        <p>© 2012 Instacram, Inc.</p>
        <p>Logged in as: ${user.username}. <a href="index.jsp?loginattempt=">Logout</a></p>
    </div>

</body>
</html>
