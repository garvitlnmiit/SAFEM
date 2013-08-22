import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.util.*;

public class parent1 extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
	{
		doGet(req,res);
	}
	
	public void doGet (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	    res.setContentType("text/html");
	    int count=0,i=0;
	    PrintWriter out=res.getWriter();
		HttpSession session=req.getSession(false);
		
	    try {
		Class.forName("org.gjt.mm.mysql.Driver").newInstance();
	    }
	    catch (Exception E) {
		  
		out.println("Unable to load driver.");
		E.printStackTrace();
	    }
	    
		try {
		Connection Conn = DriverManager.getConnection("jdbc:mysql://localhost/swe?user=root&password=root");
	    Statement Stmt = Conn.createStatement();
		String query="select * from student_details where student_id='"+session.getAttribute("logged")+"'";	
		ResultSet RS = Stmt.executeQuery(query);
		out.println("<html>");
	    out.println("<head><title>");
		out.println("Student Evaluation");
		out.println("</title></head>");
	    out.println("<body>");	    
		out.println("<h1>Evaluation</h1><br/>");
		out.println("<form action='change_pass.jsp' method='post'>"); //CHANGE PASSWORD
		out.println("<input type='hidden' name='sid' value='"+session.getAttribute("logged")+"' />");
		out.println("<input type='submit' value='Change Password'/></form>");
		
		while(RS.next())
		{
			String s=RS.getString(2);
			String m=RS.getString(3);
			Integer total_v=RS.getInt(4);
			String grade_v=RS.getString(5);
			Integer j=Integer.parseInt(RS.getString(6));
			String query1="select * from course_details where course_id='"+s+"'";	
			Statement Stmt1 = Conn.createStatement();
			ResultSet RS1 = Stmt1.executeQuery(query1);
			if(RS1.next())
			{	
				out.println("Course Name: " +RS1.getString(2)+"<br/>");
				out.println("Instructors Name: " +RS1.getString(3)+"<br/>");
				String t=RS1.getString(3);
				String total_c=RS1.getString(12);
				Integer grade_c=RS1.getInt(13);
				String fname=RS1.getString(6);
				String[] data1 = fname.split("\\-");
				
				String ftype=RS1.getString(8);
				String[] data2 = ftype.split("\\-");
				
				String max_marks=RS1.getString(9);
				String[] data3 = max_marks.split("\\-");
				
				String pub_v=RS1.getString(10);
				String[] data4 = pub_v.split("\\-");
				String[] data5 = m.split("\\-");
		
				int values=data1.length;
				
				for(i=0;i<values;i++)
				{
					if(data4[i].equalsIgnoreCase("n"))
						{
						data3 = removeAt(i, data3);
						data2 = removeAt(i, data2);
						data1 = removeAt(i, data1);
						data5 = removeAt(i, data5);
						
						}
				}
				
				out.println("<table border='1'>");	    
				out.println("<tr>");
				values=data1.length;
				
				for(i=0;i<values;i++)
				{
					out.print("<th>");
					out.print(""+data1[i]+" "+data2[i]+" "+"("+data3[i]+")");
					out.print("</th>");
				}

				out.println("</tr>");
				out.println("<tr>");

				for(i=0;i<values;i++)
				{
					out.print("<td>");
					out.print(""+data5[i]);
					out.print("</td>");
				}

				out.println("</tr>");
				out.println("</table>");
				if (total_c.equalsIgnoreCase("1"))
				{out.println("Total: "+total_v+"<br/>");
				}
				if (grade_c==1)
				{out.println("Grade: "+grade_v+"<br/>");
				}
				
			}
			else
			{
						out.println("Course Details NOT available!!");
			}

		out.println("<br />");
		}
		out.println("</body></html>");		
		
		// Clean up after ourselves
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
static String[] removeAt(int k, String[] arr) {
    final int L = arr.length;
    String[] ret = new String[L - 1];
    System.arraycopy(arr, 0, ret, 0, k);
    System.arraycopy(arr, k + 1, ret, k, L - k - 1);
    return ret;
}
static void print(String[] arr) {
    System.out.println(Arrays.toString(arr));       
}  	

	}
