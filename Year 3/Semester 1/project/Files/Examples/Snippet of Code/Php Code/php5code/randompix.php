<!-- example for PHP 5.0.0 final release -->

<html>
 <head>
  <title>Random Images</title>
 </head>
 <body bgcolor = "#000000" >

 <?php
	srand( microtime() * 1000000 );
	$num = rand( 1, 4 );

	switch( $num )
	{
	  case 1 : $car = "alfa.jpg";    $url="alfa.php";      break;
	  case 2 : $car = "ferrari.jpg"; $url="ferrari.php";   break;
	  case 3 : $car = "jaguar.jpg";  $url = "jaguar.php";  break;
	  case 4 : $car = "porsche.jpg"; $url = "porsche.php"; break;
	}
	$banner = "<a href=\"$url\"> ";
	$banner.= "<img src=\"$car\"  ";
	$banner.= "width=\"380\" height=\"110\" border=\"0\" >";
	$banner.="</a>";
	echo( $banner );
 ?>

 </body>
</html>