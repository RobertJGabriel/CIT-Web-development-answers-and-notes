<?php

include 'Config/config.php';

$con=mysqli_connect($host,$username,$password,$database);



// Check connection
if (!$con) { 
    echo 'Server error. Please try again sometime. CON'; 
    }
else {
    //Selects all from the table
    $result = mysqli_query($con,"SELECT * FROM titles");

    while($row = mysqli_fetch_array($result))
        {
        
       
    $_SESSION['varName'] =  $row['filmtitle'];

        
        echo "<div class='display' " . "onclick='insert(" . $row['ID']. ")'>";
        echo'<form action="Php_code/update.php"id="test"  method="post">';
        echo '<h1>'.$row['filmtitle'].'</h1>';
        echo'<input type="text"  placeholder="Director" id="title" value="' . $row['filmtitle']. '"name="filmtitle" hidden>';
        echo "by<br/>";
        echo'<input type="text"  placeholder="Director" value="' . $row['director']. '"name="director">';
        echo '<div class="dir"> </div>';
        echo'<textarea type="text" placeholder="description" value=""name="description">'. $row['description']. '</textarea>';
        echo'<input type="time" placeholder="Flim time" value="' .$row['filmDuration'] . '" name="filmDuration">';
        echo'<input type="date" placeholder="Relelase date" value="' .$row['releaseDate'] . '"name="releaseDate">';
        echo'<img width="80px" height="80px" src="../images/' .$row['cert'] . '.png">';
        echo'<input class="submit" value="Update" type="submit">';
        echo'</form>';
        echo "</div>";
        
        
        
        
        
        
        }
     mysqli_close($con);
}







   
?>

