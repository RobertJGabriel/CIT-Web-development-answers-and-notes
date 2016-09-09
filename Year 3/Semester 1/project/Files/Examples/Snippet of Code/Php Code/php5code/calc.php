<!-- example for PHP 5.0.0 final release -->

<html> 
 <head> 
  <title>Calculation Result</title> 
 </head>
 <body>

 <?php 

	$val1 = $_POST['val1'];
	$val2 = $_POST['val2'];
	$calc = $_POST['calc'];

	if( is_numeric( $val1 ) && is_numeric( $val2 ) )
	{
  		if( $calc != null )
  		{
    			switch( $calc )
    			{
      				case "add" : $result = $val1 + $val2; break;
      				case "sub" : $result = $val1 - $val2; break;
      				case "mul" : $result = $val1 * $val2; break;
      				case "div" : $result = $val1 / $val2; break;
    			}			
  			echo( "Calculation result: $result" );
  		} 
	}
	else
	{ 
		echo( "Invalid entry - please retry" ); 
	}
 ?>

 </body>
</html>