<?php # DISPLAY COMPLETE LOGGED OUT PAGE.

# Access session.
session_start() ;

# Redirect if not logged in.
if ( !isset( $_SESSION[ 'user_id' ] ) ) { require ( 'login_tools.php' ) ; load() ; }

# Set page title and display header section.
$page_title = 'Goodbye' ;
include ( 'includes/header.html' ) ;

# Clear existing variables.
$_SESSION = array() ;
  
# Destroy the session.
session_destroy() ;

# Display body section.
echo '<h1>Goodbye!</h1><p>You are now logged out.</p><p><a href="login.php">Login</a></p>' ;

# Display footer section.
include ( 'includes/footer.html' ) ;

?>