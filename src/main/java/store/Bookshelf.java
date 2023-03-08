package store;

import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dbcon.DBcon;

import java.io.*;

public class Bookshelf extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		try {
			  
            Connection con = DBcon.initializeDatabase();
  
            PreparedStatement st = con
                   .prepareStatement("select * from books");
            
            ResultSet rs = st.executeQuery();
            
            RequestDispatcher rd = request.getRequestDispatcher("/bookshelf.html");
            rd.include(request, response);
            while(rs.next()) {
            	String title = rs.getString(2);
            	String author = rs.getString(3);
            	String img = rs.getString(4);
            	out.print(this.showBook(title, author, img));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	public String showBook(String title, String author, String img) {
		  return "<p>" + title + " " + author + " " + img + "</p>";
	}
}

