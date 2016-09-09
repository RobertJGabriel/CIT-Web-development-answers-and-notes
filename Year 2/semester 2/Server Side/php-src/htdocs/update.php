<?php # CONNECT TO MySQL DATABASE.

require( '../connect_db.php' ) ;

# Function to create and execute a MySQL query.
function show_records( $dbc )
{
  $q = 'SELECT * FROM watches' ;
  $r = mysqli_query( $dbc , $q ) ;
  $num = mysqli_num_rows( $r ) ;
   if ( $num > 0 )
  {
      echo '<h1>Records in watches table</h1> ' ;
      while ( $row = mysqli_fetch_array( $r , MYSQLI_ASSOC ) ) 
     {
       echo '<br>' . $row['model'] . ' | ' . $row[ 'style' ] .  ' @ ' . $row[ 'price' ] ;
      }
  } else { echo '<p>' . mysqli_error( $dbc ) . '</p>'  ; }
}
# Call the function.
show_records($dbc) ;
  
# Create and execute another MySQL query.
$q = 'UPDATE watches SET style = "Gents" WHERE style = "Ladies"  ' ;
$r = mysqli_query( $dbc , $q ) ;

# Call the function again if 2 records updated.
if(  mysqli_affected_rows( $dbc ) == 2 ) 
{ 
  echo '<hr>' . mysqli_affected_rows( $dbc ) . ' Records Updated...' ; 
  show_records($dbc) ;
 } 

# Close the connection.
mysqli_close( $dbc ) ;
