<!DOCTYPE HTML>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>PHP Form Validation Handler</title>
</head>
<body>

<?php

# Empty check.
if ( !empty ( $_POST['quantity'] ) )
{
  $quantity = $_POST['quantity'] ; 
  
  # Format check.
  if ( !is_numeric( $quantity ) ) 
  { 
    $quantity = NULL ;
    echo 'Quantity must be numeric<br>' ; 
  }
  
}
else
{ 
  $quantity = NULL ;
  echo 'You must enter a quantity<br>' ;
 }

 # Empty check.
 if ( !empty ( $_POST['email'] ) )
{
  $email = $_POST['email'] ; 
  
  # Format check.
  $pattern = '/\b[\w.-]+@[\w.-]+\.[A-Za-z]{2,6}\b/' ;
  if ( !preg_match(  $pattern , $email ) ) 
  { 
    $email = NULL ;
    echo 'Email address is incorrect format' ;
  }
  
}
else
{ 
  $email = NULL ;
  echo 'You must enter an email address' ;
 }
 
 # Success response.
 if ( ( $quantity != NULL ) && ( $email != NULL ) )
 {
   echo "Email : $email<br>Quantity : $quantity " ;
 }
 

?>

</body>
</html>