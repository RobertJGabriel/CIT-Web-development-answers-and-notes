var obj;

function over()
{ 
  obj.style.background="yellow"; 
}

function down()
{ 
  obj.style.background="lime"; 
}

function up()
{ 
  obj.style.background="yellow"; 
}

function out()
{ 
  obj.style.background="aqua"; 
}


function init()
{
 obj=document.getElementById("active");
 
 obj.style.width="100px";
 obj.style.background="aqua";
 obj.style.padding="5px";
 obj.style.border="2px solid black";
 obj.style.textAlign="center";

 obj.onmouseover=over;
 obj.onmousedown=down;
 obj.onmouseup=up;
 obj.onmouseout=out;
}
onload=init;

