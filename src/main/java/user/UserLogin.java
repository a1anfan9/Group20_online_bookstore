package user;

import user.UserDao;
import user.UserDaoImpl;

import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dbcon.DBcon;

import java.io.*;

public class UserLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
	        try {
	        	UserDao userdao = new UserDaoImpl();
	            ResultSet rs = userdao.userQueryByUsernameAndPswd(userName, password);
	            
	            if(rs.next()) {
	            	response.sendRedirect(request.getContextPath()+"/bookshelf");
	            }
	            else {
	            	out.print("<p><a>Invalid user name or password!</a></p>");
	            	RequestDispatcher rd = request.getRequestDispatcher("/login.html");
	            	rd.include(request, response);
	            }
	        }
	        catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

}

