// This file is a part of ***********STUDENT AND FACULTY EVALUATION SYSTEM************

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

class UserDetail
{
	String name;
	String password;
	String category;
}

public class handle extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse res) 
	throws ServletException, IOException 
	{
		doGet(req,res);
	}
	
	public void doGet (HttpServletRequest req, HttpServletResponse res)
	    throws ServletException, IOException {
	    res.setContentType("text/html");
	    int count=0;
		String err=null;
	    ServletOutputStream out = res.getOutputStream();

		UserDetail user=new UserDetail(); 
		
		user.name=req.getParameter("usr");
		user.password=req.getParameter("pwd");
		user.category=req.getParameter("userCategory");
		HttpSession session=req.getSession();
		session.setAttribute("uName",user.name);
		HttpSession session1=req.getSession();
		session1.setAttribute("upwd",user.password);		
		HttpSession session2=req.getSession();
		session2.setAttribute("ucat",user.category);		
		//out.println(user.name);
		//out.println(user.password);
		//out.println("<html>");
		HttpSession session3=req.getSession(false);			// fetching error
		err=(String)session3.getAttribute("err");
		if(err==null)
		{
			err="";
		}
		
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
	    
		String query="select * from users where userid='"+user.name+"' and password='"+user.password+"' and category='"+user.category+"'";	
		Statement Stmt = Conn.createStatement();
		ResultSet RS = Stmt.executeQuery(query);

		out.println("<html>");
	    out.println("<head><title>Welcome</title>");
		out.println("<script>");
		out.println("function changePwd(){");
		out.println("window.location='changePwd.html';}");
		out.println("function logout(){");
		out.println("window.location='log_out';}");		
		out.println("</script>");	
		out.println("<link rel='stylesheet' type='text/css' href='css/handle.css'>");
		out.println("</head>");
	    out.println("<body>");
		out.println("<div id='topRightPanel'>");
		out.println("<button onclick='changePwd()'>Change Password</button>");
		out.println("<button onclick='logout()'>Log Out</button></div>");
		out.println("<div id='topLeftPanel'>");
		out.println("<h1>Welcome</h1>");
		out.println("</div>");
		
		
		
		if(RS.next())
		{
		if((user.category).equals("instructor"))
		{
			String query1="select * from course_details where instructor_id='"+user.name+"'";	
			Statement Stmt1 = Conn.createStatement();
			ResultSet RS1 = Stmt1.executeQuery(query1);			
			out.println("<form action='/course_s' method='post'>");
			out.println("<table id='box'>");
			out.println("<tr><th>Courses:</th><th>Evaluation</th><th>Feedback</th></tr>");
				while(RS1.next())
				{	
					count++;
					out.println("<tr><td>"+RS1.getString(2)+"</td>");
					out.println("<td><input type='radio' name='courses' value='"+RS1.getString(1)+"'></td>");
					out.println("<td><input type='radio' name='courses' value='feedack_"+RS1.getString(1)+"'></td><tr>");
				}
				if(count==0)
				{
					out.println("<br/>");
					out.println("No Courses Taken By You.");
					out.println("<br/>");
				}
					out.println("<tr><td colspan='3'><input type='submit'/><td></table></form>");	
		}
		else if((user.category).equals("admin"))
		{
			;// For admin-------------
		}
		else if((user.category).equals("student"))
		{
			; // For student--------------
		}
		else
		{
			;
		}
		
		out.println(err);
		out.println("</body></html>");		
		}
		else
		{
			//HttpResponse.sendRedirect("Location: index.html");
			res.sendRedirect("index.html");
		}
		
		session3.removeAttribute("err");	
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
