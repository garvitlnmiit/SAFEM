<!DOCTYPE html>
<%@page language="java" %>
<%@page import= "java.io.*" %>
<%@page import= "java.util.*" %>
<%@page import ="java.sql.*" %>
<%@page import ="javax.sql.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
<head>
<title>FEEDBACK!!</title>
<style type="text/css">
h1
{font-style:italic;
color:#00008B;}
</style>
</head>
<%
ResultSet resultSet = null;
ResultSet resultSet1 = null;
ResultSet resultSet2 = null;
Class.forName("com.mysql.jdbc.Driver");
java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/swe","root","root");
Statement st= con.createStatement();
PreparedStatement ps=null;
PreparedStatement ps1=null;
PreparedStatement ps2=null;
String query="select * from feedback_details where course_id='"+request.getParameter("cid")+"'";
ps=con.prepareStatement(query);
ps1=con.prepareStatement(query);
ps2=con.prepareStatement(query);
resultSet = ps.executeQuery();
resultSet1 = ps2.executeQuery();
resultSet2 = ps1.executeQuery();
Integer count=1,org_poor=0,org_avg=0,org_good=0,org_ex=0,t_poor=0,t_avg=0,t_good=0,t_ex=0;
Integer i_poor=0,i_avg=0,i_good=0,i_ex=0;
Integer r_poor=0,r_avg=0,r_good=0,r_ex=0;
Integer ri_poor=0,ri_avg=0,ri_good=0,ri_ex=0;
Integer boring=0,theory=0,cram=0,intrst=0,prac=0,log=0;
%>
<body bgcolor="#E6E6FA">
<% while(resultSet1.next()) 
{	
	if (resultSet1.getString(3).equalsIgnoreCase("poor"))
	{org_poor=org_poor+1;
	}
	else if (resultSet1.getString(3).equalsIgnoreCase("average"))
	{org_avg++;}
	else if (resultSet1.getString(3).equalsIgnoreCase("good"))
	{org_good++;}
	else if (resultSet1.getString(3).equalsIgnoreCase("excellent"))
	{org_ex++;}
	
	if (resultSet1.getString(4).equalsIgnoreCase("poor"))
	{t_poor++;
	}
	else if (resultSet1.getString(4).equalsIgnoreCase("average"))
	{t_avg++;}
	else if (resultSet1.getString(4).equalsIgnoreCase("good"))
	{t_good++;}
	else if (resultSet1.getString(4).equalsIgnoreCase("excellent"))
	{t_ex++;}

	if (resultSet1.getString(5).equalsIgnoreCase("poor"))
	{i_poor++;
	}
	else if (resultSet1.getString(5).equalsIgnoreCase("average"))
	{i_avg++;}
	else if (resultSet1.getString(5).equalsIgnoreCase("good"))
	{i_good++;}
	else if (resultSet1.getString(5).equalsIgnoreCase("excellent"))
	{i_ex++;}

	if (resultSet1.getString(6).equalsIgnoreCase("poor"))
	{r_poor++;
	}
	else if (resultSet1.getString(6).equalsIgnoreCase("average"))
	{r_avg++;}
	else if (resultSet1.getString(6).equalsIgnoreCase("good"))
	{r_good++;}
	else if (resultSet1.getString(6).equalsIgnoreCase("excellent"))
	{r_ex++;}

	if (resultSet1.getString(7).equalsIgnoreCase("poor"))
	{ri_poor++;
	}
	else if (resultSet1.getString(7).equalsIgnoreCase("average"))
	{ri_avg++;}
	else if (resultSet1.getString(7).equalsIgnoreCase("good"))
	{ri_good++;}
	else if (resultSet1.getString(7).equalsIgnoreCase("excellent"))
	{ri_ex++;}

	
	if (resultSet1.getString(9).equalsIgnoreCase("very boring"))
	{boring++;
	}
	else if (resultSet1.getString(9).equalsIgnoreCase("pure cramming"))
	{cram++;}
	else if (resultSet1.getString(9).equalsIgnoreCase("only theoretical"))
	{theory++;}
	else if (resultSet1.getString(9).equalsIgnoreCase("interesting"))
	{intrst++;}
	else if (resultSet1.getString(9).equalsIgnoreCase("practically viable"))
	{prac++;}
	else if (resultSet1.getString(9).equalsIgnoreCase("logical"))
	{log++;}
}
%>

<h1>
<%= request.getParameter("cid")%> Feedback!!
</h1>
Course Instructor:
<% if(resultSet.next()) 
{%>
<%= resultSet.getString(2) %>
<%
}
%>
<br/>
<b>Organization and Coherence:</b><br/>
Poor: <%= org_poor%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
Average: <%= org_avg%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
Good: <%= org_good%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
Excellent: <%= org_ex%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<br/><br/>
<b>Teaching:</b><br/>
Poor: <%= t_poor%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
Average: <%= t_avg%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
Good: <%= t_good%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
Excellent: <%= t_ex%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<br/><br/>
<b>Interaction:</b><br/>
Poor: <%= i_poor%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
Average: <%= i_avg%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
Good: <%= i_good%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
Excellent: <%= i_ex%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<br/><br/>
<b>Rating of the Course:</b><br/>
Poor: <%= r_poor%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
Average: <%= r_avg%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
Good: <%= r_good%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
Excellent: <%= r_ex%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<br/><br/>

<b>Rating of the Instructor:</b><br/>
Poor: <%= ri_poor%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
Average: <%= ri_avg%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
Good: <%= ri_good%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
Excellent: <%= ri_ex%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<br/><br/>
<b>Course Appeal:</b><br/>
Very Boring: <%= boring%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
Pure Cramming: <%= cram%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
Only Theoretical: <%= theory%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
Interesting: <%= intrst%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
Practically viable: <%= prac%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
Logical: <%= log%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<br/><br/>
<b>Course Description:</b>
<table border='1'>
<% while(resultSet2.next()) 
{
%>

<tr>
<td><%= count%>.</td>
<td><%= resultSet2.getString(8) %></td>
</tr>
<% count=count+1;}
%>
</table>
<br/><br/>


</body>
</html>