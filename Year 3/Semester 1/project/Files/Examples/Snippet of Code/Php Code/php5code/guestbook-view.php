<!-- example for PHP 5.0.0 final release -->

<html>
<head>
<title>View guestbook</title>
</head>
<body>
<h3>Latest 3 guestbook entries...</h3>

<?php

#connect to MySQL
$rs = @mysql_connect( "localhost", "mike", "bingo" )
               	or die( "Could not connect to MySQL" );

#select the database
$rs = @mysql_select_db( "my_database" ) 
		or die( "Could not select database" );

#create the SQL query
$sql = "select * from guestbook order by time desc limit 3";


#execute the query
$rs = @mysql_query( $sql ) 
		or die( "Could not execute SQL query" );

#loop through all records
while ( $row = mysql_fetch_array( $rs ) ) 
{

?>

<table border="1" width="375">
<tr>
<td><b>Name:</b> <?php echo $row["name"]; ?></td>

<td><b>Email:</b> <a href="mailto:<?php echo $row["email"]; ?>">
           <?php echo $row["email"]; ?></a> </td><tr>
<tr><td colspan="2">
  <?php

  $datetime = $row["time"];
  $year = substr( $datetime, 0, 4 );
  $mon  = substr( $datetime, 4, 2 );
  $day  = substr( $datetime, 6, 2 );
  $hour = substr( $datetime, 8, 2 );
  $min  = substr( $datetime, 10, 2 );
  $sec  = substr( $datetime, 12, 2 );
  $orgdate = date("l F dS, Y h:i A",mktime( $hour, $min, $sec, $mon, $day, $year ) );
  ?>
Date: <?php echo $orgdate; ?></td></tr>

<tr><td colspan="2"><b>Comments:</b> <?php echo $row["comments"]; ?></td></tr>
</table>
<br>

<?php } ?>

</body>
</html>

