/* HTML5 in easy steps:example 60.1 */

function send()
{
  var address = document.getElementById("adr").value;
  var pattern=/^([a-zA-Z0-9_.-])+@([a-zA-Z0-9_.-])+\.([a-zA-Z])+([a-zA-Z])+/;
  if( ! pattern.test(address)) alert("Invalid Email Address");
  else document.getElementById("form-1").submit();  
}

function init()
{
  document.getElementById("btn").onclick=send;
}

onload=init;