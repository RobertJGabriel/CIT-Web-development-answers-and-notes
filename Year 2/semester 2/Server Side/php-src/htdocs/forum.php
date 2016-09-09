<?php # DISPLAY COMPLETE FORUM PAGE.

# Access session.
session_start() ;

# Redirect if not logged in.
if ( !isset( $_SESSION[ 'user_id' ] ) ) { require ( 'login_tools.php' ) ; load() ; }

# Set page title and display header section.
$page_title = 'Forum' ;
include ( 'includes/header.html' ) ;

# Open database connection.
require ( '..\connect_db.php' ) ;

# Display body section, retrieving from 'forum' database table.
$q = "SELECT * FROM forum" ;
$r = mysqli_query( $dbc, $q ) ;
if ( mysqli_num_rows( $r ) > 0 )
{
  echo '<table><tr><th>Posted By</th><th>Subject</th><th id="msg">Message</th></tr>';
  while ( $row = mysqli_fetch_array( $r, MYSQLI_ASSOC ))
  {
    echo '<tr><td>' . $row['first_name'] .' '. $row['last_name'] . '<br>'. $row['post_date'].'</td>
    <td>' . $row['subject'] . '</td><td>' . $row['message'] . '</td> </tr>';
  }
  echo '</table>' ;
}
else { echo '<p>There are currently no messages.</p>' ; }

# Create navigation links.
echo '<p><a href="post.php">Post Message</a> | <a href="shop.php">Shop</a> | <a href="home.php">Home</a> | <a href="goodbye.php">Logout</a></p>' ;

# Close database connection.
mysqli_close( $dbc ) ;
  
# Display footer section.
include ( 'includes/footer.html' ) ;

?>