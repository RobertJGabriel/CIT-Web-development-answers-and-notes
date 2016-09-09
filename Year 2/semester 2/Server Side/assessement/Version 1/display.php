
<?php
include 'congfig.php';
 $con=mysqli_connect($host,$username,$password,$database);
 // Check connection
 if (mysqli_connect_errno())
   {
   echo "Failed to connect to MySQL: " . mysqli_connect_error();
   }

 $result = mysqli_query($con,"SELECT * FROM titles");

 while($row = mysqli_fetch_array($result))
   {
   echo "<div class='display' id='test '" . "onclick='" . "". "'>";
      echo '<ul>';
	  echo "<li><h2>". $row['filmtitle']."</h2></li>";
	   echo "<li class='dir'> By<br/>". $row['director']."</li>";
	  	     echo "<li>". $row['description']."</li>";
	 echo "<li>". $row['cert']."</li>";
	  echo "<li>". $row['releaseDate']."</li>";
	  	  echo "<li>". $row['filmDuration']."</li>";
		  
	
   echo "<br>";
     echo '</ul>';
      echo "</div>";
   }

 mysqli_close($con);
 ?>
