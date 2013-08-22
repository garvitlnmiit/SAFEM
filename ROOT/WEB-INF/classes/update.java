// This file is a part of ***********STUDENT AND FACULTY EVALUATION SYSTEM************

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.util.*;

public class update extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse res) 
	throws ServletException, IOException 
	{
		doGet(req,res);
	}
	
	public void doGet (HttpServletRequest req, HttpServletResponse res)
	    throws ServletException, IOException {
	    res.setContentType("text/html");
	    String id_var,id_val,delims="[-]",courseid,marks=null,maxMarks=null,examTypes=null,newMarks="",totalFlag="";
		int i=0;
	    ServletOutputStream out = res.getOutputStream();
		
		id_var=req.getParameter("a");
		id_val=req.getParameter("b");
		
		String[] idVarArray,marksArray,maxMarksArray,examTypesArray;	
		idVarArray=id_var.split(delims);
		
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

		String query1="select * from course_details where course_id='"+idVarArray[2]+"'";    //"+idVarArray[2]+"
		Statement Stmt1 = Conn.createStatement();
		ResultSet RS1 = Stmt1.executeQuery(query1);			
		
		while(RS1.next())
		{
		     maxMarks=RS1.getString(9);	
			 examTypes=RS1.getString(8);	
			totalFlag=RS1.getString(12);
		}
		
		if(totalFlag.equals("1"))	//testing if total is done
		{
		    out.println("Can't update marks anymore. Total has been evaluated!!");
		}
		else
		{
		maxMarksArray=maxMarks.split(delims);		
		examTypesArray=examTypes.split(delims);						
		
		String query="select * from student_details where student_id='"+idVarArray[1]+"' and course_id='"+idVarArray[2]+"'";	
		Statement Stmt = Conn.createStatement();
		ResultSet RS = Stmt.executeQuery(query);

		while(RS.next())
		{	
		marks=RS.getString(3);
		}
		//out.println(marks);
		//out.println("<br/>");
		
		marksArray=marks.split(delims);

		if(Float.parseFloat(id_val)<=Float.parseFloat(maxMarksArray[Integer.parseInt(idVarArray[0])]))
		{
		marksArray[Integer.parseInt(idVarArray[0])]=id_val;
		for(i=0;i<marksArray.length;i++)
		newMarks=newMarks+marksArray[i]+"-";
		//out.println(newMarks);
		
		String query2="update student_details set marks='"+newMarks+"' where student_id='"+idVarArray[1]+"' and course_id='"+idVarArray[2]+"'";	
		//out.println("<br/>");
		//out.println(query2);

		Statement Stmt2 = Conn.createStatement();
		Stmt2.executeUpdate(query2);		
		//out.println("<br/>");
		out.println("Updated : ");
		out.println(idVarArray[1]);
		out.println(" | ");
		out.println(examTypesArray[Integer.parseInt(idVarArray[0])]);
		//out.println("hereh");
		
	    }
		else
		{
		out.println("Entered marks exceeds maximum marks. So can not proceed");
		}
		}
		
		}
	    catch (Exception E) {
		out.println("--> "+E);
		E.printStackTrace();
		}
		
		//out.println(maxMarks);
		//out.println(" ");
		//out.println(id_val);
		}
	
	public String getServletInfo() {
	  return "Create a page that says <i>Hello World</i> and send it back";
	}
	
}
