<!-- example for PHP 5.0.0 final release -->

<html><head><title>Adding a User</title></head>
<body>
<?php

$self = $_SERVER['PHP_SELF'];
$firstname = $_POST['firstname'];
$lastname = $_POST['lastname'];
$username = $_POST['username'];
$password = $_POST['password'];

if( (!$firstname) or (!$lastname) or (!$username) or (!$password) )
{
  $form ="Please enter all new user details...";
  $form.="<form action=\"$self\"";
  $form.=" method=\"post\">First Name: ";
  $form.="<input type=\"text\" name=\"firstname\"";
  $form.=" value=\"$firstname\"><br>Last Name: ";
  $form.="<input type=\"text\" name=\"lastname\"";
  $form.=" value=\"$lastname\"><br>User Name: ";
  $form.="<input type=\"text\" name=\"username\"";
  $form.=" value=\"$username\"><br>Password: &nbsp; ";
  $form.="<input type=\"text\" name=\"password\"";
  $form.=" value=\"$password\"><br>";
  $form.="<input type=\"submit\" value=\"Submit\">";
  $form.="</form>";
  echo($form);
}
else
{
  #connect to MySQL
  $conn = @mysql_connect("localhost","mike","bingo")
		        or die("Could not connect to MySQL");
  #select a database
  $db = @mysql_select_db("my_database",$conn)
		        or die("Could not select database");
  #create the SQL query
  $sql = "insert into users (first_name,last_name,user_name,password)
  values (\"$firstname\",\"$lastname\",\"$username\",password(\"$password\") )";
  #execute the query
  $result = @mysql_query($sql,$conn)
		        or die("Could not execute query");
  if($result)
  { echo("New user $username added"); }
}
?>
</body></html>
