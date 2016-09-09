<?php # CONNECT TO MySQL DATABASE.

require( '../connect_db.php' ) ;

# Create a MySQL query.
$q = 'SHOW TABLES' ;

# Execute the query.
$r = mysqli_query( $dbc , $q ) ;

# Show results.
if( $r ) 
{
  echo '<h1>Result Set Returned OK</h1>' ;
}
else
{
  echo '<p>' . mysqli_error( $dbc ) . '</p>' ;
}

# Close the connection.
mysqli_close( $dbc ) ;
