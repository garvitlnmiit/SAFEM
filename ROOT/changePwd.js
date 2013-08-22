function changePwd(val)
{
var opwd,npwd,cnpwd;
//alert(document.forms['pwd_login']['opwd'].value);
opwd=document.forms['pwd_login']['opwd'].value;
npwd=document.forms['pwd_login']['npwd'].value;
cnpwd=document.forms['pwd_login']['cnpwd'].value;

if(!opwd || !npwd || !cnpwd)
{
	document.getElementById("err").innerHTML="Invalid Input, try again!!";
	return;
}
if(npwd!=cnpwd)
{
	document.getElementById("err").innerHTML="Password does not match, try again!!";
	return;
}
window.location='pwdfun?a='+opwd+'&b='+npwd;
}