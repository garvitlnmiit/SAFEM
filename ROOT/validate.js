function validateForm()
{
var x=document.forms["Form"]["cname"].value
if (x==null || x=="")
  {
  alert("Course name must be filled");
  return false;
  }
var x=document.forms["Form"]["fname"].value
if (x==null || x=="")
  {
  alert("Faculty name must be filled");
  return false;
  }
 
    var x=document.forms["Form"]["xyz"].value

  var x=document.forms["Form"]["app"].value
if (x==null || x=="")
  {
  alert("Course appeal must be filled");
  return false;
  }
 
}
$('#my-text-box').prop('disabled', true);
