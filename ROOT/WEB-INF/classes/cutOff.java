// This file is a part of ***********STUDENT AND FACULTY EVALUATION SYSTEM************

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class cutOff extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse res) 
	throws ServletException, IOException 
	{
		doGet(req,res);
	}
	
	public void doGet (HttpServletRequest req, HttpServletResponse res)
	    throws ServletException, IOException {
	    res.setContentType("text/html");
		ServletOutputStream out = res.getOutputStream();
		float total;		
		String cos=req.getParameter("a");		
	    String[] coa;
		coa=cos.split("[-]");
		
		HttpSession session=req.getSession(false);
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

		String query="update course_details set eval_grade='1' where course_id='"+courseid+"'";	
		Statement Stmt = Conn.createStatement();
		Stmt.executeUpdate(query);			// stores the fields matches courseId from course_details

		String query1="select * from student_details where course_id='"+courseid+"'";	
		Statement Stmt1 = Conn.createStatement();
		ResultSet RS1 = Stmt1.executeQuery(query1);			// stores the fields matches courseId from student_details
		
		while(RS1.next())
		{
			String grade="",roll="";
			roll=RS1.getString(1);
			total=RS1.getFloat(4);
			if(total<=Float.parseFloat(coa[0]))
			grade="F";
			else if(total<=Float.parseFloat(coa[1]) && total>Float.parseFloat(coa[0]))
			grade="D";
			else if(total<=Float.parseFloat(coa[2]) && total>Float.parseFloat(coa[1]))
			grade="CD";
			else if(total<=Float.parseFloat(coa[3]) && total>Float.parseFloat(coa[2]))
			grade="C";
			else if(total<=Float.parseFloat(coa[4]) && total>Float.parseFloat(coa[3]))
			grade="BC";
			else if(total<=Float.parseFloat(coa[5]) && total>Float.parseFloat(coa[4]))
			grade="B";
			else if(total<=Float.parseFloat(coa[6]) && total>Float.parseFloat(coa[5]))
			grade="AB";
			else if(total<=Float.parseFloat(coa[7]) && total>Float.parseFloat(coa[6]))
			grade="A";
			else
			{;}	
			
			String query2="update student_details set grades='"+grade+"' where course_id='"+courseid+"' and student_id='"+roll+"'";	
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

}