// This file is a part of ***********STUDENT AND FACULTY EVALUATION SYSTEM************

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.util.*;

public class evaltot extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse res) 
	throws ServletException, IOException 
	{
		doGet(req,res);
	}
	
	public void doGet (HttpServletRequest req, HttpServletResponse res)
	    throws ServletException, IOException {
	    res.setContentType("text/html");

	    ServletOutputStream out = res.getOutputStream();
		String delims="[-]",fieldType="",courseId,quiz=null,midt=null,endt=null,asg=null,pro=null;
		String[] fieldTypeArray;
		String[] filter = new String[5];
		int i=0,k=0;
		HttpSession session=req.getSession(false);				// get value of courseid from the session
		courseId=(String)session.getAttribute("courseId");		
		
	    try {
		Class.forName("org.gjt.mm.mysql.Driver").newInstance();
	    }
	    catch (Exception E) {
		out.println("Unable to load driver.");
		E.printStackTrace();
	    }

	    try {
		Connection Conn = 
		    DriverManager.getConnection("jdbc:mysql://localhost/swe?user=root&password=root");
	    
		String query="select * from course_details where course_id='"+courseId+"'";
		Statement Stmt = Conn.createStatement();
		ResultSet RS = Stmt.executeQuery(query);						// course_details
		while(RS.next())
		{
			fieldType=RS.getString(6);	// eval_field_name
		}
		
		fieldTypeArray=fieldType.split(delims);		
		for(i=0;i<fieldTypeArray.length;i++)
		{
			if(quiz==null && fieldTypeArray[i].equals("quiz"))
			{filter[k++]="quiz"; quiz="";}
			else if(endt==null && fieldTypeArray[i].equals("endsem"))
			{filter[k++]="endsem"; endt="";}
			else if(midt==null && fieldTypeArray[i].equals("midsem"))
			{filter[k++]="midsem"; midt="";}
			else if(asg==null && fieldTypeArray[i].equals("assignment"))
			{filter[k++]="assignment"; asg="";}
			else if(pro==null && fieldTypeArray[i].equals("project"))
			{filter[k++]="project"; pro="";}
			else
			{;}			
		}
		
		/*if(endt==null)
		{
		String error="Can't evaluate total before end semester examinations!!";	
					HttpSession session3=req.getSession();	
		session3.setAttribute("error",error);		
		
		String url="/course_s?courses="+courseId;
		res.sendRedirect(url);
		}*/
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Evaluate Total</title>");
		out.println("<script>");
		out.println("function caltot(submit){");
		out.println("var len = document.forms['frm'].length;");
		out.println("var name='';");
		out.println("var nameval='';");
		out.println("var temp=0;");
		out.println("for(i=0;i<len-1;i++){");
		out.println("name=name+document.forms['frm'][i].id+'-';");
		out.println("nameval=nameval+document.forms['frm'][i].value+'-';");
		out.println("temp=temp+parseFloat(document.forms['frm'][i].value);");  // to make sure that the total must be 100%
		out.println("if(document.forms['frm'][i].value=='')");
		out.println("break;");
		out.println("}");
		out.println("if(i!=len-1)");
		out.println("{alert('Fields can not be empty!!');");
		out.println("return;}");
		out.println("if(temp!=100){alert('Sum should be 100%'); return;}");
		out.println("window.location='/evaltot_a?a='+name+'&b='+nameval}");					
		out.println("</script>");
		out.println("<link rel='stylesheet' type='text/css' href='css/handle.css'>");
		out.println("</head>");
		out.println("<body>");
		//Top Left Buttons
		out.println("<div id='topRightPanel'>");
		out.println("<button onclick='changePwd()'>Change Password</button>");
		out.println("<button onclick='logout()'>Log Out</button>");
		out.println("</div>");
		//Top Right Heading
		out.println("<div id='topLeftPanel'>");
		out.println("<h1>Evaluate Total</h1>");
		out.println("</div>");
		out.println("<form name='input' id='frm'>");
		out.println("<table id='box'>");
		for(i=0;i<k;i++)
		{
		out.println("<tr><td>"+filter[i]+"\nweightage(int):</td><td><input type='text' id='"+filter[i]+"'></td><td>%</td></tr>");
		}
		out.println("<tr><td></br></td></tr>");
		out.println("<tr><td colspan='3'><input type='button' onclick='caltot()' value='Get Total'></td></tr>");
		out.println("</table></form><br/><br/>");
		out.println("<div id='tot'></div>");
		out.println("</body>");
		out.println("</html>");
		}
	    catch (Exception E) {
		out.println("--> "+E);
		E.printStackTrace();
		}
		
	}
}
