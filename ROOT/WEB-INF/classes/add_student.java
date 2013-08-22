import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import org.apache.poi.poifs.filesystem.*;
import java.text.SimpleDateFormat;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.*;
import org.apache.poi.ss.usermodel.*;
public class add_student extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		res.setContentType("text/html");
		PrintWriter out=res.getWriter();
		String cid=req.getParameter("cid");
		String type=req.getParameter("db1");
				
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
	
		if ((type).equalsIgnoreCase("db"))
		{	
			String query="show tables like '"+cid+"'";	
			ResultSet RS = Stmt.executeQuery(query);
			if(RS.next())
			{
			query="select sid from "+cid;	
			RS = Stmt.executeQuery(query);
				while(RS.next())
					{
						Statement Stmt1 = Conn.createStatement();
						String query1="INSERT INTO student_details VALUES ('"+RS.getString(1)+"','"+cid+"','',0,'',0)";	
						Stmt1.executeUpdate(query1);
						Stmt1.close();
					}
				Statement Stmt1 = Conn.createStatement();
				String query1="UPDATE course_details SET student_added=1 where course_id='"+cid+"'";
				Stmt1.executeUpdate(query1);
				Stmt1.close();
				ServletContext context=getServletContext();	
				RequestDispatcher dispatcher=context.getRequestDispatcher("/admin");
				dispatcher.forward(req, res);
			}
			else
			{
				ServletContext context=getServletContext();	
				RequestDispatcher dispatcher=context.getRequestDispatcher("/admin");
				dispatcher.forward(req, res);
			
			}
			
		}
		else
		{

			String file1=req.getParameter("filename");
			String file2="C:\\excel\\"+file1;
			FileInputStream file = new FileInputStream(new File(file2));
		    HSSFWorkbook workbook = new HSSFWorkbook(file);
		    HSSFSheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rowIterator = sheet.iterator();
			while(rowIterator.hasNext()) 
			{
		        Row row = rowIterator.next();
		        Iterator<Cell> cellIterator = row.cellIterator();
				while(cellIterator.hasNext()) 
				{
		            Cell cell = cellIterator.next();
					String s_id=cell.getStringCellValue();
					String query="INSERT INTO student_details VALUES ('"+s_id+"','"+cid+"','',0,'',0)";	
					Stmt.executeUpdate(query);
				}
			    
				 
			}
				Statement Stmt1 = Conn.createStatement();
				String query1="UPDATE course_details SET student_added=1 where course_id='"+cid+"'";
				Stmt1.executeUpdate(query1);
				Stmt1.close();
							
				ServletContext context=getServletContext();	
				RequestDispatcher dispatcher=context.getRequestDispatcher("/admin");
				dispatcher.forward(req, res);	
		}
		
			
		Stmt.close();
		Conn.close();
		}
		catch (Exception E) 
		{
		//out.println("SQLException: " + E.getMessage());
				ServletContext context=getServletContext();	
				RequestDispatcher dispatcher=context.getRequestDispatcher("/admin");
				dispatcher.forward(req, res);	
		}
		
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

}
