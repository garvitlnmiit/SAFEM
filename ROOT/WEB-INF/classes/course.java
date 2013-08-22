// This file is a part of ***********STUDENT AND FACULTY EVALUATION SYSTEM************

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

class courseDetails
{
	String evalNameString,evalTypeString,marksString,publicViewString,maxMarksString,evalTotFlag,totalFloat,grade,pbv;
	String[] evalNameArray,evalTypeArray,marksArray,publicViewArray,maxMarksArray,pbva;
	int courseCredit,evalStatus,evalGradeFlag=0, evalFlag=0;
}

public class course extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse res) 
	throws ServletException, IOException 
	{
		doGet(req,res);
	}
	
	public void doGet (HttpServletRequest req, HttpServletResponse res)
	    throws ServletException, IOException {
	    res.setContentType("text/html");

	    int i,globFlag=0,tft=0,gradeF=0;
		String courseid,var,delims,err;
		String id_var,uName,temp="",statFlag=null;
		
		courseDetails courseObj=new courseDetails();
		
		delims="[-]";
		var=req.getParameter("courses");
		
		courseid=var.trim();
		
	    ServletOutputStream out = res.getOutputStream();
		
		HttpSession session=req.getSession(false);			// fetching session value for user variable
		uName=(String)session.getAttribute("uName");		

		HttpSession session1=req.getSession();	
		session1.setAttribute("courseId",courseid);		
		
		HttpSession session2=req.getSession(false);			// fetching session value for error-notification
		err=(String)session2.getAttribute("error");	

		HttpSession session3=req.getSession(false);			// fetching session value for graph-Flag
		statFlag=(String)session3.getAttribute("statFlag");			
		
		if(err==null)
		err="";	
		if(statFlag==null)
		statFlag="";

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
	    
		String query="select * from student_details where course_id='"+courseid+"'";
		Statement Stmt = Conn.createStatement();
		ResultSet RS = Stmt.executeQuery(query);

		String query1="select * from course_details where course_id='"+courseid+"'";
		Statement Stmt1 = Conn.createStatement();
		ResultSet RS1 = Stmt1.executeQuery(query1);		

		if(RS1.next())
		{
			courseObj.evalNameString=RS1.getString(6);
			courseObj.evalNameArray=courseObj.evalNameString.split(delims);		
			courseObj.evalTypeString=RS1.getString(8);
			courseObj.evalTypeArray=courseObj.evalTypeString.split(delims);	
			courseObj.maxMarksString=RS1.getString(9);
			courseObj.maxMarksArray=courseObj.maxMarksString.split(delims);				
			courseObj.evalTotFlag=RS1.getString(12);    // eval_total
			courseObj.evalFlag=RS1.getInt(11);		// eval_status
			courseObj.evalGradeFlag=RS1.getInt(13);		// grade flag
			courseObj.pbv=RS1.getString(10);
			courseObj.pbva=(courseObj.pbv).split(delims);
		}

		if(courseObj.evalNameString.equals(""))
			globFlag=1;
		if((courseObj.evalTotFlag).equals("1"))
			tft=1;
		if(courseObj.evalGradeFlag==1)
		gradeF=1;
		
		// ------------------------------------------------------EVAL-GUI-----------------------------------------------------//
		out.println("<html>");
	    out.println("<head><title>");
		out.println(uName);
		out.println("</title>");
		out.println("<script src='main.js' type='text/javascript'></script>");	
		out.println("<link rel='stylesheet' type='text/css' href='evalGui1.css' />");		
		out.println("</head>");
	    out.println("<body>");	    
		//LogOut
		out.println("<tr><td><button onclick='logout()'>Log Out</button></td></tr>");
		//Heading for Html Page		
		out.println("<h3>Student Evaluation <hr id='pati'> </hr></h3>");
		//Status and Error
		out.println("<div id='status'></div>");
		out.println("<div id='draw'>"+err+"</div></br>");	
		out.println("<div id='draw'>"+statFlag+"</div></br>");	
		
		//PUT MENU HERE AFTER DESINGING IN HTML TO BE DONE 0500 hrs on 11 April
		
			out.println("<div id='controlPanel'>");
			out.println("<table><tr><td>");
			out.println("<tr><td>Add Field</td>");
			if(tft==1)
			{
			out.println("<td><select onchange='addField(this)' disabled>");
			out.println("<option value='select'>--</option>");
			out.println("<option value='quiz'>Quiz</option>");
			out.println("<option value='endsem'>End Term</option>");
			out.println("<option value='midsem'>Mid Term</option>");
			out.println("<option value='project'>Project</option>");
			out.println("<option value='assignment'>Assignment</option>");
			out.println("</select>&nbsp;&nbsp;");
			out.println("</td>");
			}
			else
			{
			out.println("<td><select onchange='addField(this)'>");
			out.println("<option value='select'>--</option>");
			out.println("<option value='quiz'>Quiz</option>");
			out.println("<option value='endsem'>End Term</option>");
			out.println("<option value='midsem'>Mid Term</option>");
			out.println("<option value='project'>Project</option>");
			out.println("<option value='assignment'>Assignment</option>");
			out.println("</select>&nbsp;&nbsp;");
			out.println("</td>");			
			}
			
			if(globFlag==1)
			{
			out.println("<td><button class='evalGuiButtons' onclick='performStat()' disabled>Performance Statistic</button>&nbsp;&nbsp;</td>");
			out.println("<td><button class='evalGuiButtons' onclick='evalTotal()' disabled>Evaluate Total</button>&nbsp;&nbsp;</td>");
			out.println("<td><button class='evalGuiButtons' onclick='cutoff()' disabled>Set Cutoff</button>&nbsp;&nbsp;</td></tr>");
			}	
			else
			{
			out.println("<td><button class='evalGuiButtons' onclick='performStat()'>Performance Statistic</button>&nbsp;&nbsp;</td>");
			if(tft==1)
			out.println("<td><button class='evalGuiButtons' onclick='evalTotal()' disabled>Evaluate Total</button>&nbsp;&nbsp;</td>");
			else
			out.println("<td><button class='evalGuiButtons' onclick='evalTotal()'>Evaluate Total</button>&nbsp;&nbsp;</td>");
			
			if(tft==1 && gradeF==0)
			out.println("<td><button class='evalGuiButtons' onclick='cutoff()'>Set Cutoff</button>&nbsp;&nbsp;</td></tr>");
			else
			out.println("<td><button class='evalGuiButtons' onclick='cutoff()' disabled>Set Cutoff</button>&nbsp;&nbsp;</td></tr>");
			}				

			out.println("</table></div>");
		
		
		//Gets information from the table in Data Base		<Changed in this version>
			
		out.println("</br></br><table id='evaluationTable'>");
		out.println("<tr><td>");
		out.println("Public View");
		out.println("</td>");
		// Beign Course Evaluation Fields		
		// Public View <Added in this version>	
	
		for(i=0;i<(courseObj.evalNameArray).length;i++)
		{
			if((courseObj.evalNameArray[0]).equals(""))
			{
				//globFlag=1;
				break;	
			}	

			/*if((courseObj.pbva[i]).equals("n"))
			temp="unset";
			else
			temp="set";*/
			if(courseObj.pbva[i].equals("n"))
			out.println("<td><input class='publicView' type='checkbox' onclick='pubv(this)' id='"+i+"'></td>");	
			else
			out.println("<td><input class='publicView' type='checkbox' onclick='pubv(this)' id='"+i+"' checked></td>");	
		}
		// unique id for total public view
	
		
		if((courseObj.evalTotFlag).equals("1"))		// evaluation total flag
		{
			/*if((courseObj.pbva[i]).equals("n"))
			temp="unset";
			else
			temp="set";*/		
		out.println("<td>&nbsp</td>");
		}
		i++;	// unique id for grade public view
		if(courseObj.evalGradeFlag==1)		// evaluation grade flag
		{
			/*if((courseObj.pbva[i]).equals("n"))
			temp="unset";
			else
			temp="set";*/		
		out.println("<td>&nbsp</td>");		
		}
		
		// End of Public View		
		out.println("</tr>");

		out.println("<tr>");		
		out.println("<th>");
		out.println("Student");
		out.println("</th>");
		// Beign Course Evaluation Fields
		
		for(i=0;i<(courseObj.evalNameArray).length;i++)
		{
		if((courseObj.evalNameArray[0]).equals(""))
			break;
		out.println("<th>"+courseObj.evalNameArray[i]+"-"+courseObj.evalTypeArray[i]+"("+courseObj.maxMarksArray[i]+")"+"</th>");			
		}
		
		//End of Course Evaluation Fields		
		if((courseObj.evalTotFlag).equals("1"))		// evaluation total flag
		out.println("<th>Total (100)</th>");
		if(courseObj.evalGradeFlag==1)		// evaluation grade flag
		out.println("<th>Grade</th>");		

		out.println("</tr>");
		//Start of Putting Data in Evaluation Table
		
		int flag=0;
		String idDiff = new String("evenStudent");
		while(RS.next())
		{
			courseObj.marksString=RS.getString(3);
			courseObj.marksArray=courseObj.marksString.split(delims);
			courseObj.totalFloat=RS.getString(4);
			courseObj.grade=RS.getString(5);
			
			if(flag%2==0)
			{
				idDiff = "evenStudent";
			}
			else
			{
				idDiff = "oddStudent";
			}
			out.println("<tr><td class='"+idDiff+"'>"+RS.getString(1)+"</td>");
			for(i=0;i<(courseObj.marksArray).length;i++) //
			{
			if((courseObj.evalNameArray[0]).equals("")) //added in this version
				break;		
			id_var=i+"-"+RS.getString(1)+"-"+courseid;
			out.println("<td class='"+idDiff+"'>"+"<input class='"+idDiff+"' type='text' id='"+id_var+"' value='"+courseObj.marksArray[i]+"' onchange='updateMarks(this)'></td>");
			}
			if((courseObj.evalTotFlag).equals("1"))			// evaluation total flag
			out.println("<td class='"+idDiff+"'>"+courseObj.totalFloat+"</td>");
			if(courseObj.evalGradeFlag==1)		// evaluation grade flag
			out.println("<td class='"+idDiff+"'>"+courseObj.grade+"</td>");			

			
			out.println("</tr>");
			flag++;
		}
		//End of Putting Data in Evaluation Table
		
		out.println("</table>");
		
		out.println("</body></html>");		

		session2.removeAttribute("error");
		session3.removeAttribute("statFlag");
		//session2.invalidate();	
		// Clean up after ourselves
		RS.close();
		Stmt.close();
		Conn.close();
		
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
