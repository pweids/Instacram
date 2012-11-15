package com.instacram.servlet;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mybeans.dao.DAOException;

import com.instacram.dao.UserDAO;
import com.instacram.dto.User;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private UserDAO dao;
	private static final long serialVersionUID = 1L;
	
	Pattern regex = Pattern.compile("\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b");
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username, password, email;
		User usr;
		username = request.getParameter("username");
		password = request.getParameter("password");
		email = request.getParameter("email");
		
		
		try {
			dao = new UserDAO();
			usr = dao.lookup(username);
			
			if(!regex.matcher(email).find() || usr != null) {
				response.sendRedirect("register.jsp");
				return;
			}
			else {
				usr = dao.addUser(username, password, email);
				request.getSession().setAttribute("user", usr);
				response.sendRedirect("./mycourses");
				return;
			}
			
		} catch (DAOException e) {
			e.printStackTrace();
		}
		
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher disp;
		disp = getServletContext().getRequestDispatcher("/register.jsp");
		disp.forward(request, response);
	}
}
