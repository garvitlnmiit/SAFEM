import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class target extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse res) 
	throws ServletException, IOException 
	{
		doGet(req,res);
	}
	
	public void doGet (HttpServletRequest req, HttpServletResponse res)
	    throws ServletException, IOException {

	    res.setContentType("text/html");
	    int count=0;
	    ServletOutputStream out = res.getOutputStream();
		
		out.println("Executed in servlet!!");
		
		}
		
}