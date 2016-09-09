<!-- example for PHP 5.0.0 final release -->

<html><head><title>Get Users</title></head>
<body>

<?php

#connect to MySQL
$conn=@mysql_connect("localhost", "mike", "bingo")
		 or die("Could not connect");

#select the specified database
$rs = @mysql_select_db("my_database", $conn) 
		or die("Could not select database");
 
#create the query 
$sql="select * from users";
 
#execute the query
$rs=mysql_query($sql,$conn) 
		or die("Could not execute query");

#write the users details in a table
$list = "<table border=\"1\" cellpadding=\"2\">";
$list.="<tr><th>First Name</th>";
$list.="<th>Last Name</th>";
$list.="<th>User Name</th>";
$list.="<th>Password</th></tr>";

while($row= mysql_fetch_array($rs) )
{
   $list .= "<tr>";
   $list .= "<td>".$row["first_name"]."</td>";
   $list .= "<td>".$row["last_name"]."</td>";
   $list .= "<td>".$row["user_name"]."</td>";
   $list .= "<td>".$row["password"]."</td>";
   $list .= "</tr>";
}
$list .= "</table>";
echo($list);

?>
</body></html>