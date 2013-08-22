import java.io.*;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class check_old extends HttpServlet
{
protected void doPost(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException
{
response.setContentType("text/html;charset=UTF-8");
PrintWriter out = response.getWriter();
try
{
	String connectionURL = "jdbc:mysql://localhost:3306/swe";
	Connection connection = null;
	Class.forName("com.mysql.jdbc.Driver").newInstance();
	connection = DriverManager.getConnection(connectionURL, "root", "root");
	String uname = request.getParameter("old");
	String sid = request.getParameter("sid");
	PreparedStatement ps = connection.prepareStatement("select * from users where userid=?");
	ps.setString(1,sid);
	ResultSet rs = ps.executeQuery();

	if (rs.next())
	{	
		if (rs.getString(2).equalsIgnoreCase(uname))
				{
					out.println("Old Password entered is CORRECT");

				}
				else
				{
					out.println("Old Password entered is INCORRECT");

				}
	}
	else
	{
	}
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