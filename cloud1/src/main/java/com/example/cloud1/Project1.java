
package com.example.cloud1;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.google.appengine.api.datastore.Query.FilterOperator;


import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;




//import Project_1.Student;

import java.io.*;
//import java.sql.*;
/**
 * Servlet implementation class TestServlet
 */
//@WebServlet("/MyServlet")
public class Project1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     *//*
    public MyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }*/

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	    response.setContentType("text/html;charset=UTF-8");
	    PrintWriter out = response.getWriter(); 

	    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
            String Name = request.getParameter("first_name");
	    String RollNo = request.getParameter("rollno");
	    String DOB = request.getParameter("dob");
	
            Entity details = new Entity("StudentData");
			
	    details.setProperty("Name",Name);
	    details.setProperty("RollNo",RollNo);
	    details.setProperty("DOB",DOB);
		
            datastore.put(details);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	    doGet(request,response);
	    response.setContentType("text/html;charset=UTF-8");
	    PrintWriter out = response.getWriter(); 
	    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		
            Query q = new Query("StudentData");
	    PreparedQuery pq = datastore.prepare(q);
		
	   out.println("<!DOCTYPE html>");
	   out.println("<html>");
	   out.println("<head><b><center>Student Details</center></b></head>");
	   out.println("<body>");
	   out.println("<div style='margin-left:200px'>");
	   out.println("<p>");
	   out.println("<table style='background-color:beige'>");
	   out.println("<th>Name</th>");out.println("<th>RollNo</th>");out.println("<th>DOB</th>");
	
	//try {

	     for (Entity result : pq.asIterable()) {
		 
    		 String Name = (String) result.getProperty("Name");
		 String RollNo = (String) result.getProperty("RollNo");
		 String DOB = (String) result.getProperty("DOB");

	   	 out.println("<tr>");
	         out.print("<td>"+Name+"</td>");
	         out.print("<td>"+RollNo+"</td>");
	         out.print("<td>"+DOB+"</td>");
	         
	         out.println("</tr>");	
	     }   
	//}catch (SQLException e) {
	    // throw new ServletException("No Records found.", e);

	
	//}
	out.println("</table>");
	out.println("</p>");
	
	out.println("</div>");
	out.println("<div style='margin-left:160px'><a href='index.jsp'>Add More</a></div>");
	out.println("</body>");
	out.println("</html>");
	}
	
}
