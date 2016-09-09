<?php 
	$location = $_POST['location'];
	$self = $_SERVER['PHP_SELF'];

	if( $location != null )
	{
	  header( "Location:$location") ;
	  exit();
	}
?>

<!-- example for PHP 5.0.0 final release -->

<html>

 <head>
  <title>Redirect</title>
 </head>

 <body>
  Choose a site to visit:
  <form action = "<?php $self ?>" method = "post">
   <select name = "location">
   <option value = "http://www.ineasysteps.com">In Easy Steps</option>
   <option value = "http://www.amazon.com">Amazon</option>
   <option value = "http://w3c.org">World Wide Web Consortium</option>
   <option value = "http://www.reuters.com">Reuters News</option>
   <option value = "http://www.ebay.com">Ebay</option>
   </select>
   <input type = "submit" name = "submit" value = "Go">
  </form>

 </body>

</html>


	