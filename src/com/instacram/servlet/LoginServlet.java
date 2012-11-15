package com.instacram.servlet;

import java.io.IOException;
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
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO dao;
	
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		System.out.println("called");
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username, password;
		username = request.getParameter("username");
		password = request.getParameter("password");
		com.instacram.dto.User usr;
		
		try {
			dao = new UserDAO();
			usr = dao.lookup(username);
			
			if (usr == null) {
				request.getSession().setAttribute("loginattempt", "exist");
				request.getSession().setAttribute("loggedIn", false);
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
				rd.forward(request, response);
				return;
			}
			else System.out.println(usr.toString());
			if (password.equals(usr.getPassword())){
				request.getSession().setAttribute("user", usr);
				request.getSession().setAttribute("loggedIn", true);
				response.sendRedirect("./mycourses");
				return;
			}
			else {
				System.out.println("wrong password. Got " + usr.getPassword() + ", expected " + password);
				request.getSession().setAttribute("loginattempt", "password");
				request.getSession().setAttribute("loggedIn", false);
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
				rd.forward(request, response);
				return;
			}
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getSession().getAttribute("user") != null) {
			response.sendRedirect("./mycourse");
		}
		RequestDispatcher disp;
		disp = getServletContext().getRequestDispatcher("/index.jsp");
		disp.forward(request, response);
	}
	
}
