import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class add_course extends HttpServlet {
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
		Statement st= Conn.createStatement();
		PreparedStatement ps=null;
		String iid="";
		ResultSet resultSet = null;
		String query1="select userid from users where user_name='"+req.getParameter("iname")+"'";
		ps=Conn.prepareStatement(query1);
		resultSet = ps.executeQuery();
		if(resultSet.next()){iid=resultSet.getString(1);}
		String query="INSERT INTO course_details VALUES ('"+req.getParameter("fid")+"','"+req.getParameter("fname")+"','"+req.getParameter("iname")+"','"+iid+"','"+req.getParameter("credit")+"','','','','','',0,'0',0,0 )";	
		Stmt.executeUpdate(query);
		Stmt.close();
		Conn.close();
		ServletContext context=getServletContext();	
		RequestDispatcher dispatcher=context.getRequestDispatcher("/admin");
		dispatcher.forward(req, res);
		}
		catch (SQLException E) {
		out.println("SQLException: " + E.getMessage());
		out.println("SQLState:     " + E.getSQLState());
		out.println("VendorError:  " + E.getErrorCode());
	    }
		
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

}
