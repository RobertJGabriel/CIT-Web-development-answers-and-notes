<!-- example for PHP 5.0.0 final release -->

<?php 

$conn = @mysql_connect( "localhost", "mike", "bingo" )
      	or die( "Sorry - could not connect to MySQL" );

$rs1= mysql_list_dbs( $conn );

for( $row=0; $row < mysql_num_rows( $rs1 ); $row++ )
{ 
  $this_db = mysql_tablename( $rs1, $row );
  $list .= "<b>".$this_db."</b><br>"; 
  if( $this_db != "mysql" )
  {
    $rs2 = mysql_list_tables( $this_db );
 
    for( $num=0; $num < mysql_num_rows( $rs2) ; $num++ )
    { 
      $list .= " - " . mysql_tablename( $rs2, $num ) . "<br>"; 
    }
  }
}
?>

<html>

 <head>
  <title>Listing Tables</title>
 </head>

 <body> 
  <?php  echo( $list ); ?> 
 </body>

</html>