<!-- example for PHP 5.0.0 final release -->

<html>

<head>
<title>Using timestamp</title>
</head>

<body>
<?php

  $conn = mysql_connect( "localhost", "mike", "bingo" ); 

  $rs = mysql_select_db( "my_database", $conn );

  $sql = "select * from guestbook where id=1";

  $rs = mysql_query( $sql, $conn );
 
  #split the timestamp into organized date format 
  while ( $row = mysql_fetch_array( $rs ) ) 
  {
    $datetime = $row["time"];
    $year = substr( $datetime, 0, 4 );
    $mon  = substr( $datetime, 4, 2 );
    $day  = substr( $datetime, 6, 2 );
    $hour = substr( $datetime, 8, 2 );
    $min  = substr( $datetime, 10, 2 );
    $sec  = substr( $datetime, 12, 2 );
    $orgdate = date("l F dS, Y h:i A", mktime( $hour, $min, $sec, $mon, $day, $year ) );
    echo( "Time of entry: " . $orgdate );
  }

?>

</body>
</html>
