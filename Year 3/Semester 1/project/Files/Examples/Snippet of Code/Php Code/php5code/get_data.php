<!-- example for PHP 5.0.0 final release -->

<html>

 <head>
  <title>Get data</title>
 </head>

 <body>

<?php

#connect to MySQL
$conn=@mysql_connect( "localhost", "mike", "bingo" )
		 	or die( "Err:Conn" );

#select the specified database
$rs = @mysql_select_db( "my_database", $conn ) 
			or die( "Err:Db" );
#create the query 
$sql = "select id, first_name from my_table";

#execute the query
$rs = mysql_query( $sql, $conn );

#write the data
while( $row = mysql_fetch_array( $rs ) )
{
   echo( "ID: " . $row["id"] );
   echo( " - FIRST NAME: " . $row["first_name"] . "<br>" );
}

?>

 </body>

</html>