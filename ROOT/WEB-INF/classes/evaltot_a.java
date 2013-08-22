// This file is a part of ***********STUDENT AND FACULTY EVALUATION SYSTEM************

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.util.*;


public class evaltot_a extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse res) 
	throws ServletException, IOException 
	{
		doGet(req,res);
	}
	
	public void doGet (HttpServletRequest req, HttpServletResponse res)
	    throws ServletException, IOException {
	    res.setContentType("text/html");

	    ServletOutputStream out = res.getOutputStream();
		
		String name=req.getParameter("a"),nameval=req.getParameter("b"),marks,rollid,evalFieldType="",evalFieldName="",maxMarks="",pbv="";	
		String[] nameArray=name.split("[-]"),marksArray,eftArray,pbva,maxMarksArray;
		String[] namevalArray=nameval.split("[-]");
		int i,j,totalFlag=0,maxTot=0;
		double varx,totx;
		double roundOff;
		HttpSession session=req.getSession(false);
		String courseid=(String)session.getAttribute("courseId");		
	
	    try {
		Class.forName("org.gjt.mm.mysql.Driver").newInstance();
	    }
	    catch (Exception E) {
		out.println("Unable to load driver.");
		E.printStackTrace();
	    }

	    try {
		Connection Conn = 
		    DriverManager.getConnection("jdbc:mysql://localhost/swe?user=root&password=root");
	    
		String query="select * from course_details where course_id='"+courseid+"'";
		Statement Stmt = Conn.createStatement();
		ResultSet RS = Stmt.executeQuery(query);

		String query1="select * from student_details where course_id='"+courseid+"'";
		Statement Stmt1 = Conn.createStatement();
		ResultSet RS1 = Stmt1.executeQuery(query1);
		
		
		while(RS.next())
		{
			evalFieldType=RS.getString(6);
			evalFieldName=RS.getString(8);
			maxMarks=RS.getString(9);
			pbv=RS.getString(10);
			
		}
		maxMarksArray=maxMarks.split("[-]");
		eftArray=evalFieldType.split("[-]");
		pbv=pbv+"n-";			// correct it today 4-22-2013.
		// making total flag 1
		String query3="update course_details set eval_total='1', public_view='"+pbv+"' where course_id='"+courseid+"'"; // have to make the datatype of total in student_details to Float
		Statement Stmt3 = Conn.createStatement();
		Stmt3.executeUpdate(query3);				
		
		while(RS1.next())
		{
			marks=RS1.getString(3);
			marksArray=marks.split("-");
			totx=0;
			varx=0;
			
			
			for(i=0;i<nameArray.length;i++)
			{
				varx=0;
				maxTot=0;
				for(j=0;j<eftArray.length;j++)
				{
					if(nameArray[i].equals(eftArray[j]))
					{
						varx=varx+Float.parseFloat(marksArray[j]);
						//max total..
						maxTot=maxTot+Integer.parseInt(maxMarksArray[j]);
					}
				}
				totx=totx+varx*(Float.parseFloat(namevalArray[i])/maxTot);		// ******final evaluated value******
			}
			
			roundOff=Math.round(totx * 100.0) / 100.0; 		// to make decimal upto two floating point
			rollid=RS1.getString(1);

			String query2="update student_details set total='"+roundOff+"' where course_id='"+courseid+"' and student_id='"+rollid+"'"; // have to make the datatype of total in student_details to Float
			Statement Stmt2 = Conn.createStatement();
			Stmt2.executeUpdate(query2);	
		}
		
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
