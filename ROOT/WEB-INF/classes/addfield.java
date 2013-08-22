// This file is a part of ***********STUDENT AND FACULTY EVALUATION SYSTEM************

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class addfield extends HttpServlet  					// add field class
 {

	public void doGet (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{

	    res.setContentType("text/html");

	    ServletOutputStream out = res.getOutputStream();			// Declaring output stream
		
	    int i;
		String error,fieldType,fieldName,maxMarks,evalType="",evalName="",mMarks="",publicView="",stm="",totalFlag="";
		String[] evalTypeArray,evalNameArray,stmArray;

		fieldType=req.getParameter("a");
		fieldName=req.getParameter("b");
		maxMarks=req.getParameter("c");		
		
		HttpSession session=req.getSession(false);					// Getting the session value
		String courseid=(String)session.getAttribute("courseId");		
		
	    try {
		Class.forName("org.gjt.mm.mysql.Driver").newInstance();
	    }
	    catch (Exception E) {
		out.println("Unable to load driver.");
		E.printStackTrace();
	    }

	    try 
		{
		Connection Conn = DriverManager.getConnection("jdbc:mysql://localhost/swe?user=root&password=root");

		String query="select * from course_details where course_id='"+courseid+"'";	
		Statement Stmt = Conn.createStatement();
		ResultSet RS = Stmt.executeQuery(query);			// stores the fields matches courseId from course_details

		String query1="select * from student_details where course_id='"+courseid+"'";	
		Statement Stmt1 = Conn.createStatement();
		ResultSet RS1 = Stmt1.executeQuery(query1);			// stores the fields matches courseId from student_details
		
		while(RS.next())
		{
		evalType=RS.getString(6);		// eval_field_name
		evalName=RS.getString(8);		// eval_field_type
		mMarks=RS.getString(9);			// max_marks
		publicView=RS.getString(10);	// public_view	
		totalFlag=RS.getString(12);	// eval_total
		}
		
		if(totalFlag.equals("1"))	//testing if total is done
		{
			error="Can not add fields any more. Total has been evaluated!!";
		}
		else if(evalName.toLowerCase().contains(fieldName.toLowerCase()))
		{
			error="Field with the requested name already exists!!";
		}
		else
		{
		evalType=evalType+fieldType+"-";
		evalName=evalName+fieldName+"-";
		mMarks=mMarks+maxMarks+"-";	
		publicView=publicView+"n-";

		String query2="update course_details set eval_field_name='"+evalType+"', eval_field_type='"+evalName+"', max_marks='"+mMarks+"', public_view='"+publicView+"' where course_id='"+courseid+"'";	
		Statement Stmt2 = Conn.createStatement();
		Stmt2.executeUpdate(query2);				
		
		while(RS1.next())
		{
			stm=RS1.getString(3);
			stm=stm+"0-";
			
		String query3="update student_details set marks='"+stm+"' where course_id='"+courseid+"' and student_id='"+RS1.getString(1)+"'";	
		Statement Stmt3 = Conn.createStatement();
		Stmt3.executeUpdate(query3);							
	    }
		error="";
		}
		
		HttpSession session3=req.getSession();	
		session3.setAttribute("error",error);		
		
		String url="/course_s?courses="+courseid;
		res.sendRedirect(url);
		
	    }
	    catch (SQLException E) {
		out.println("SQLException: " + E.getMessage());
		out.println("SQLState:     " + E.getSQLState());
		out.println("VendorError:  " + E.getErrorCode());
	    }
	    
	}
	
	public String getServletInfo() {
	  return "Create a page that says <i>Hello World</i> and send it back";
	}
}
