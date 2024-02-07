package com.JSPServletsJDBC;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class JSPServletJbdc
 */
@WebServlet("/JSPServletJbdc")
public class JSPServletJbdc extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			PrintWriter out=response.getWriter();
			response.setContentType("text/html");
			  Class.forName("com.mysql.cj.jdbc.Driver");
			  Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbname","username", "password");
			  String n=request.getParameter("txtName");
			  String p=request.getParameter("txtPwd");
			  
			  PreparedStatement preparedStatement = connection.prepareStatement("select username  from login where username =? and password=?");
			  preparedStatement.setString(1, n);
			  
			  preparedStatement.setString(2, p);
			  ResultSet rs= preparedStatement.executeQuery();
			  if(rs.next()) {
				  RequestDispatcher rd=request.getRequestDispatcher("welcome.jsp");
				  rd.forward(request, response);
			  }else {
				  out.println("<font color=blue size=18>Login Failed !!<br>");
				  out.println("<a href=login.jsp>Try again !!</a>");
			  }
			  
			  
		}catch (Exception e) {
            e.printStackTrace();
        }
	}

}
