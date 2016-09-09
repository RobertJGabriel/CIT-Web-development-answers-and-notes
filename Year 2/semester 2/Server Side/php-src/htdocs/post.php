<?php # DISPLAY POST MESSAGE FORM.

# Access session.
session_start() ;

# Redirect if not logged in.
if ( !isset( $_SESSION[ 'user_id' ] ) ) { require ( 'login_tools.php' ) ; load() ; }

# Set page title and display header section.
$page_title = 'Post Message' ;
include ( 'includes/header.html' ) ;

# Display form.
echo '<form action="post_action.php" method="post" accept-charset="utf-8">
<p>Subject:<br><input name="subject" type="text" size="64" maxlength="100"></p>
<p>Message:<br><textarea name="message" rows="5" cols="50"></textarea></p>
<p><input name="submit" type="submit" value="Submit"></p></form>';

# Create navigation links.
echo '<p><a href="forum.php">Forum</a> | <a href="shop.php">Shop</a> | <a href="home.php">Home</a> | <a href="goodbye.php">Logout</a></p>' ;

# Display footer section.
include ( 'includes/footer.html' ) ;

?>