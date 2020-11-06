package com.example;

import java.io.*;
import java.sql.*;
import java.util.*;
import javax.servlet.*;
import java.sql.DriverManager;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class products extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			  throws IOException, ServletException{
		boolean exists = false;
		String barcode=request.getParameter("barcode");
		String name=request.getParameter("name");
		String color=request.getParameter("color");
		String description=request.getParameter("description");
		List result = new ArrayList();
		PrintWriter pwriter = response.getWriter();
		Connection myConn=null;
		
		String url = "jdbc:postgresql://0.0.0.0:5432/isi";
		String dbUser = "postgres";
		String dbPassword ="admin";
		
		try {
			Class.forName("org.postgresql.Driver");
			myConn = DriverManager.getConnection(url, dbUser, dbPassword);
			Statement myStmt = myConn.createStatement();
			
			ResultSet searchResult = myStmt.executeQuery("SELECT barcode FROM products ;");
			  
			myStmt.executeUpdate("INSERT INTO products" + " (barcode, name, color, description) " + " values('" + barcode+ "', '" + name + "','" + color + "','" + description + "');");
				
			result.add(barcode);
			result.add(name);
			result.add(color);
			result.add(description);
			request.setAttribute("details", result);
			RequestDispatcher view = request.getRequestDispatcher("result.jsp");
			view.forward(request, response);
			
		}	
		
		catch(Exception exc) {
			pwriter.println("<p>Error: Item already in Database </p>");
			System.err.println("An error occurred while connecting PostgreSQL database: isi");
			exc.printStackTrace();
			System.err.println("\nError again my friend...");
		}
		
		
		pwriter.close();
	}
}
		
