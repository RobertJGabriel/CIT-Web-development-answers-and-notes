function poll()
{
  var i , isOk , summary = "" , form = document.getElementById( "pizza" ) ;
  for ( i = 0 ; i < form.Topping.length ; i++ )
  {
    if ( form.Topping[ i ].checked ) { summary += form.Topping[ i ].value + " " ; }
  }
  isOk = confirm( "Submit these choices?\n" + summary ) ;
  if ( isOk )
  { 
    form.submit() ; 
  }
}

function init()
{
  document.getElementById( "pizza" ).Topping[ 0 ].checked = true ;
  document.getElementById( "btn" ).onclick = poll ;
}
document.addEventListener( "DOMContentLoaded" , init , false ) ;