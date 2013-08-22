import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.util.*;

public class feedback extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
	{
		doGet(req,res);
	}
	
	public void doGet (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	    res.setContentType("text/html");
	    int count=0,i=0;
	    ServletOutputStream out = res.getOutputStream();
		
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
		String cname,fname,o,t,c,r,i1,desc,appeal;
		cname=req.getParameter("cname");
		fname=req.getParameter("fname");
		o=req.getParameter("o");
		t=req.getParameter("t");
		c=req.getParameter("c");
		r=req.getParameter("r");
		i1=req.getParameter("i");
		desc=req.getParameter("desc");
		appeal=req.getParameter("appeal");
		
		int p =Integer.parseInt(req.getParameter("p"));
		
		String query="INSERT INTO feedback_details VALUES ('"+cname+"','"+fname+"','"+o+"','"+t+"','"+c+"','"+r+"','"+i1+"','"+desc+"','"+appeal+"',"+p+")";	
		Stmt.executeUpdate(query);
		Stmt.close();
		Conn.close();
		res.sendRedirect("index.html");
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
