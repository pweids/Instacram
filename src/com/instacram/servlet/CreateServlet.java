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
import com.instacram.dto.Course;
import com.instacram.dto.User;

/**
 * Servlet implementation class CreateServlet
 */
@WebServlet("/create")
public class CreateServlet extends HttpServlet {
	private CourseDAO cdao;
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String courseName = request.getParameter("courseName");
		String instructor = request.getParameter("instructor");
		User usr = (User)request.getSession().getAttribute("user");
		
		Course course = (instructor == null) ? new Course(courseName, usr.getUsername()) : new Course(courseName, instructor, usr.getUsername());
		try {
			cdao = new CourseDAO();
			cdao.addCourse(course);
			request.setAttribute("created", true);
			
			ArrayList<Course> courselist = (ArrayList<Course>)request.getSession().getAttribute("allCourses");
			if (courselist == null) {
				courselist = new ArrayList<Course>();
			}
			courselist.add(course);
			request.getSession().setAttribute("allCourses", courselist);
			
			RequestDispatcher disp;
			disp=getServletContext().getRequestDispatcher("/create.jsp");
			request.setAttribute("course", course);
			disp.forward(request, response);
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User usr = (User) request.getSession().getAttribute("user");
		if (usr == null) {
			response.sendRedirect("./login");
			return;
		}
		
		RequestDispatcher disp;
		disp = getServletContext().getRequestDispatcher("/create.jsp");
		disp.forward(request, response);
	}

}
