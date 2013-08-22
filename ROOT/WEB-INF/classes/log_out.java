// This file is a part of ***********STUDENT AND FACULTY EVALUATION SYSTEM************

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

class courseDetails
{
	String evalNameString,evalTypeString,marksString,publicViewString,maxMarksString;
	String[] evalNameArray,evalTypeArray,marksArray,publicViewArray,maxMarksArray;
	int courseCredit,evalStatus;
}

public class log_out extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse res) 
	throws ServletException, IOException 
	{
		doGet(req,res);
	}
	
	public void doGet (HttpServletRequest req, HttpServletResponse res)
	    throws ServletException, IOException {
	    res.setContentType("text/html");

			HttpSession session=req.getSession();
			session.removeAttribute("err");
			session.removeAttribute("uName");
			session.removeAttribute("upwd");
			session.removeAttribute("ucat");
			session.removeAttribute("courseId");
			session.removeAttribute("error");
			res.sendRedirect("index.html");
		}

}