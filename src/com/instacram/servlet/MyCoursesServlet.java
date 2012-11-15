package com.instacram.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mybeans.dao.DAOException;

import com.instacram.dao.CourseDAO;
import com.instacram.dao.MessageDAO;
import com.instacram.dao.UserDAO;
import com.instacram.dto.Message;
import com.instacram.dto.User;
import com.instacram.dto.Course;

/**
 * Servlet implementation class MyCoursesServlet
 */
@WebServlet("/mycourses")
public class MyCoursesServlet extends HttpServlet {
	private MessageDAO mdao;
	private static final long serialVersionUID = 1L;
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String coursename = request.getParameter("postNoteClass");
		String content = request.getParameter("postNoteText");
		User usr = (User)request.getSession().getAttribute("user");
		
		if (coursename.equals("--Choose a class--") || content.equals("")) {
			System.out.println("==msgsrvlet==");
			response.sendRedirect("./mycourses");
			return;
		}
		
		Date date = new Date();
		
		try {
			mdao = new MessageDAO();
			mdao.addMessage(new Message(content, date, usr.getUsername(), coursename));
		} catch (DAOException e) {
			e.printStackTrace();
		}
		
		response.sendRedirect("./mycourses");
		return;
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Normal load
		User usr = (User) request.getSession().getAttribute("user");
		if (usr == null) {
			response.sendRedirect("./login");
			return;
		}
		
		//Course and Searching
		String searchtext = request.getParameter("s");
		String coursename = request.getParameter("coursename");
		ArrayList<Message> msgs;
		try {
			mdao = new MessageDAO();
			if (searchtext != null && !searchtext.equals("")) {
				msgs = mdao.searchMessages(searchtext);
			} else if (coursename != null && !coursename.equals("")){
				msgs = mdao.getAllMessages(coursename);
			} else {
				msgs = mdao.userMessages(usr);
			}
			request.setAttribute("messages", msgs);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		
		
		RequestDispatcher disp;
		disp = getServletContext().getRequestDispatcher("/myCourses.jsp");
		disp.forward(request, response);
	}
}

