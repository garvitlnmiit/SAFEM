import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class admin extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession(false);
		int count=0;
		out.println("<html>");
	    out.println("<head><title>");
		out.println("Admin HOME");
		out.println("</title><script type='text/javascript' src='jquery.js'></script><script type='text/javascript' src='check.js'></script></head>");
		

		out.println("<body bgcolor=#F3EEF0><h1>");
		out.println("<center>");
		out.println("Welcome");
		out.println(session.getAttribute("logged")+"!!");
		out.println("</center>");
		out.println("</h1>");
		out.println("<form action='add_instructor.jsp' method='post'>"); //ADD INSTRUCTOR
		out.println("<input type='submit' value='Add Instructor'/></form>");
		out.println("<form action='add_course.jsp' method='post'>");  //ADD COURSE
		out.println("<input type='submit' value='Add Course'/></form>");
		//--------------------------
		try 
		{
		Class.forName("org.gjt.mm.mysql.Driver").newInstance();
	    }
	    catch (Exception E) 
		{
		out.println("Unable to load driver.");
		E.printStackTrace();
	    }
		try
		{
		Connection Conn = DriverManager.getConnection("jdbc:mysql://localhost/swe?user=root&password=root");
	    String query="select * from course_details";	
		Statement Stmt = Conn.createStatement();
		ResultSet RS = Stmt.executeQuery(query);
		out.println("<b>Added Courses</b><br />");
		out.println("<form action='add_student.jsp' method='post'>"); //ADD STUDENT
		out.println("<table border='0' cellpadding='10'>");	    
		out.println("<tr><th>Course ID</th>");
		out.println("<th>Course Name [Click for FEEDBACK]</th>");
		out.println("<th>Course Instructor</th>");
		out.println("<th>Course Credit</th>");
		out.println("<th>Add Students</th>");
		out.println("</tr>");
		//String t;
		while(RS.next())
			{		
					out.println("<tr><th>");
					String t=RS.getString(1);
					out.println(t);
					out.print("</th><th>");
					out.println("<a href='check_feedback.jsp?cid="+RS.getString(1)+"' target='_blank' >");
					out.println(RS.getString(2));
					out.println("</a>");
					out.print("</th><th>");
					out.println(RS.getString(3));
					out.print("</th><th>");
					out.println(RS.getString(5));
					out.print("</th><th>");
					if(Integer.parseInt(RS.getString(14))==0)
					{
						out.println("<input type='radio' name='courseid' value='"+RS.getString(1)+"' required='required'/>");
					count=count+1;
					}
					else{}
						out.print("</th></tr>");
					
			}
		out.print("<tr><th></th><th></th><th></th><th></th><th>");
		if(count>0){
		out.println("<input type='submit' name='courseid' value='Add Student'/>");
		}
		out.print("</th></tr>");
		out.println("</table>");
		out.println("</form>");
		Stmt.close();
		Conn.close();

		}
		catch (SQLException E) {
		out.println("SQLException: " + E.getMessage());
		out.println("SQLState:     " + E.getSQLState());
		out.println("VendorError:  " + E.getErrorCode());
	    }
		out.println("<br />");
		out.println("<form action='change_pass.jsp' method='post'>"); //CHANGE PASSWORD
		out.println("<input type='hidden' name='sid' value='admin' />");
		out.println("<input type='submit' value='Change Password'/></form>");
		
		out.println("<form action='report.jsp' method='post'>"); //CHANGE PASSWORD
		out.println("Enter the Roll No (Student ID) <input type='text' class='check' id='check' name='sid' required='required'/> <span class='status'></span><br/>");
		out.println("<input type='submit' value='Print Report Card'/></form>");
		
		out.println("</body>");
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
