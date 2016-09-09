function validate()
{
  if( this.elements["Name"].value === "" )
  { 
    alert("Please enter your name"); return false; 
  }
  
  if( ( this.elements["Email"].value.indexOf("@") === -1 )
   || ( this.elements["Email"].value.indexOf(".") === -1 ) )
  { 
    alert("Please enter a valid email address"); return false; 
  }

}

function init()
{
  var panel=document.getElementById("panel");
  panel.innerHTML="Please enter your name and email address.";

  var form=document.getElementById("contact");
  form.onsubmit=validate;

}
onload=init;