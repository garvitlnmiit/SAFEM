
<!DOCTYPE HTML5>

<html>
<head>
<title>Change Password</title>
<script type="text/javascript" src='changePwd.js'></script>
<script type="text/javascript" src="jquery.js"></script>
<script type="text/javascript">
$(document).ready(function()
{$(".old").change(function()
{var old = $(this).val();
var cat= '<%= request.getParameter("sid")%>';
	$(".status").html("Checking old password...");
	$.ajax({type: "POST",
			url: "/check_old",
			data: "old="+old+"&sid="+cat,
			success: function(msg)
			{$(".status").ajaxComplete
				(function(event, request, settings)
				{
				$(".status").html(msg);
				var value = $("#old");
				//alert(html.(msg));
				//var n=0;
					if(msg.search("INCORRECT")>0)
					{
					value.replaceWith( value = value.val('').clone(true) );
					msg="";
					}
					else
					{
					}});}});
});});
</script>
</head>
<body>

<form name='pwd_login' action='/change_pass' method="post">
<div>
<b>Old Password :</b>
<input type="password" class="old" id="old" required="required"/><span class="status"></span>
</div><br/>
New Password:<input type="password" name="newp" required="required"><br/>
Confirm New Password:<input type="password" name="renew" required="required"><br/>
<input type="hidden" name="sid" value='<%=request.getParameter("sid")%>' />
<input type="submit" value="Submit" /></td>
</form>
<div id="err"></div>
</body>
</html>