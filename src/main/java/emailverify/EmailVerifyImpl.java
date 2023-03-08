package emailverify;

import user.User;
import user.UserDao;
import user.UserDaoImpl;

import java.util.Properties;
import java.util.Random;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dbcon.DBcon;


public class EmailVerifyImpl implements EmailVerify{
	@Override
	public boolean sendEmail(User user) {
		boolean test = false;

        String toEmail = user.getEmail();
        final String fromEmail = "yukun_zhan@outlook.com";
        final String password = "@Clky9912";

        try {

            // host email smtp server details
            Properties pr = new Properties();
            pr.put("mail.smtp.host", "smtp.office365.com");
            pr.put("mail.smtp.port", "587");
            pr.put("mail.smtp.auth", "true");
            pr.put("mail.smtp.starttls.enable", "true");
 
            //get session to authenticate the host email address and password
            Session session = Session.getInstance(pr, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(fromEmail, password);
                }
            });
            //set email message details
            Message mess = new MimeMessage(session);
    		//set from email address
            mess.setFrom(new InternetAddress(fromEmail));
    		//set to email address or destination email address
            mess.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
    		//set email subject
            mess.setSubject("User Email Verification");
    		//set message text
            mess.setText("Registered successfully.Please verify your account using this code: " + user.getCode());
            //send the message
            Transport.send(mess);
            test=true;
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return test;
	}
	@Override
	public String getRandom() {
		Random rnd = new Random();
        int number = rnd.nextInt(999999);
        return String.format("%06d", number);
	}
	
	//code verification class
	public class VerifyCode extends HttpServlet {

	    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        response.setContentType("text/html;charset=UTF-8");
	        try (PrintWriter out = response.getWriter()) {
	        	
	            HttpSession session = request.getSession();
	            User user= (User) session.getAttribute("authcode");	            
	            String code = request.getParameter("authcode");
            
	            if(code.equals(user.getCode())){
	            	//use dao interface to insert user info to db
		        	UserDao userdao = new UserDaoImpl();
		        	userdao.userInfoInsert(user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword(), user.getAddress(), user.getContact());
		            
	                out.println("<p><a>Verification Done</p></a>");
	                RequestDispatcher rd = request.getRequestDispatcher("login.html");
	                rd.include(request, response);
	            }else{
	                out.println("<p><a>Incorrect verification code</p></a>");
	            	RequestDispatcher rd = request.getRequestDispatcher("verify.jsp");
	            	rd.include(request, response);
	            }
	            
	        }
	        catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    
	    @Override
	    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        processRequest(request, response);
	    }
	}
}