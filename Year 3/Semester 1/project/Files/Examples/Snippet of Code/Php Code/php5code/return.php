<!-- example for PHP 5.0.0 final release -->

<?php
	function multiply( $a = 1, $b = 1, $c = 1, $d = 1, $e = 1)
	{
	  $total = $a * ($b * ($c * ($d * $e)));
	  return $total;
	}
?>

<html>
 <head>
  <title>Return Statement</title>
 </head>
 <body>

  Each year has 365¼ days<br>
  Each day has 24 hours<br>
  Each hour has 60 minutes<br>

  Each year has <?php echo( multiply(365.25,24,60)) ?> minutes

 </body>
</html>