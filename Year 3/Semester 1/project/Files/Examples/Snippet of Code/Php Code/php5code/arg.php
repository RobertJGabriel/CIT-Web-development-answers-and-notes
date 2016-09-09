<!-- example for PHP 5.0.0 final release -->

<html>
 <head>
  <title>PHP Arguments</title>
 </head>
 <body>

 <?php 
	function go($arg)
	{ 
	  echo("<b><u><i>$arg</i></u></b>"); 
	} 
 ?>

 <p>This is the regular text style of this page.</p>

 <?php go("This text has added style"); ?>

 <p>This is the regular text style of this page.<p>

 <?php go("PHP makes this so easy"); ?>

 </body>
</html>