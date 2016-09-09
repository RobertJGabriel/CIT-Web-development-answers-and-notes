var pop;

function showPop()
{
  pop.style.visibility="visible"; 
}

function hidePop()
{
 pop.style.visibility="hidden"; 
}

function init()
{
 pop = document.getElementById("popupLayer");
 pop.style.visibility="hidden"; 

 var obj=document.getElementById("contentLayer");
 obj.onmouseover=showPop;
 obj.onmouseout=hidePop;
}
onload=init;

