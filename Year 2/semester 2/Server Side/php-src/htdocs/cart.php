<?php # DISPLAY SHOPPING CART PAGE.

# Access session.
session_start() ;

# Redirect if not logged in.
if ( !isset( $_SESSION[ 'user_id' ] ) ) { require ( 'login_tools.php' ) ; load() ; }

# Set page title and display header section.
$page_title = 'Cart' ;
include ( 'includes/header.html' ) ;

# Check if form has been submitted for update.
if ( $_SERVER['REQUEST_METHOD'] == 'POST' )
{
  # Update changed quantity field values.
  foreach ( $_POST['qty'] as $item_id => $item_qty )
  {
    # Ensure values are integers.
    $id = (int) $item_id;
    $qty = (int) $item_qty;

    # Change quantity or delete if zero.
    if ( $qty == 0 ) { unset ($_SESSION['cart'][$id]); } 
    elseif ( $qty > 0 ) { $_SESSION['cart'][$id]['quantity'] = $qty; }
  }
}

# Initialize grand total variable.
$total = 0; 

# Display the cart if not empty.
if (!empty($_SESSION['cart']))
{
  # Connect to the database.
  require ('../connect_db.php');
  
  # Retrieve all items in the cart from the 'shop' database table.
  $q = "SELECT * FROM shop WHERE item_id IN (";
  foreach ($_SESSION['cart'] as $id => $value) { $q .= $id . ','; }
  $q = substr( $q, 0, -1 ) . ') ORDER BY item_id ASC';
  $r = mysqli_query ($dbc, $q);

  # Display body section with a form and a table.
  echo '<form action="cart.php" method="post"><table><tr><th colspan="5">Items in your cart</th></tr><tr>';
  while ($row = mysqli_fetch_array ($r, MYSQLI_ASSOC))
  {
    # Calculate sub-totals and grand total.
    $subtotal = $_SESSION['cart'][$row['item_id']]['quantity'] * $_SESSION['cart'][$row['item_id']]['price'];
    $total += $subtotal;

    # Display the row/s:
    echo "<tr> <td>{$row['item_name']}</td> <td>{$row['item_desc']}</td>
    <td><input type=\"text\" size=\"3\" name=\"qty[{$row['item_id']}]\" value=\"{$_SESSION['cart'][$row['item_id']]['quantity']}\"></td>
    <td>@ {$row['item_price']} = </td> <td>".number_format ($subtotal, 2)."</td></tr>";
  }
  
  # Close the database connection.
  mysqli_close($dbc); 
  
  # Display the total.
  echo ' <tr><td colspan="5" style="text-align:right">Total = '.number_format($total,2).'</td></tr></table><input type="submit" name="submit" value="Update My Cart"></form>';
}
else
# Or display a message.
{ echo '<p>Your cart is currently empty.</p>' ; }

# Create navigation links.
echo '<p><a href="shop.php">Shop</a> | <a href="checkout.php?total='.$total.'">Checkout</a> | <a href="forum.php">Forum</a> | <a href="home.php">Home</a> | <a href="goodbye.php">Logout</a></p>' ;

# Display footer section.
include ( 'includes/footer.html' ) ;

?>