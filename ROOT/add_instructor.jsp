<html>
<head>
<title>Add Instructor</title>
<style type="text/css">
h1
{font-style:italic;
color:#00008B;
}
</style>
<script type="text/javascript" src="jquery.js"></script>
<script type="text/javascript">
$(document).ready(function()
{$(".uname").change(function()
{var uname = $(this).val();
if(uname.length > 3)
{
	$(".status").html("Checking availability...");
	$.ajax({type: "POST",
			url: "/avail",
			data: "uname="+ uname,
			success: function(msg)
			{$(".status").ajaxComplete
				(function(event, request, settings)
				{
				$(".status").html(msg);
				var value = $("#f1");
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
var value = $("#f1");
value.replaceWith( value = value.val('').clone(true) );}
});});
</script>
<script type="text/javascript">
$(document).ready(function()
{
$(".uid").change(function()
{
var uid = $(this).val();
if(uid.length > 3)
{
	$(".status1").html("Checking availability...");
	$.ajax
	(
		{
			type: "POST",
			url: "/avail1",
			data: "uid="+ uid,
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
<body bgcolor="#E6E6FA">
<h1>Add Instructor</h1>
<form action="/instructor" method="post">

<div>
<b>Faculty name :</b>
<input type="text" class="uname" name="uname" id="f1" required="required"/><span class="status"></span>
</div>
<br/>
<div>
<b>Faculty ID :</b>
<input type="text" class="uid" id="f2" name="fid" required="required"/><span class="status1"></span>
</div>
<br/>
<b>Faculty Email ID: </b><input type="email" id="emailid" name="femail" required="required"/><br/><br/>
<input type="submit" value="Submit" />
<input type="reset" />
</form> 
</body>
</html>