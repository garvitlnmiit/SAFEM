<html>
<head>
<title>Add Student</title>
<style type="text/css">
form{
    border:solid 2px #CD853F;
    background:#FFDEAD;
    margin-left:auto;
    margin-right:auto;
    width:72%;
text-align:left;
}
h1
{font-style:italic;
color:#00008B;}
</style>
<script type="text/javascript" src="jquery-1.4.3.js" ></script>
<script type="text/javascript">
function haitham()
{   
    if(document.getElementById('e').checked == true)
    {
     document.getElementById('StudentData').style.display = "block";
	}
    else if(document.getElementById('d').checked == true)
    {
      document.getElementById('StudentData').style.display = "none";
	}
}
function check()
{   
	var str='<%= request.getParameter("courseid")%>.xls';
    var value = $("#f1");
	if(value.val()==str)
	{}
	else
	{	alert("WRONG FILE!! ");
		value.replaceWith( value = value.val('').clone(true) );
	}

	}
</script>
</head>
<body bgcolor="#E6E6FA">
<center><h1>ADD STUDENT</h1></center>
<h1>ADD Students [Course: <%= request.getParameter("courseid")%>]: </h1><br/>
<form name="myForm" action="/add_student" method="post"><br />
<input onclick="document.getElementById('f1').disabled = true;" type='radio' id='d' name='db1' value="db" onchange="haitham();" required="required"/>
<b>By Using Database </b>
<br />
		<font face="verdana" color="red">&nbsp;&nbsp;&nbsp;&nbsp;1.Make sure that a table exists with this <b>Course ID</b> [Otherwise list will not be updated]<br/></font>
		<br/>
<input onclick="document.getElementById('f1').disabled = false;" type='radio' id='e' name='db1' value="excel" onchange="haitham();" required="required"/> 
<b>By Uploading Excel Sheet</b> <br /> <br /> 
<input type='hidden' name='cid' value='<%= request.getParameter("courseid")%>' />
		<div id="StudentData">&nbsp;&nbsp;&nbsp;&nbsp;
<input type='file' id='f1' name='filename' onchange="check();" required="required"/><br/><br/>
		<font face="verdana" color="red">&nbsp;&nbsp;&nbsp;&nbsp;1. Excel file should have same name as the <b>CourseID.xls</b><br/>
		&nbsp;&nbsp;&nbsp;&nbsp;2. Excel file should be in folder named <b>'excel'</b> in <b>C:\</b> [Otherwise list will not be updated]</font>
		</div>
<br /><br /> 
&nbsp;&nbsp;&nbsp;&nbsp;

<input type="submit" value="Submit"/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="reset" /><br /> <br /> 
</form>
</body>
</html>	