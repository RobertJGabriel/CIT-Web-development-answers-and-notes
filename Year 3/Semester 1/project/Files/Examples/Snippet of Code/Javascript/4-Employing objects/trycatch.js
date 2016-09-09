function init()
{
	var panel = document.getElementById("panel");
	var day = 32;  // Delete, or comment-out this line, to see an automatic TypeError.

	try
  	{
		if ( day > 31 ) { throw new RangeError(" 'day' cannot exceed 31"); } 	// Manual RangeError.
		if( day < 1 )   { throw "GetReal"; 				}	// Manual Error
  	}
	catch(err)
  	{	
		panel.innerHTML = ( err === "GetReal") ?  ("Ooops! The 'day' variable has an invalid value of " + day ) : (err.name + " exception has occurred: " + err.message) ;
  	}
	finally
	{
		panel.innerHTML += "<br>The script has ignored that variable and is continuing..."; 
	}
}
window.onload=init;

/*

JavaScript implementation can automatically produce these exception types:
'Error'
'EvalError'
'RangeError'
'SyntaxError'
'TypeError'
'URIError'

*/




