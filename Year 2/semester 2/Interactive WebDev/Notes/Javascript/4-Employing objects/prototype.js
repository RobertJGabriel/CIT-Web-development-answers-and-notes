function Car(make,model,color)
{
 this.make = make ;
 this.model = model ;
 this.color = color ;
}

function init()
{
 var panel=document.getElementById("panel");
 var myCar = new Car( "Ford", "Focus", "Blue" );

 Car.prototype.doors = 4;

 for( property in myCar ) 
 { 
    if( myCar[property] !== "" )
    {
	panel.innerHTML +=  property + ": " + myCar[property] + "<br>"; 
    }
 }
 panel.innerHTML += "<hr>"; 

 myCar.make = "Dodge";
 myCar.model= "Challenger";
 myCar.color= "Orange";
 myCar.doors=2;

 for( property in myCar ) 
 {    
    if( myCar[property] !== "" )
    {
    panel.innerHTML += ( property + ": " + myCar[property] + "<br>");
    }
 }

}
window.onload=init;

