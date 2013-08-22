// This file is a part of ***********STUDENT AND FACULTY EVALUATION SYSTEM************

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class publicView extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse res) 
	throws ServletException, IOException 
	{
		doGet(req,res);
	}
	
	public void doGet (HttpServletRequest req, HttpServletResponse res)
	    throws ServletException, IOException {
	    res.setContentType("text/html");
		ServletOutputStream out = res.getOutputStream();
		int i;
		String pvstring=null,temp="";
		String[] pvarray;	
		String id=req.getParameter("a");
	    String check=req.getParameter("b");
		HttpSession session=req.getSession(false);					// Getting the session value
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
		while(RS.next())
		pvstring=RS.getString(10);
		
		pvarray=pvstring.split("[-]");
		if(check.equals("true"))
		{
		pvarray[Integer.parseInt(id)]="y";
		temp="set";
		}
		else
		{
		pvarray[Integer.parseInt(id)]="n";
		temp="unset";
		}
		
		pvstring="";
		
		for(i=0;i<pvarray.length;i++)
		pvstring=pvstring+pvarray[i]+"-";
		
		String query1="update course_details set public_view='"+pvstring+"' where course_id='"+courseid+"'";
		Statement Stmt1 = Conn.createStatement();
		Stmt1.executeUpdate(query1);		
		
		out.println(temp);
		
		RS.close();
		Stmt.close();
		Conn.close();		
	    }
	    catch (SQLException E) {
		out.println("SQLException: " + E.getMessage());
		out.println("SQLState:     " + E.getSQLState());
		out.println("VendorError:  " + E.getErrorCode());
	    }
	}
}