import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.util.*;
public class instructor extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html");
		PrintWriter out=res.getWriter();

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
	    Statement Stmt = Conn.createStatement();
		String query="INSERT INTO users VALUES ('"+req.getParameter("fid")+"','"+req.getParameter("fid")+"','instructor','"+req.getParameter("uname")+"','"+req.getParameter("femail")+"')";	
		Stmt.executeUpdate(query);
		Stmt.close();
		Conn.close();
		ServletContext context=getServletContext();	
		RequestDispatcher dispatcher=context.getRequestDispatcher("/admin");
		dispatcher.forward(req, res);
		}
		catch (SQLException E) {
		ServletContext context=getServletContext();	
		RequestDispatcher dispatcher=context.getRequestDispatcher("/admin");
		dispatcher.forward(req, res);
		}
		
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

}
