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

public class pwdfun extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse res) 
	throws ServletException, IOException 
	{
		doGet(req,res);
	}
	
	public void doGet (HttpServletRequest req, HttpServletResponse res)
	    throws ServletException, IOException {
	    res.setContentType("text/html");
	    int count=0;
		String upwd,url,uname,ucat;
		
	    ServletOutputStream out = res.getOutputStream();

		classPwd pwd=new classPwd(); 
		
		pwd.oldPwd=req.getParameter("a");
		pwd.newPwd=req.getParameter("b");
		
		HttpSession session=req.getSession(false);			// fetching password
		upwd=(String)session.getAttribute("upwd");				
		HttpSession session1=req.getSession(false);			// fetching username
		uname=(String)session1.getAttribute("uName");				
		HttpSession session2=req.getSession(false);			// fetching category
		ucat=(String)session2.getAttribute("ucat");				
		
		if(!upwd.equals(pwd.oldPwd))
		{
			//HttpSession session3=req.getSession();	
			//session3.setAttribute("err","Password did not match!!");	
			url="/hello?usr="+uname+"&pwd="+upwd+"&userCategory="+ucat;
			res.sendRedirect(url);
		}
		
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
	    
		String query="update users set password='"+pwd.newPwd+"' where userid='"+uname+"' and category='"+ucat+"'";
		Statement Stmt = Conn.createStatement();
		Stmt.executeUpdate(query);

			//HttpSession session3=req.getSession();	
			//session3.setAttribute("err","Password Updated!!");		
			url="/hello?usr="+uname+"&pwd="+upwd+"&userCategory="+ucat;
			res.sendRedirect(url);
		}
	    catch (SQLException E)
		{
		out.println("SQLException: " + E.getMessage());
		out.println("SQLState:     " + E.getSQLState());
		out.println("VendorError:  " + E.getErrorCode());
	    }

}
}