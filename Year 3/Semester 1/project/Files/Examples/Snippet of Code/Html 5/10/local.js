/* HTML5 in easy steps:example 81.1 */

function storeName()
{
	var username = document.getElementById("username").value;
	if(username === "null" || username==="") return false;
	localStorage.setItem("user", username);
	document.getElementById("username").value="";
	document.getElementById("leg").innerHTML = "\""+ localStorage.getItem("user") + "\" is Stored";
}

function recallName()
{
	if(localStorage.getItem("user") === null) return false;
	document.getElementById("username").value="";
	document.getElementById("leg").innerHTML = "Stored Name is \""+localStorage.getItem("user")+"\"";
}

function removeName()
{
	if(localStorage.getItem("user") === null) return false;
	document.getElementById("leg").innerHTML = "\""+ localStorage.getItem("user") + "\" is Removed";
	localStorage.removeItem("user");
	document.getElementById("username").value = "";
}

