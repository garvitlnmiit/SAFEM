import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class change_pass extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html");
		PrintWriter out=res.getWriter();
		String category=req.getParameter("sid");
		String newp=req.getParameter("newp");
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
			Statement Stmt1 = Conn.createStatement();
			//out.println(category);
			String query1="UPDATE users SET password='"+newp+"' where userid='"+category+"'";
			Stmt1.executeUpdate(query1);
			Stmt1.close();
			ServletContext context=getServletContext();	
			RequestDispatcher dispatcher=context.getRequestDispatcher("/"+category);
			dispatcher.forward(req, res);
		}
		catch (Exception E) {
		ServletContext context=getServletContext();	
		RequestDispatcher dispatcher=context.getRequestDispatcher("/admin");
		dispatcher.forward(req, res);
			}
		
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		doGet(req, res);
	}

}
