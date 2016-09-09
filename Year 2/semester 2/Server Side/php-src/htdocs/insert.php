<?php # CONNECT TO MySQL DATABASE.

require( '../connect_db.php' ) ;

# Function to create and execute a MySQL query.
function show_records( $dbc )
{
  $q = 'SELECT * FROM prints' ;
  $r = mysqli_query( $dbc , $q ) ;
   if ( $r )
  {
      echo '<h1>Records in prints table</h1> ' ;
      while ( $row = mysqli_fetch_array( $r , MYSQLI_ASSOC ) ) 
     {
       echo '<br>' . $row['id'] . ' | ' . $row[ 'name' ] .  ' @ ' . $row[ 'price' ] ;
      }
  } else { echo '<p>' . mysqli_error( $dbc ) . '</p>'  ; }
}
# Call the function.
show_records($dbc) ;
  
# Create and execute another MySQL query.
$q = 'INSERT INTO prints ( name , price ) VALUES ( "One Center" , 32.99 ) , ( "Yellow Red Blue" , 36.99 ) ' ;
$r = mysqli_query( $dbc , $q ) ;

# Call the function again.
if( $r) { show_records($dbc) ; } else { echo '<p>' . mysqli_error( $dbc ) . '</p>'  ; }

# Close the connection.
mysqli_close( $dbc ) ;
