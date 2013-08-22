// This file is a part of ***********STUDENT AND FACULTY EVALUATION SYSTEM************

import org.jfree.chart.axis.CategoryAxis;   
import org.jfree.chart.axis.CategoryLabelPositions;   
import org.jfree.chart.axis.NumberAxis;   
import org.jfree.chart.plot.CategoryPlot;   
import java.awt.Color;
import javax.servlet.*;
import javax.servlet.http.*;
  import java.sql.*;
  import java.util.*;
  import java.io.IOException;
  import java.io.OutputStream;
  import javax.servlet.ServletException;
  import javax.servlet.http.HttpServlet;
  import javax.servlet.http.HttpServletRequest;
  import javax.servlet.http.HttpServletResponse;

 import org.jfree.chart.ChartFactory;
 import org.jfree.chart.ChartUtilities; /* This class will return the chart to the output stream */
 import org.jfree.chart.JFreeChart; /* This class will hold the chart object */
 import org.jfree.data.category.DefaultCategoryDataset; /* This class will help the servlet to load chart data */
 import org.jfree.chart.plot.PlotOrientation;
 /* Main servlet code starts here */
 
 public class draw_pie extends HttpServlet {
 
 public draw_pie() {
 
 }
 
 public void doGet(HttpServletRequest request, HttpServletResponse response)
 throws ServletException, IOException {
         OutputStream out = response.getOutputStream(); /* Get the output stream from the response object */
		
		String fieldName="",courseId,fieldname="",marks="",mMarks="",totFlag="";
		String[] fieldnameArray,marksArray,mMarksArray;
		int[] count;
		float[] studentMarksArray;
		int index,i=0,j,maxVal,k;
		double divten=0.0,averageClass=0.0,maxOpted=0.0,minOpted=0.0;
		
		studentMarksArray=new float[200];
		count=new int[10];
		
		fieldName=request.getParameter("b");
 		HttpSession session=request.getSession(false);
		courseId=(String)session.getAttribute("courseId");
		//courseId="cs101";
		
	    try {
		Class.forName("org.gjt.mm.mysql.Driver").newInstance();
	    }
	    catch (Exception E) {
		//out.println("Unable to load driver.");
		E.printStackTrace();
	    }

	    try {
		Connection Conn = 
		    DriverManager.getConnection("jdbc:mysql://localhost/swe?user=root&password=root");
	    
		String query="select * from course_details where course_id='"+courseId+"'";
		Statement Stmt = Conn.createStatement();
		ResultSet RS = Stmt.executeQuery(query);
		
		while(RS.next())
		{
				fieldname=RS.getString(8);
				mMarks=RS.getString(9);
				totFlag=RS.getString(12);
		}
		
		
		
		fieldnameArray=fieldname.split("[-]");
		mMarksArray=mMarks.split("[-]");
		
		for(i=0;i<fieldnameArray.length;i++)
		{
			if(fieldnameArray[i].equals(fieldName))
			break;
		}
		
		if(fieldnameArray.length==i && (fieldName.toLowerCase()).equals("total")==false)		// if entered field name not found!!
		{
			HttpSession session1 = request.getSession(true);
			session1.setAttribute("statFlag","The field name entered not found!!");
			String url="/course_s?courses="+courseId;	
			response.sendRedirect(url);	
		}
		//----------------------------------------------
		else if((fieldName.toLowerCase()).equals("total"))	// if entered field name is TOTAL/Total/total..
		{
			//out.println("executed!!");
			if(totFlag.equals("0"))
			{
					HttpSession session1 = request.getSession(true);
					session1.setAttribute("statFlag","The field name entered not found!!");
					String url="/course_s?courses="+courseId;	
					response.sendRedirect(url);			
			}
			else
			{
			String query2="select * from student_details where course_id='"+courseId+"'";
			Statement Stmt2 = Conn.createStatement();
			ResultSet RS2 = Stmt2.executeQuery(query2);	
			i=0;
			divten=100/10.0;
			divten = Math.round(divten * 100.0) / 100.0;			
			while(RS2.next())
			{
				studentMarksArray[i++]=RS2.getFloat(4);
			}
			Arrays.sort(studentMarksArray,0,i-1);	// Marks of the students in that course are sorted in the ascending order.
			maxOpted=studentMarksArray[i-1];
			minOpted=studentMarksArray[0];
		
			for(j=0;j<i;j++)
			{
				averageClass=averageClass+studentMarksArray[j];
			}
		
			averageClass=averageClass/(i);
			k=0;
			for(j=0;j<i;j++)
			{
				if(0<=studentMarksArray[j] && studentMarksArray[j]<=divten)
				count[0]++;
				else if((divten+1)<=studentMarksArray[j] && studentMarksArray[j]<=2*divten)
				{k++; count[1]++;}
				else if((2*divten+1)<=studentMarksArray[j] && studentMarksArray[j]<=3*divten)
				{k++; count[2]++;}
				else if((3*divten+1)<=studentMarksArray[j] && studentMarksArray[j]<=4*divten)
				{k++; count[3]++;}
				else if((4*divten+1)<=studentMarksArray[j] && studentMarksArray[j]<=5*divten)
				{k++; count[4]++;}
				else if((5*divten+1)<=studentMarksArray[j] && studentMarksArray[j]<=6*divten)
				{k++; count[5]++;}
				else if((6*divten+1)<=studentMarksArray[j] && studentMarksArray[j]<=7*divten)
				{k++; count[6]++;}
				else if((7*divten+1)<=studentMarksArray[j] && studentMarksArray[j]<=8*divten)
				{k++; count[7]++;}
				else if((8*divten+1)<=studentMarksArray[j] && studentMarksArray[j]<=9*divten)
				{k++; count[8]++;}
				else if((9*divten+1)<=studentMarksArray[j] && studentMarksArray[j]<=10*divten)
				{k++; count[9]++;}
				else
				{;}
			}
			}			
		}
		//-------------------------------------------------
		else		// if entered field name is from QUIZ..
		{
		index=i;
		maxVal=Integer.parseInt(mMarksArray[index]);	// Maximum marks of the field type selected.
		
		divten=maxVal/10.0;
		divten = Math.round(divten * 100.0) / 100.0;
		
		String query1="select * from student_details where course_id='"+courseId+"'";
		Statement Stmt1 = Conn.createStatement();
		ResultSet RS1 = Stmt1.executeQuery(query1);
		
		i=0;
		while(RS1.next())
		{
			marks=RS1.getString(3);
			marksArray=marks.split("[-]");
			studentMarksArray[i++]=Integer.parseInt(marksArray[index]);
		}
		
		Arrays.sort(studentMarksArray,0,i-1);	// Marks of the students in that course are sorted in the ascending order.
		
		maxOpted=studentMarksArray[i-1];
		minOpted=studentMarksArray[0];
		
		for(j=0;j<i;j++)
		{
			averageClass=averageClass+studentMarksArray[j];
		}
		
		averageClass=averageClass/(i);
		
		k=0;
		for(j=0;j<i;j++)
		{
			if(0<=studentMarksArray[j] && studentMarksArray[j]<=divten)
			count[0]++;
			else if((divten+1)<=studentMarksArray[j] && studentMarksArray[j]<=2*divten)
			{k++; count[1]++;}
			else if((2*divten+1)<=studentMarksArray[j] && studentMarksArray[j]<=3*divten)
			{k++; count[2]++;}
			else if((3*divten+1)<=studentMarksArray[j] && studentMarksArray[j]<=4*divten)
			{k++; count[3]++;}
			else if((4*divten+1)<=studentMarksArray[j] && studentMarksArray[j]<=5*divten)
			{k++; count[4]++;}
			else if((5*divten+1)<=studentMarksArray[j] && studentMarksArray[j]<=6*divten)
			{k++; count[5]++;}
			else if((6*divten+1)<=studentMarksArray[j] && studentMarksArray[j]<=7*divten)
			{k++; count[6]++;}
			else if((7*divten+1)<=studentMarksArray[j] && studentMarksArray[j]<=8*divten)
			{k++; count[7]++;}
			else if((8*divten+1)<=studentMarksArray[j] && studentMarksArray[j]<=9*divten)
			{k++; count[8]++;}
			else if((9*divten+1)<=studentMarksArray[j] && studentMarksArray[j]<=10*divten)
			{k++; count[9]++;}
			else
			{;}
		}
		
		
		/*out.println(divten);
		for(j=0;j<10;j++)
		{
			out.println(""+count[j]+"");
			
		}*/
		
	    }
		}
	    catch (SQLException E) {
		/*out.println("SQLException: " + E.getMessage());
		out.println("SQLState:     " + E.getSQLState());
		out.println("VendorError:  " + E.getErrorCode());
		*/
		}	

			try {

                DefaultCategoryDataset bar_chart_servlet = new DefaultCategoryDataset();
				String temp;

		
				for(j=0;j<10;j++)
				{
				if(j==0)
				{
				temp=""+0+"-"+divten+"";
                bar_chart_servlet.addValue(count[j],temp,"");				
				}
				else
				{
				temp=""+j*divten+1+"-"+(j+1)*divten+"";
                bar_chart_servlet.addValue(count[j],temp,"");
				}
				}
				
				String title="Performance Statistics\n Minimum="+minOpted+" Average="+averageClass+" Maximum="+maxOpted;
                JFreeChart BarChartObject=ChartFactory.createBarChart(title,"Marks","Students",bar_chart_servlet,PlotOrientation.VERTICAL,true,true,false);
                
				BarChartObject.setBackgroundPaint(Color.white);
				CategoryPlot plot = (CategoryPlot) BarChartObject.getPlot();
				NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();   
				rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		
                response.setContentType("image/png"); 
                
               
                ChartUtilities.writeChartAsPNG(out, BarChartObject, 900, 500);
         }
         catch (Exception e) {
                 System.err.println(e.toString()); 
         }
         finally {
                 out.close();
         }
		 }
    }
	
