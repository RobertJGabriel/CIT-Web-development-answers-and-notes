<?php  	header( "Cache-Control:no-cache" );

	function setnum()
	{
  	  global $num;
  	  srand( (double)microtime() * 1000000 );
  	  $num = rand( 1, 20 );
	}
?>

<!-- example for PHP 5.0.0 final release -->

<html>

 <head>
  <title>Number guess</title>
 </head>
 
 <body>

 <?php


 $num =   $_POST['num'];
 $guess = $_POST['guess'];
 $self =  $_SERVER['PHP_SELF'];
 
 if( $num == null )
 { 
   $msg =  "I have thought of a number between 1 and 20";
   $msg .= " <h3>guess what it is...</h3>";
 }

 if( $num != null and !is_numeric( $guess ) ) 
 { $msg = "Your guess was invalid<h3>Try Again!</h3>"; }
 else if( $guess == $num ) 
 { 
   if($num != null)
   {
     $msg = "CORRECT! - THE NUMBER WAS $num";
     $msg .= "<h3><a href= \"$self\">";
     $msg.="CLICK HERE TO TRY AGAIN???</a></h3>";
   }
   setnum(); 
 }
 else if( $guess > $num )
 { $msg = "You guessed $guess<h3>My number is lower!</h3>"; }
 else if( $guess < $num )
 { $msg = "You guessed $guess<h3>My number is higher!</h3>";}

 echo($msg);

?>

 <form action = "<?php $self ?>" method = "post">
 <input type = "hidden" name = "num" value = "<?php echo( $num ); ?>" >
 Guess:<input type = "text" name = "guess">
 <input type = "submit" value = "Submit">
 </form> 

 </body>

</html>