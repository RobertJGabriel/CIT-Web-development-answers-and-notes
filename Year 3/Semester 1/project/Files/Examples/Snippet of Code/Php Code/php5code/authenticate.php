<!-- example for PHP 5.0.0 final release -->

<?php

$username = $_POST['username'];
$password = $_POST['password'];
$self =     $_SERVER['PHP_SELF'];
$referer =  $_SERVER['HTTP_REFERER'];

# if either form field is empty return to the log-in page
if( ( !$username ) or ( !$password ) )
{ header( "Location:$referer" ); exit(); }

#connect to MySQL
$conn=@mysql_connect( "localhost", "mike", "bingo" )
		 or die( "Could not connect" );

#select the specified database
$rs = @mysql_select_db( "my_database", $conn ) 
		or die( "Could not select database" );
 
#create the query 
$sql = "select * from users where user_name=\"$username\" and password = password( \"$password\" )";
 
#execute the query
$rs = mysql_query( $sql, $conn ) 
		or die( "Could not execute query" );

#get number of rows that match username and password
$num = mysql_numrows( $rs );

#if there is a match the log-in is authenticated
if( $num != 0 )
{ 
  $msg = "<h3>Welcome $username - your log-in succeeded!</h3>";
}
else
{
  header( "Location:$referer" ); exit(); 
}
?>

<html>

 <head>
  <title>Log-In Authenticated</title>
  </head>

  <body>
   <?php echo( $msg ); ?>
  </body>

</html>
