package user;

import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import emailverify.EmailVerify;
import emailverify.EmailVerifyImpl;

import java.io.*;

public class UserRegistration extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        try {
	        	
	        	response.setContentType("text/html");
	            PrintWriter out = response.getWriter();
	  
	            UserDao userdao = new UserDaoImpl();
	            
	            ResultSet rs = userdao.userQueryByUsername(request.getParameter("username"));
	            
		        if(rs.next()) {
		        	out.println("<p><a>You are already registered! Login please!</a></p>");
		        	RequestDispatcher rd = request.getRequestDispatcher("/login.html");
	            	rd.include(request, response);
		        }
		        else {
	  
		            String firstName = request.getParameter("firstName");
		            String lastName = request.getParameter("lastName");
		            String email = request.getParameter("username");
		            String password = request.getParameter("password");
		            String address = request.getParameter("address");
		            String contact = request.getParameter("contact");

		            //use the interface EmailVerify
		            EmailVerify emlvrf = new EmailVerifyImpl();
		            
		            //get the 6-digit code
		            String code = emlvrf.getRandom();

		       		//create new user using all information
		            User user = new User(firstName,lastName,email,password,address,contact,code);
		     		            
		            //call the send email method
		            boolean test = emlvrf.sendEmail(user);
		            
		       		//check if the email send successfully
		            if(test){
			            // to display the successful result
		                HttpSession session  = request.getSession();
		                session.setAttribute("authcode", user);
		                response.sendRedirect("verify.jsp");
		            }else{
		       		  out.println("<p><a>Failed to send verification email, please try again.</a></p>");
		       		  RequestDispatcher rd = request.getRequestDispatcher("registration.html");
		       		  rd.include(request, response);
		       	   }
		        }
	        }
		        catch (Exception e) {
		            e.printStackTrace();
		        }
		    }

}

