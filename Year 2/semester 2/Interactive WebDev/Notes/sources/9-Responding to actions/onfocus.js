function focusResponse()
{ 
 this.value = "In Focus";
}

function blurResponse()
{ 
 this.value = "Focus Lost";
}

function init()
{
 var panel=document.getElementById("panel");
 panel.innerHTML += "<input type='text' id='txt1'>";
 panel.innerHTML += "<input type='text' id='txt2'>";
 
 var field1=document.getElementById("txt1");
 field1.onfocus=focusResponse;
 field1.onblur=blurResponse; 

 var field2=document.getElementById("txt2");
 field2.onfocus=focusResponse;
 field2.onblur=blurResponse;

 field1.focus();
}
document.addEventListener( "DOMContentLoaded" , init , false ) ;

