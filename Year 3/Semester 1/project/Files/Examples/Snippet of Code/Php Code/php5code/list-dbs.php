<!-- example for PHP 5.0.0 final release -->

<?php

$conn = @mysql_connect( "localhost", "mike", "bingo" ) 
	or die( "Sorry - could not connect to MySQL" );



$rs= 	@mysql_list_dbs( $conn )
	or die( "Sorry - could not list databases" );

#create a list of all the databases
for( $row=0; $row < mysql_num_rows($rs); $row++ )
{
  $db_list .= mysql_tablename( $rs, $row ) . "<br>";  

} 


?>

<html>

 <head>
  <title>Listing databases</title>
 </head>

 <body>
  <h3>
   <?php echo( $db_list ); ?>  
  </h3>
 </body>

</html>