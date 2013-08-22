// This file is a part of ***********STUDENT AND FACULTY EVALUATION SYSTEM************

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

class classPwd
{
	String oldPwd;
	String newPwd;
}

public class changePwd extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse res) 
	throws ServletException, IOException 
	{
		doGet(req,res);
	}
	
	public void doGet (HttpServletRequest req, HttpServletResponse res)
	    throws ServletException, IOException {
	    res.setContentType("text/html");
	    int count=0;
		String upwd;
		
	    ServletOutputStream out = res.getOutputStream();

		classPwd pwd=new classPwd(); 
		
		pwd.oldPwd=req.getParameter("a");
		pwd.newPwd=req.getParameter("b");
		
		HttpSession session=req.getSession(false);			// fetching password
		upwd=(String)session.getAttribute("upwd");				

		out.println(upwd);
		
		if(upwd.equals(pwd.oldPwd))
		{
			res.sendRedirect("/hello");
		}
		
	    try {
		Class.forName("org.gjt.mm.mysql.Driver").newInstance();
	    }
	    catch (Exception E) {
		out.println("Unable to load driver.");
		E.printStackTrace();
	    }

	    /*try {
		Connection Conn = DriverManager.getConnection("jdbc:mysql://localhost/eval_sys?user=root&password=root");
	    
		String query="select * from student_details where course_id='"+courseid+"'";
		Statement Stmt = Conn.createStatement();
		ResultSet RS = Stmt.executeQuery(query);

		String query1="select * from course_details where course_id='"+courseid+"'";
		Statement Stmt1 = Conn.createStatement();
		ResultSet RS1 = Stmt1.executeQuery(query1);		
		
		}
	    catch (SQLException E) {
		out.println("SQLException: " + E.getMessage());
		out.println("SQLState:     " + E.getSQLState());
		out.println("VendorError:  " + E.getErrorCode());
	    }*/

}
}