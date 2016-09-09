<?php # CONNECT TO MySQL DATABASE.

require( '../connect_db.php' ) ;

# Create a MySQL query.
$q = 'SHOW TABLES' ;

# Execute the query.
$r = mysqli_query( $dbc , $q ) ;

# Show results.
if( $r ) 
{
  echo '<h1>Tables on site_db database</h1> ' ;
  while ( $row = mysqli_fetch_array( $r , MYSQLI_NUM ) ) 
  {
    echo '<br>'. $row[0] ;
  }
  mysqli_free_result( $r ) ;
}
else
{
  echo '<p>' . mysqli_error( $dbc ) . '</p>'  ;
}

# Close the connection.
mysqli_close( $dbc ) ;
