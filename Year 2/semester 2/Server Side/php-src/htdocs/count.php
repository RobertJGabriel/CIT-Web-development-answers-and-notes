<?php # CONNECT TO MySQL DATABASE.

require( '../connect_db.php' ) ;

# Function to create and execute a MySQL query.
function show_records( $dbc )
{
  $q = 'SELECT * FROM towels' ;
  $r = mysqli_query( $dbc , $q ) ;
  
  $num = mysqli_num_rows( $r ) ;
  
   if ( $num > 0 )
  {
      echo '<h1>Records in towels table</h1> ' ;
      while ( $row = mysqli_fetch_array( $r , MYSQLI_ASSOC ) ) 
     {
       echo '<br>' . $row['name'] . ' | ' . $row[ 'color' ] .  ' @ ' . $row[ 'price' ] ;
      }
	  echo "<br>$num Records" ;
  } else { echo '<p>' . mysqli_error( $dbc ) . '</p>'  ; }
}
# Call the function.
show_records($dbc) ;
  
# Create and execute another MySQL query.
$q = 'INSERT INTO towels ( name , color , price ) VALUES ( "Sunset" , "Orange" , 9.99 ) ' ;
$r = mysqli_query( $dbc , $q ) ;

# Call the function again.
if( $r) { show_records($dbc) ; } else { echo '<p>' . mysqli_error( $dbc ) . '</p>'  ; }

# Close the connection.
mysqli_close( $dbc ) ;
