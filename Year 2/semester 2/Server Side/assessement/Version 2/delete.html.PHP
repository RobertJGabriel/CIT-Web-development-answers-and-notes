

<div id="catch">
<form action="Php_Code/delete.php" method="post">

<?php
echo "<h3> Delete Movie<h3>";
include 'Config/config.php';
 $con=mysqli_connect($host,$username,$password,$database);
 // Check connection
 if (mysqli_connect_errno())
   {
   echo "Failed to connect to MySQL: " . mysqli_connect_error();
   }

 $result = mysqli_query($con,"SELECT * FROM titles");
   echo "<select placeholder='Cert' name='id'>";
 while($row = mysqli_fetch_array($result))
   {



   echo "<option value='". $row['ID'] ."'> " . $row['filmtitle'] ."</option> ";

    
   }

 mysqli_close($con);
   echo " </select>";
 ?> 


<input class="button" type="submit">
</form>
</div>