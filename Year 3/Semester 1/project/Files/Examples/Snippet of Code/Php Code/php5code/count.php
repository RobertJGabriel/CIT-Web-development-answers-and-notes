<?php
   	session_start();	#start a session

	# initially set the value to 1 or increment on next visits
    	if ( !isset( $_SESSION['count'] ) ) 
	$_SESSION['count'] = 1; else $_SESSION['count']++;
?>

<!-- example for PHP 5.0.0 final release -->

<html>
<head>
<title>Count Visits</title>
</head>

<body>
<h2>You have visited this page <?php echo( $_SESSION['count'] ); ?> times in this session</h2>

</body>
</html>