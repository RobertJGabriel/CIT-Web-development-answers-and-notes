<!-- example for PHP 5.0.0 final release -->

<html>
<head>
<title>Sign the guestbook</title>
</head>
<body>
<?php

$self = $_SERVER['PHP_SELF'];
$name = $_POST['name'];
$email = $_POST['email'];
$comments = $_POST['comments'];
$submit = $_POST['submit'];

#the html form
$form = "<form action=\"$self\" method=\"post\">";
$form.= "Name: <input type=\"text\" name=\"name\" ";
$form.= "size=\"50\" value=\"$name\"> <br>";
$form.= "Email: <input type=\"text\" name=\"email\" ";
$form.= "size=\"50\" value=\"$email\"> <br>";
$form.= "Comments:<br>";
$form.= "<textarea name=\"comments\" cols=\"45\" ";
$form.= "rows=\"4\">$comments</textarea> <br>";
$form.= "<input type=\"submit\" name=\"submit\" ";
$form.= "value=\"Sign\"> </form>";

#on first opening display the form
if( !$submit)
{ $msg = $form; }
else 

#redisplay a message and the form if incomplete
if( !$name or !$email or !$comments)
{
  $msg = "<b>Please complete all fields</b><br><br>";
  $msg.= $form;
}
else

#add the form data to the guestbook database table
{
  #connect to MySQL
  $conn = mysql_connect("localhost", "mike","bingo") 
	or die("Count not connect to database");
  
  #select the database
  $rs = mysql_select_db("my_database",$conn) 
	or die ("Could not select database");  

  #create the SQL query
  if($name and $comments)
  {
     $sql = "insert into guestbook (name,email, comments) 
			values (\"$name\",\"$email\",\"$comments\")"; 
     $rs = mysql_query($sql,$conn) 
	or die ("Could not execute SQL query");
  }

  #confirm the entry and display a link to view guestbook
  if($rs)
  {
    $msg = "<h3>Thank you - your entry has been saved.</h3>";
    $msg.= "<h3><a href = \"guestbook-view.php\">";
    $msg.= "View My Guestbook</a></h3>";
  }
}
echo($msg);
?>

</body>
</html>
