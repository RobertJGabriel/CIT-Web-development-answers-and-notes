/* HTML5 in easy steps:example 51.2	*/

var rate = new Array(1,0.734235,0.62279);

function convert(n)
{
	var num=document.getElementById("c"+n).value;
	var i, v, $equiv = rate[n];
		
	for (i=0; i<rate.length; i++)
	{
		if (i!==n)
		{
			v = Math.round(rate[i]*num/$equiv*100)/100;
			document.getElementById("c"+i).value=v;
		}
	}
}

function init()
{
	var i;
	for(i=0; i<3; i++)
	{
		document.getElementById("c"+i).value="";
	}

	var status=document.getElementById("status")
	status.innerHTML="Latest<br>Rates";
	status.style.font="bold";
	status.style.color="white";
	status.style.background="green";
	status.style.textAlign="center";
	status.style.padding="2px";
}

onload=init;