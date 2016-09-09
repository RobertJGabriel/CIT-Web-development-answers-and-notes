<!-- example for PHP 5.0.0 final release -->

<html><head>
<title>Add record to my_database/my_table</title></head>
<body>

<?php


$self =  $_SERVER['PHP_SELF'];
$id =    $_POST['id'];
$fname = $_POST['fname'];
$lname = $_POST['lname'];


?>

<form action="<?php echo( $self ); ?>" method="post">
ID: <input type="text" name="id" size="3">
First Name: <input type="text" name="fname" size="8">
Last Name: <input type="text" name="lname" size="8"><br>
<input type="submit" value="Submit">
</form>

<?php

if( $id and $fname and $lname)
{
  $conn=@mysql_connect( "localhost", "mike", "bingo" ) or die( "Err:Conn" );

 #select the specified database
 $rs = @mysql_select_db( "my_database", $conn) or die( "Err:Db" );

 #create the query
 $sql = "insert into my_table ( id, first_name, last_name ) values ( $id, \"$fname\", \"$lname\" )";

 #execute query
 $rs = mysql_query( $sql, $conn );

 if( $rs )
 {
   echo( "Record added:$id $fname $lname" );
 }
}
 
?>

</body></html> 

