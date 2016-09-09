<!-- example for PHP 5.0.0 final release -->

<html>
 
 <head> 
  <title>Create guestbook table</title> 
 </head>
    
 <body>

 <?php

  #connect to MySQL
  $conn = @mysql_connect("localhost", "mike","bingo") 
  	or die("Could not connect to database");
  
  #select the database
  $rs = @mysql_select_db("my_database", $conn) 
	or die("Could not select database");
 
  #create the SQL query
  $query = "id int(4) auto_increment, name varchar(50),";
  $query.= "email varchar(50), comments text, ";
  $query.= "time timestamp(14), primary key(id)";
  $sql = "create table guestbook($query)";
  
  #execute the query
  $rs = @mysql_query ($sql);

  #confirm the result
  if( $rs )
  {
    $msg = "<h3>Created guestbook table</h3>";
  }
  else
  {
    $msg = "<h3>Could not create guestbook table";
    $msg.= "<br>Does it already exist?</h3>";
  }
  echo($msg);
 ?>

 </body>

</html>
