/* HTML5 in easy steps:example 6.1	*/

function init()
{
	var h1tags = document.getElementsByTagName("h1");
	h1tags[1].onclick = react;
}

function react()
{
	this.innerHTML = "Clicked!"; this.style.color = "red";
}

onload=init;