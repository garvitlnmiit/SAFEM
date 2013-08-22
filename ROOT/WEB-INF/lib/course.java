// You can use this sample servlet as a starting point to connect to
// your database included with useractive account.
//
// Portions copied from Sun's HelloWorldServlet.java
// You are free to use this code in any way you chose

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

class courseDetails
{
	String evalNameString,evalTypeString,mMarksString,publicViewString;
	String[] evalNameArray,evalTypeArray,mMarksArray,publicViewArray;
	int courseCredit,evalStatus;
}

public class course extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse res) 
	throws ServletException, IOException 
	{
		doGet(req,res);
	}
	
	public void doGet (HttpServletRequest req, HttpServletResponse res)
	    throws ServletException, IOException {
	    res.setContentType("text/html");

	    int i;
		String courseid,var,delims;
		
		courseDetails courseObj=new courseDetails();
		
		delims="[-]";
		var=req.getParameter("courses");
		courseid=var.trim();
		
	    ServletOutputStream out = res.getOutputStream();

	    try {
		Class.forName("org.gjt.mm.mysql.Driver").newInstance();
	    }
	    catch (Exception E) {
		out.println("Unable to load driver.");
		E.printStackTrace();
	    }

	    try {
		Connection Conn = 
		    DriverManager.getConnection("jdbc:mysql://localhost/eval_sys?user=root&password=root");
	    
		String query="select * from student_details where course_id='"+courseid+"'";
		Statement Stmt = Conn.createStatement();
		ResultSet RS = Stmt.executeQuery(query);

		String query1="select * from course_details where course_id='"+courseid+"'";
		Statement Stmt1 = Conn.createStatement();
		ResultSet RS1 = Stmt1.executeQuery(query1);		
		
		// ------------------------------------------------------EEEVVVAAALLL GGGUUUIII-----------------------------------------------------//
		out.println("<html>");
	    out.println("<head><title>");
		out.println("profile");
		out.println("</title></head>");
	    out.println("<body>");	    
		//out.println("<form action='/course_s' method='post'>");
		out.println("<h1>Welcome ");
		//out.println(user.name);
		out.println("</h1>");
		out.println("Students :</br>");
		//out.println(query);
		out.println("<table>");
		out.println("<tr>");
		if(RS1.next())
		{
			courseObj.evalNameString=RS1.getString(6);
			courseObj.evalNameArray=courseObj.evalNameString.split(delims);			
		}

		out.println("<td>");
		out.println("Student");
		out.println("</td>");
		for(i=0;i<(courseObj.evalNameArray).length;i++)
		{
		out.println("<td>");
		out.println(courseObj.evalNameArray[i]);
		out.println("</td>");			
		}
		
		out.println("</tr>");

		while(RS.next())
		{
		out.println("<tr>");
		for(i=0;i<)
		out.println(RS.getString(1));
		out.println("</tr>");
		}

		//out.println("<input type='submit'/></form>");	
		out.println("</table>");
		out.println("</body></html>");		

		// Clean up after ourselves
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
	
	public String getServletInfo() {
	  return "Create a page that says <i>Hello World</i> and send it back";
	}
}
