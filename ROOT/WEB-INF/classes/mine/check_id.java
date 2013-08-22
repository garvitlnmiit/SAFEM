import java.io.*;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class check_id extends HttpServlet
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
String uname = request.getParameter("check");
PreparedStatement ps = connection.prepareStatement("select userid from users where userid=?");
ps.setString(1,uname);
ResultSet rs = ps.executeQuery();

if (!rs.next())
{
out.println(uname+" is NOT in Database [Stop Re-enter]");
}
else
{
String a="";
PreparedStatement ps1 = connection.prepareStatement("select count(*) from student_details where student_id=? and grades=''");
ps1.setString(1,uname);
ResultSet rs1 = ps1.executeQuery();
if(rs1.next())
{if(Integer.parseInt(rs1.getString(1))==0)
{
out.println(uname+" is in Database with ALL grades [Proceed]");
}
else
{out.println(uname+" is in Database without ALL grades [Stop Re-enter]");

}
}
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