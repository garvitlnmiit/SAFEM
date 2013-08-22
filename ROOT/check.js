$(document).ready(function()
{$(".check").change(function()
{var check = $(this).val();
	$(".status").html("Checking this ID...");
	$.ajax({type: "POST",
			url: "/check_id",
			data: "check="+ check,
			success: function(msg)
			{$(".status").ajaxComplete
				(function(event, request, settings)
				{
				$(".status").html(msg);
				var value = $("#check");
				//alert(html.(msg));
				//var n=0;
					if(msg.search("Stop")>0)
					{
					value.replaceWith( value = value.val('').clone(true) );
					msg="";
					}
					else
					{
					}});}});
});});
