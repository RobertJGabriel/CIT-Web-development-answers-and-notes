var panel, field, saved;

function update()
{
   panel.innerHTML= saved+" changed to " + field.value+"<br>";
   saved = field.value;
}

function wipe()
{
 panel.innerHTML ="<br>";
}

function init()
{
  panel=document.getElementById("panel");
  field=document.getElementById("txt");
  saved=field.value;

  var form=document.getElementById("lang");

  field.onchange=update;
  form.onreset=wipe;
  wipe();
}
onload=init;

