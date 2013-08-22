<!DOCTYPE html>
<%@page language="java" %>
<%@page import ="java.sql.*" %>
<%@page import ="javax.sql.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
<head>
<title>Report Card</title>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1251">
<link rel="stylesheet" type="text/css" href="style.css" />
<script>
function printpage()
{
document.getElementById("test").style.display = 'none';
window.print();
}
</script>
</head>
<%
ResultSet resultSet = null;
Class.forName("com.mysql.jdbc.Driver");
java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/swe","root","root");
String sid= request.getParameter("sid");
String name="";
ResultSet rs1=null;
ResultSet rs2=null;
String query="select * from users where userid='"+request.getParameter("sid")+"'";	
Statement Stmt = con.createStatement();
ResultSet rs = Stmt.executeQuery(query);

Integer count=1;
if(rs.next())
{
name=rs.getString(4);

String query1="select * from student_details where student_id='"+request.getParameter("sid")+"'";	
Statement Stmt1 = con.createStatement();
rs1 = Stmt1.executeQuery(query1);

}
%>

<body>
<div id="test">
<input type="button" value="Print this page" onclick="printpage()">
</div>
<br/>
<div id="wrapper">
	<div id="header" align="center">
	<font face="tahoma" color="darkblue" size=5><strong><b>THE LNM INSTITUTE OF INFORMATION TECHNOLOGY</b></strong></font><br/>	
	<font face="verdana" color="black"><b>(Deemed University)</b></font><br/><br/>
	<font face="verdana" color="black"><strong><b>GRADE - REPORT</b></strong></font>	
	</div>
	<div id="content">
	<table border=0 width="100%">
	<tr>
	<td width="10%"><font face="verdana" color="black"><b>ROLL NO.</b></font></td>
	<td width="25%"><font face="verdana" color="black"><b><%= sid%></b></font></td>
	<td align="center" width="55%"><font face="verdana" color="black"><b>UG PROGRAMME</b></font></td>
	</tr><tr>
	<td><font face="verdana" color="black"><b>NAME</b></font></td>
	<td><font face="verdana" color="black"><b><%= name%></b></font></td>
	<td align="center" colspan="40"><font face="verdana" color="black"><b>Computer Science and Engineering</b></font></td>
	</tr>
	</table>
	<br/>
	<hr>
	<table border=0 width="100%">
	<tr>
	<td width="10%"><font face="verdana" color="black"><b></b></font></td>
	<td width="50%" align="center"><font face="verdana" color="black"><b>Title</b></font></td>
	<td width="10%" align="center"><font face="verdana" color="black"><b>Unit</b></font></td>
	<td width="10%" align="center"><font face="verdana" color="black"><b>Grade</b></font></td>
	</tr>
	</table>
	<hr>
	<table border=0 width="100%">
<%while(rs1.next())
{	
String id=rs1.getString(2);
String id1=rs1.getString(5);

String query2="select * from course_details where course_id='"+id+"'";	
Statement Stmt2 = con.createStatement();
rs2 = Stmt2.executeQuery(query2);
if(rs2.next()){
%>

	<tr>
	<td width="10%" align="center"><font face="verdana" color="black"><b><%= count%></b></font></td>
	<td width="50%" align="left"><font face="verdana" color="black"><b><%= rs2.getString(2)%></b></font></td>
	<td width="10%" align="center"><font face="verdana" color="black"><b><%= rs2.getString(5)%></b></font></td>
	<td width="10%" align="center"><font face="verdana" color="black"><b><%= id1%></b></font></td>
	</tr>
<% }

count=count+1;
}

%>	
	</table><BR/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<font face="verdana" color="black"><b>STUDENT HAS NOT YET COMPLETED THIS PROGRAMME</b></font>
	<hr>
	<div align="center">
	<font face="verdana" color="black"><b>Letter Grades: A, AB, B, BC, C, CD, D, F(Fail)</b></font><br/>
	<font face="verdana" color="black"><b>Points: A=10, AB=9, B=8, BC=7, C=6, CD=5, D=4, F=0</b></font><br/><br/>
	</div>	
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<br/>
	<br/><br/><br/><br/><br/>
	<table border=0 width="100%">
	<tr><td width="25%"><font face="verdana" color="black"><b>Academic Office</b></font></td>
	<td width="25%"><font face="verdana" color="black"><b>LNMIIT, Jaipur</b></font></td>
	<td width="25%"><font face="verdana" color="black"><b>Date:</b></font></td>
	<td width="25%"><font face="verdana" color="black"><b>Registrar</b></font></td></tr>
	</table>
	</div>
	<div id="footer" align="center">
	<font face="verdana" color="black"><b>Campus : Rupa ki Nangal, Post-Sumel, Via-Jamdoli, Jaipur-302031 (Rajasthan) INDIA,</b></font><br/>
	<font face="verdana" color="black"><b>Tel.: +91-141-268 9011/12/13, Fax: +91-141-518 9214, 268 9014</b></font><br/>
	<font face="verdana" color="black"><b>e-mail: info.lnmiit@lnmiit.ac.in, website: www.lnmiit.org</b></font>
	</div>
</div>
</body>
</html>
