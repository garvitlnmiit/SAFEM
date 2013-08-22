<html>
<head>
<title>Feedback Form</title>
<style type="text/css">
form{
    border:solid 2px #CD853F;
    background:#FFDEAD;
    margin-left:auto;
    margin-right:auto;
    width:70%;
text-align:left;
}
h1
{font-style:italic;
color:#00008B;}
</style>
<!--
$(document).ready(function()
	{$(":radio").click(function(event)
		{alert("You clicked me!!");
		$('#i').val('1');
		});
	});


-->
<script type="text/javascript" src="validate.js"></script>
</head>
<body bgcolor="#E6E6FA">
<h1 align="center"> Feedback Form!!</h1>
<form action="/feedback" onsubmit="return validateForm()" method="post">

<table border="0">
<tr>
<td width="550">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>Course name</b></td>
<td width="550">:&nbsp;&nbsp;<input type="text" name="cname" value="<%= request.getParameter("cid")%>" id="my-text-box" required="required"/></td>
</tr>
<tr>
<td width="550"></td><td width="550"></td>
</tr>
<tr>
<td width="550"></td><td width="550"></td>
</tr>
<tr>
<td width="550">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>Faculty name</b></td>
<td>:&nbsp;&nbsp;<input type="text" name="fname" value="<%=request.getParameter("iid") %>" id="my-text-box" required="required"/></td>
</tr>
<tr>
<td width="550"></td><td width="550"></td>
</tr>
<tr>
<td width="550"></td><td width="550"></td>
</tr>

<tr>
<td width="550">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>Organization and Coherence</b></td>
<td width="550">:
<input type="radio" name="o" value="poor" required="required"/>Poor&nbsp;
<input type="radio" name="o" value="average" required="required"/>Average&nbsp;
<input type="radio" name="o" value="good" required="required"/>Good&nbsp;
<input type="radio" name="o" value="excellent" required="required"/>Excellent</td>
</tr>
<tr>
<td width="550"></td><td width="550"></td>
</tr>
<tr>
<td width="550"></td><td width="550"></td>
</tr>
<tr>
<td width="550">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>Teaching Skills</b></td>
<td width="550">:
<input type="radio" name="t" value="poor" required="required"/>Poor&nbsp;
<input type="radio" name="t" value="average" required="required"/>Average&nbsp;
<input type="radio" name="t" value="good" required="required"/>Good&nbsp;
<input type="radio" name="t" value="excellent" required="required"/>Excellent</td>
</tr>
<tr>
<td width="550"></td><td width="550"></td>
</tr>
<tr>
<td width="550"></td><td width="550"></td>
</tr>
<tr>
<td width="550">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>Continuous Interaction</b></td>
<td width="550">:
<input type="radio" name="c" value="poor" required="required"/>Poor&nbsp;
<input type="radio" name="c" value="average" required="required"/>Average&nbsp;
<input type="radio" name="c" value="good" required="required"/>Good&nbsp;
<input type="radio" name="c" value="excellent" required="required"/>Excellent</td>
</tr>
<tr>
<td width="550"></td><td width="550"></td>
</tr>
<tr>
<td width="550"></td><td width="550"></td>
</tr>
<tr>
<td width="550">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>Overall rating of course</b></td>
<td width="550">:
<input type="radio" name="r" value="poor" required="required"/>Poor&nbsp;
<input type="radio" name="r" value="average" required="required"/>Average&nbsp;
<input type="radio" name="r" value="good" required="required"/>Good&nbsp;
<input type="radio" name="r" value="excellent" required="required"/>Excellent</td>
</tr>
<tr>
<td width="550"></td><td width="550"></td>
</tr>
<tr>
<td width="550"></td><td width="550"></td>
</tr>
<tr>
<td width="550">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>Overall rating of the instructor</b></td>
<td width="550">:
<input type="radio" name="i" value="poor" required="required"/>Poor&nbsp;
<input type="radio" name="i" value="average" required="required"/>Average&nbsp;
<input type="radio" name="i" value="good" required="required"/>Good&nbsp;
<input type="radio" name="i" value="excellent" required="required"/>Excellent</td>
</tr>
<tr>
<td width="550"></td><td width="550"></td>
</tr>
<tr>
<td width="550"></td><td width="550"></td>
</tr>
<tr>
<td width="550">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>Course(contents , perceived , usefulness)</b><br></td>
<td width="550">&nbsp;<textarea cols="50" rows="4" wrap="physical" name="desc" required="required"></textarea></td>
</tr>
<tr>
<td width="550"></td><td width="550"></td>
</tr>
<tr>
<td width="550"></td><td width="550"></td>
</tr>
<tr>
<td width="550">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>Course Appeal</b></td>
<td width="550">:&nbsp;<select name="appeal" required="required">
<option value="" selected="Select">Select</option>
<option value="very boring">Very Boring</option>
<option value="pure cramming">Pure Cramming</option>
<option value="only theoretical">Only Theoretical</option>
<option value="interesting">Interesting</option>
<option value="practically viable">Practically viable</option>
<option value="logical">Logical</option>
</select></td>
</tr>
<tr>
<td width="550"></td><td width="550"></td>
</tr>
<tr>
<td width="550"></td><td width="550"></td>
</tr>
<tr>
<td width="550">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>Your participation in class(_/5)</b></td>
<td width="550">:
<input type="radio" name="p" value="1" required="required"/>1&nbsp;
<input type="radio" name="p" value="2" required="required"/>2&nbsp;
<input type="radio" name="p" value="3" required="required"/>3&nbsp;
<input type="radio" name="p" value="4" required="required"/>4&nbsp;
<input type="radio" name="p" value="5" required="required"/>5</td>
</tr>
<tr>
<td width="550"></td><td width="550"></td>
</tr>
<tr>
<td width="550"></td><td width="550"></td>
</tr>
</table> 
<center>
<input type="submit" value="Submit" />
<input type="reset" /><center><p>("You will be logged out after submitting the feedback")</p>
</form> 
</body>
</html>