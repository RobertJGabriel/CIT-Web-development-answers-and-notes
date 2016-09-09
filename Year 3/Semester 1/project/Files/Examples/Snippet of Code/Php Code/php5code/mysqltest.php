<!-- example for PHP 5.0.0 final release -->

<html>

 <head> 
  <title>MySQL Connection Test</title> 
 </head>

 <body>
  <h2>
   <?php $connection = mysql_connect( "localhost", "root", "" )
	 or die( "Sorry - unable to connect to MySQL" );
	 echo( "Congratulations - you connected to MySQL" );
   ?>
  </h2>
 </body>

</html>