<!-- example for PHP 5.0.0 final release -->

<html>
 <head>
  <title>If-else Statement</title>
 </head>
 <body>

 <?php
	$num = 2; 
	$bool=false;
	
	if($num==1 and $bool==true) echo("Test 1 success");
	else
	if($num==2 and $bool==true) echo("Test 2 success");
	else
	if($num==2 and $bool==false) echo("Test 3 success");
	else
	if($num==3 and $bool==false) echo("Test 4 success");

 ?>

 </body>
</html>