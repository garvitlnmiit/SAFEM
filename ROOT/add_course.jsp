<!DOCTYPE html>
<%@page language="java" %>
<%@page import ="java.sql.*" %>
<%@page import ="javax.sql.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
<head>
<title>Add Course</title>
<style type="text/css">
h1
{font-style:italic;
color:#00008B;}
</style>
<script type="text/javascript" src="jquery.js"></script>
<script type="text/javascript">
$(document).ready(function()
{$(".fname").change(function()
{var fname = $(this).val();
if(fname.length > 3)
{
	$(".status").html("Checking availability...");
	$.ajax({type: "POST",
			url: "/avail2",
			data: "fname="+ fname,
			success: function(msg)
			{$(".status").ajaxComplete
				(function(event, request, settings)
				{
				$(".status").html(msg);
				var value = $("#fname");
				//alert(html.(msg));
				//var n=0;
					if(msg.search("is already in use")>0)
					{
					value.replaceWith( value = value.val('').clone(true) );
					msg="";
					}
					else
					{
					}});}});}
else
{$(".status").html(" Username should be more than 3 characters");
var value = $("#fname");
value.replaceWith( value = value.val('').clone(true) );}
});});
</script>
<script type="text/javascript">
$(document).ready(function()
{
$(".fid").change(function()
{
var fid = $(this).val();
if(fid.length > 3)
{
	$(".status1").html("Checking availability...");
	$.ajax
	(
		{
			type: "POST",
			url: "/avail3",
			data: "fid="+ fid,
			success: function(msg)
			{
				$(".status1").ajaxComplete
				(function(event, request, settings)
				{
					$(".status1").html(msg);
				var value = $("#f2");
				//alert(html.(msg));
				//var n=0;
				if(msg.search("is already in use")>0)
					{
					value.replaceWith( value = value.val('').clone(true) );
					msg="";
					}
					else
					{msg="";}});}});}

else
{
$(".status1").html(" Faculty ID should be more than 3 characters");
var value = $("#f2");
value.replaceWith( value = value.val('').clone(true) );
}

});
});

</script>
</head>
<%
ResultSet resultSet = null;
Class.forName("com.mysql.jdbc.Driver");
java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/swe","root","root");
Statement st= con.createStatement();
PreparedStatement ps=null;
String query="select user_name from users where category='instructor'";
ps=con.prepareStatement(query);
resultSet = ps.executeQuery();
%>

<body bgcolor="#E6E6FA">
<h1>Add Course</h1>
<form action="/add_course" method="post">
<div>
<b>Course Name: </b><input type="text" name="fname" id="fname" required="required"/><span class="status"></span><br/><br/>
</div>
<div>
<b>Course ID: </b><input type="text" name="fid" id="fid" required="required"/><span class="status1"></span><br/><br/>
</div>
<b>Course Instructor: </b></td>
<select name="iname" required="required">
<option value="" selected="select">Choose - -</option>
<% while(resultSet.next()) {%>
<option value="<%=resultSet.getString(1)%>"><%=resultSet.getString(1) %></option>
<% }%>
</select><br/><br/>

<b>Course Credit: </b>
<input type="number" min="2" max="5" name="credit" required="required" /><br/><br/>
<input type="submit" value="Submit" />
<input type="reset" />
</form> 
</body>
</html>