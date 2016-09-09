<?php # PROCESS MESSAGE POST.

# Access session.
session_start();

# Make load function available.
require ( 'login_tools.php' ) ;

# Redirect if not logged in.
if ( !isset( $_SESSION[ 'user_id' ] ) ) { load() ; }

# Set page title and display header section.
$page_title = 'Post Error' ;
include ( 'includes/header.html' ) ;

# Check form submitted.
if ($_SERVER['REQUEST_METHOD'] == 'POST')
{
  # Check Subject and Message Input.
  if ( empty($_POST['subject'] ) ) { echo '<p>Please enter a subject for this post.</p>'; }
  if ( empty($_POST['message'] ) ) { echo '<p>Please enter a message for this post.</p>'; }

  # On success add post to forum database.
  if( !empty($_POST['subject']) &&  !empty($_POST['message']) )
  {
    # Open database connection.
    require ( '../connect_db.php' ) ;
  
    # Execute inserting into 'forum' database table.
    $q = "INSERT INTO forum(first_name,last_name,subject,message,post_date) 
          VALUES ('{$_SESSION[first_name]}','{$_SESSION[last_name]}','{$_POST[subject]}','{$_POST[message]}',NOW() )";
    $r = mysqli_query ( $dbc, $q ) ;

    # Report error on failure.
    if (mysqli_affected_rows($dbc) != 1) { echo '<p>Error</p>'.mysqli_error($dbc); } else { load('forum.php'); }
    
    # Close database connection.
    mysqli_close( $dbc ) ; 
    }
 } 
 
# Create a hyperlink back to the forum page.
echo '<p><a href="forum.php">Forum</a>' ;
 
# Display footer section.
include ( 'includes/footer.html' ) ;

?>