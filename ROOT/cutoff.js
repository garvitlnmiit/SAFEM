function validate()
{
	var len = document.forms['coEntry'].length;
	var i,str='';

	len=len-1;
	
	for(i=0;i<len-1;i++)
	{
		if(document.forms['coEntry'][i].value=='' || document.forms['coEntry'][i+1].value=='') //(document.forms['coEntry'][i].value > document.forms['coEntry'][i+1].value)
		break;
		else if(parseInt(document.forms['coEntry'][i].value) > parseInt(document.forms['coEntry'][i+1].value))
		break;
		else
		str=str+document.forms['coEntry'][i].value+'-';
	}
	str=str+document.forms['coEntry'][i].value+'-';
	
	if(i!=len-1)
	{
	alert('sorry! invalid input, try again!!'); //'sorry! invalid input, try again!!'
	return;
	}
	
	window.location='cutOff?a='+str;
}

function changePwd(){
window.location='changePwd.html';
}
function logout()
{
	window.location='log_out';
}