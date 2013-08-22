import java.io.*;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class avail2 extends HttpServlet
{
protected void doPost(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException
{
response.setContentType("text/html;charset=UTF-8");
PrintWriter out = response.getWriter();
try
{
// 9lessons is my database name
String connectionURL = "jdbc:mysql://localhost:3306/swe";
Connection connection = null;
Class.forName("com.mysql.jdbc.Driver").newInstance();
connection = DriverManager.getConnection(connectionURL, "root", "root");
String uname = request.getParameter("fname");
PreparedStatement ps = connection.prepareStatement("select course_name from course_details where course_name=?");
ps.setString(1,uname);
ResultSet rs = ps.executeQuery();

if (!rs.next())
{
out.println("<b>  ["+uname+"</b> is avaliable  ]");
}
else
{
out.println("<font color=red><b>  ["+uname+"</b> is already in use  ]</font>");
}
out.println();
}
catch (Exception ex)
{
out.println("Error ->" + ex.getMessage());
}
finally
{
out.close();
}
}
protected void doGet(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException
{
doPost(request, response);
}
}