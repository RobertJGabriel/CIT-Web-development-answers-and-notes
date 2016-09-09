function accelerate()
{
  return "<br>Vroom!" ;
}

function Car( make , model , color )
{
 this.make = make ;
 this.model = model ;
 this.color = color ;
 this.accelerate = accelerate ;
}

function init()
{
 var panel = document.getElementById( "panel" ) ;
 var myCar = new Car( "Dodge" , " Viper" , "Red " ) ;
 panel.innerHTML = myCar.color + myCar.make + myCar.model ;
 panel.innerHTML += myCar.accelerate() ;
}
document.addEventListener( "DOMContentLoaded" , init , false ) ;