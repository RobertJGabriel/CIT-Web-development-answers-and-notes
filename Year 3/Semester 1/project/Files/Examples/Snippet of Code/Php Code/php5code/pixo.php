<!-- example for PHP 5.0.0 final release -->

<html>

 <head>
  <title>Welcome Page</title>
 </head>
 
 <body>
 <center>

 <?php

	$browser = $_SERVER['HTTP_USER_AGENT'];

	if( preg_match( "/Pixo/i", "$browser" ) )
	{
	  $img = "<img src=\"small-tux.gif\" ";
	  $img.= "width=\"64\" height=\"75\" ";
	  $img.= " alt=\"tux\" >";
	}
	else
	{
	  $img = "<img src=\"large-tux.gif\" ";
	  $img.= " width=\"320\" height=\"375\" ";
	  $img.= " alt=\"tux\" >";
	}
	echo( $img );
 ?>
	  
 </center>
 </body>

</html>