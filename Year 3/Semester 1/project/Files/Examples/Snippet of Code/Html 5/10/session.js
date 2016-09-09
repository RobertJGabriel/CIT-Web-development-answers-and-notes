/* HTML5 in easy steps:example 82.1 */

function init()
{
	sessionStorage.setItem( "originalList" , document.getElementById("list").innerHTML );
}
onload=init;

function restore()
{
	sessionStorage.setItem( "customList" , document.getElementById("list").innerHTML );
	document.getElementById("list").innerHTML=sessionStorage.getItem("originalList");
}

function show()
{
	document.getElementById("list").innerHTML=sessionStorage.getItem("customList");
}

function wipe()
{
	document.getElementById("list").innerHTML=sessionStorage.getItem("originalList");
	sessionStorage.removeItem("customList");
}