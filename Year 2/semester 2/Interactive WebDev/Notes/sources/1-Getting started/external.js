function init()
{
  document.getElementById( "panel" ).innerHTML = "Hello... from an External JavaScript File!" ;
  window.alert( "Document Loaded!" ) ;
}
window.onload = init ;