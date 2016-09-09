<!-- example for PHP 5.0.0 final release -->

<html><head><title>Creating a table</title></head>
<body>
<?php

$self = $_SERVER['PHP_SELF'];
$fields = $_POST['fields'];
$db =     $_POST['db'];
$name =   $_POST['name'];
$table =  $_POST['table'];
$type =   $_POST['type'];
$size =   $_POST['size'];


if( !$fields and !$db )
{
  $form ="<form action=\"$self\" method=\"post\">";
  $form.="How many fields are needed in the new table?<br>";
  $form.="<input type=\"text\" name=\"fields\" size=\"5\">";
  $form.="<input type=\"submit\" value=\"Submit\">";
  echo($form);
}
else if( !$db )
{ 
  $form ="<form action=\"$self\" method=\"post\">";
  $form.="Database: &nbsp;&nbsp;&nbsp;&nbsp;<input type=\"text\" name=\"db\"><br>";
  $form.="Table Name: &nbsp;<input type=\"text\" name=\"table\" size=\"10\"><br> ";
  for ($i = 0 ; $i <$fields; $i++) 
  {
    $form.="Column Name:<input type=\"text\" name=\"name[$i]\" size=\"10\"> ";
    $form.="Type: <select name=\"type[$i]\">";
    $form.="<option value=\"char\">char</option>";	
    $form.="<option value=\"varchar\">varchar</option>";
    $form.="<option value=\"int\">int</option>";
    $form.="<option value=\"float\">float</option>";
    $form.="<option value=\"timestamp\">timestamp</option>";
    $form.="</select> ";
    $form.="Size:<input type=\"text\" name=\"size[$i]\" size=\"5\"><br>";
  }
  $form.=" <input type=\"submit\" value=\"Submit\"></form>";
  echo($form);
}
else
{
  $conn = @mysql_connect("localhost", "mike", "bingo")
	or die("Could not connect.");

  $rs = @mysql_select_db($db, $conn)
	or die("Could not select database.");
	
  $num_columns = count($name);

  $sql = "create table $table (";
  for ($i = 0; $i < $num_columns; $i++) 
  {
    $sql .= "$name[$i] $type[$i]";
    if(($type[$i] =="char") or ($type[$i] =="varchar"))
    {
      if($size[$i] !="" ){ $sql.= "($size[$i])"; }
    }
    if(($i+1) != $num_columns){ $sql.=","; }
  }
  $sql .= ")";

  echo("SQL COMMAND: $sql <hr>");

  $result = mysql_query($sql,$conn)
	or die("Could not execute SQL query");

  if ($result) {     
	echo("RESULT: table \"$table\" has been created");
  }
}

?>
</body></html>
