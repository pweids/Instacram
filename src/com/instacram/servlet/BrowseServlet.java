package com.instacram.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mybeans.dao.DAOException;

import com.instacram.dao.CourseDAO;
import com.instacram.dao.MessageDAO;
import com.instacram.dao.UserCourseDAO;
import com.instacram.dto.Message;
import com.instacram.dto.User;
import com.instacram.dto.Course;

/**
 * Servlet implementation class BrowseServlet
 */
@WebServlet("/browse")
public class BrowseServlet extends HttpServlet {
	CourseDAO cdao;
	MessageDAO mdao;
	UserCourseDAO ucdao;
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User usr = (User)request.getSession().getAttribute("user");
		String coursename = request.getParameter("joinCourse");
		System.out.println(coursename);
		Course c;
		
		try {
			ucdao = new UserCourseDAO();
			cdao = new CourseDAO();
			c = cdao.lookup(coursename);
			ucdao.addUserCourse(usr.getUsername(), coursename);
			response.sendRedirect("./browse");
		} catch (DAOException e) {
			e.printStackTrace();
		}
		}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Normal load
		User usr = (User) request.getSession().getAttribute("user");
		if (usr == null) {
			response.sendRedirect("./login");
			return;
		}
		
		String searchtext = request.getParameter("s");
		String coursename = request.getParameter("coursename");
		ArrayList<Message> msgs;
		try {
			cdao = new CourseDAO();
			ArrayList<Course> allCourses = cdao.getAllCourses();
			request.setAttribute("allCourses", allCourses);
			
			mdao = new MessageDAO();
			if (searchtext != null && !searchtext.equals("")) {
				msgs = mdao.searchMessages(searchtext);
			} else if (coursename != null && !coursename.equals("")){
				msgs = mdao.getAllMessages(coursename);
			} else {
				msgs = mdao.getAllMessages();
			}
			
			request.setAttribute("messages", msgs);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		
		
		RequestDispatcher disp;
		disp = getServletContext().getRequestDispatcher("/browse.jsp");
		disp.forward(request, response);
	}
}
