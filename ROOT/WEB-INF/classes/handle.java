import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
class UserDetail
{
	String name;
	String password;
	String category;
}

public class handle extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse res) 
	throws ServletException, IOException 
	{
		doGet(req,res);
	}
	
	public void doGet (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	    res.setContentType("text/html");
	    int count=0;
	    PrintWriter out=res.getWriter();
		
		UserDetail user=new UserDetail(); 
		
		user.name=req.getParameter("usr");
		user.password=req.getParameter("pwd");
		user.category=req.getParameter("userCategory");
		
	    try {
		Class.forName("org.gjt.mm.mysql.Driver").newInstance();
	    }
	    catch (Exception E) {
		out.println("Unable to load driver.");
		E.printStackTrace();
	    }
	    try {
		Connection Conn = DriverManager.getConnection("jdbc:mysql://localhost/swe?user=root&password=root");
	    
		String query="select * from users where userid='"+user.name+"' and password='"+user.password+"' and category='"+user.category+"'";	
		Statement Stmt = Conn.createStatement();
		ResultSet RS = Stmt.executeQuery(query);

		if(RS.next())
		{
		//instructor------------------------
		if((user.category).equalsIgnoreCase("instructor"))
		{	
			String url="/instructor_login?usr="+user.name+"&pwd="+user.password+"&userCategory="+user.category;	
					res.sendRedirect(url);		
		}
		//STUDENTS------------------------
		
		else if((user.category).equalsIgnoreCase("student"))
		{	HttpSession session=req.getSession(true);
			session.setAttribute("logged", RS.getString(1));
			ServletContext context=getServletContext();	
			RequestDispatcher dispatcher=context.getRequestDispatcher("/student");
			dispatcher.forward(req, res);
				
		}//END of Student-----------------
		else if((user.category).equalsIgnoreCase("parent"))
		{		
		HttpSession session=req.getSession(true);
			session.setAttribute("logged", RS.getString(1));
			
			ServletContext context=getServletContext();	
			RequestDispatcher dispatcher=context.getRequestDispatcher("/parent");
			dispatcher.forward(req, res);
		}
		else if((user.category).equalsIgnoreCase("admin"))
		{
			HttpSession session=req.getSession(true);
			session.setAttribute("logged", RS.getString(1));
			
			ServletContext context=getServletContext();	
			RequestDispatcher dispatcher=context.getRequestDispatcher("/admin");
			dispatcher.forward(req, res);
		}
		out.println("</div></body></html>");		
		}
		else
		{
			res.sendRedirect("index.html");
		}

		// Clean up after ourselves
		
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
}
