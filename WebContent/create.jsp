<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
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
                <li><div id="active"><a href="create">Create a Course</a></div></li>
                <li><a href="browse">Browse All Courses</a></li>
                <li><a href="mycourses">My Courses</a></li>
            </ul>
        </div>
    </div>
<div class="wrapper">
    <form id="create-form" action="create" method="post">
    <h1>Create a course</h1>
    <div id="create-inputs">
        <input id="course-name" type="text" name="courseName" placeholder="Course Name" autofocus required>   
        <input id="instructor" type="text" name ="instructor" placeholder="Instructor (optional)">
                
    </div>
    <div id="create-actions">
        <input type="submit" id="submit" value="Create">
    </div>
    </form>
    <c:if test="${created == true}"><br /><h2 style="text-align:center">Course ${course.name} created successfully!</h2></c:if>
</div>    
    <div class="footer">
        <p>© 2012 Instacram, Inc.</p>
        <p>Logged in as: ${user.username}. <a href="index.jsp?loginattempt=">Logout</a></p>
    </div>

</body>
</html>
