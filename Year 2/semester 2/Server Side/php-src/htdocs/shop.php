<?php # DISPLAY COMPLETE PRODUCTS PAGE.

# Access session.
session_start() ;

# Redirect if not logged in.
if ( !isset( $_SESSION[ 'user_id' ] ) ) { require ( 'login_tools.php' ) ; load() ; }

# Set page title and display header section.
$page_title = 'Shop' ;
include ( 'includes/header.html' ) ;

# Open database connection.
require ( '..\connect_db.php' ) ;

# Retrieve items from 'shop' database table.
$q = "SELECT * FROM shop" ;
$r = mysqli_query( $dbc, $q ) ;
if ( mysqli_num_rows( $r ) > 0 )
{
  # Display body section.
  echo '<table><tr>';
  while ( $row = mysqli_fetch_array( $r, MYSQLI_ASSOC ))
  {
    echo '<td><strong>' . $row['item_name'] .'</strong><br><span style="font-size:smaller">'. $row['item_desc'] . '</span><br><img src='. $row['item_img'].'><br>$' . $row['item_price'] . '<br><a href="added.php?id='.$row['item_id'].'">Add To Cart</a></td>';
  }
  echo '</tr></table>';
  
  # Close database connection.
  mysqli_close( $dbc ) ; 
}
# Or display message.
else { echo '<p>There are currently no items in this shop.</p>' ; }

# Create navigation links.
echo '<p><a href="cart.php">View Cart</a> | <a href="forum.php">Forum</a> | <a href="home.php">Home</a> | <a href="goodbye.php">Logout</a></p>' ;

# Display footer section.
include ( 'includes/footer.html' ) ;

?>