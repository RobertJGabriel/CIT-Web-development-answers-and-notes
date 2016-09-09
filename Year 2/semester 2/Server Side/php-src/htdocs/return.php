<!DOCTYPE HTML>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>PHP Returns</title>
</head>
<body>
<?php

date_default_timezone_set('UTC');
$user = ' Mike' ;

function display_date()
{
  return date('l, jS F');
}

function welcome($user)
{
  $hour = date('H') ;
  $greeting = ( $hour < 12 ) ? '<br>Good Morning' : '<br>Good Day' ;
  $greeting .= $user;
  return $greeting;
}

echo display_date();
echo welcome($user);


?>
</body>
</html>