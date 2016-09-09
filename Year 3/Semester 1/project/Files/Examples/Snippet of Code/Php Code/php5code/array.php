<!-- example for PHP 5.0.0 final release -->

<?php
	$arr = array();

	$arr[0] = "First ";
	$arr[1] = " PHP ";
	$arr[2] = "array";

	echo( $arr[0] . $arr[1] . $arr[2] );
?>

<hr>

<?php
	$mo = array( "Jan ", "Feb ", "Mar " );
	$dy = array( "21 ", "22 ", "23 " );
	$yr = array( "2005", "2006", "2007" );
		
	echo( $mo[1] . $dy[0] . $yr[1] );
?>