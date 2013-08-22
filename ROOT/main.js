function updateMarks(clickId)
{
	alert('Update Operation'); 
	var value1=clickId.value; 
	var id1=clickId.id;
	if(window.XMLHttpRequest)
	{
		xmlhttp=new XMLHttpRequest();
	}
	else
	{ 
		xmlhttp=new ActiveXObject('Microsoft.XMLHTTP');
	}
	xmlhttp.onreadystatechange=function(){
	if(xmlhttp.readyState==4 && xmlhttp.status==200){
	document.getElementById('status').innerHTML=xmlhttp.responseText;} }
	xmlhttp.open('GET','/update?a='+id1+'&b='+value1,true); xmlhttp.send();
}
/*
function performStat()
{
	if(window.XMLHttpRequest){
	xmlhttp=new XMLHttpRequest();}
	else{ xmlhttp=new ActiveXObject('Microsoft.XMLHTTP');}
	xmlhttp.onreadystatechange=function(){
	if(xmlhttp.readyState==4 && xmlhttp.status==200){
	document.getElementById('draw').innerHTML=xmlhttp.responseText;} }
	xmlhttp.open('GET','/draw_s',true); xmlhttp.send();
}
*/

function addField(change)
{
	if(change.value=='select'){
	alert('Select valid field type !!');}
	else{
	var examType=prompt('Please enter the field name:','');
	if(examType==null)
	{
		alert('No field name input!!');
		return;
	}
	var maxMarks=prompt('Please enter the maximum marks:','');
	if(maxMarks==null)
	{
		alert('Maximum marks can not be empty!!');
		return;
	}	
	if(examType=='' || maxMarks=='') alert('Field should not be blank !!');
	else window.location='addfield?a='+change.value+'&b='+examType+'&c='+maxMarks }
}
function evalTotal()
{
	window.location='evaltot';
}
function performStat()
{
	var fieldName=prompt('Please enter the field name:','');
	if(fieldName==null)
	return;
	if(fieldName==''){alert('Field can not be empty!!'); return;}
	window.location='draw_pie?b='+fieldName
}
function pubv(varb)
{
	if(window.XMLHttpRequest){
	xmlhttp=new XMLHttpRequest();}
	else{ xmlhttp=new ActiveXObject('Microsoft.XMLHTTP');}
	xmlhttp.onreadystatechange=function(){
	if(xmlhttp.readyState==4 && xmlhttp.status==200){
	document.getElementById('draw').innerHTML=xmlhttp.responseText;} }
	xmlhttp.open('GET','/publicView?a='+varb.id+'&b='+varb.checked,true); xmlhttp.send();
}
function logout()
{
	window.location='log_out';
}
function cutoff()
{
	window.location='cutoff.html';
}