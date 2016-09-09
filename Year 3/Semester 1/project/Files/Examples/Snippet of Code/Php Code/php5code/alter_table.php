<!-- example for PHP 5.0.0 final release -->

<?php

#connect to MySQL
$conn = @mysql_connect( "localhost", "mike", "bingo" )or die( "Err:Conn" );

#select the specified database
$rs = @mysql_select_db( "my_database", $conn ) or die( "Err:Db" );
 
#create then execute the 1st query
#reduce field to 3 number length
$qry = "alter table my_table modify id int(3)";
$rs = mysql_query( $qry, $conn ) or die( "Err:Query 1" );

#create then execute the 2nd query
#extend field to 32 character length
$qry = "alter table my_table modify last_name char(32)";
$rs = mysql_query( $qry, $conn ) or die( "Err:Query 2" );

?>
