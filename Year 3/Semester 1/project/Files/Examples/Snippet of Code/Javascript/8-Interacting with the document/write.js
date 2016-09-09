
function init()
{
 var panel=document.getElementById("panel");
 panel.innerHTML += document.title;

 var pop = open("","","top=200,left=100,width=400,height=100");

 pop.document.write("<title>Popup Window</title>");
 pop.document.write("<img src='debate.png'>");
 pop.document.write("Dynamic Popup Content");
 pop.document.close();

}
onload=init;


