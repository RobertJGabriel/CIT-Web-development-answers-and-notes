<?php 
    $con=mysqli_connect($host,$username,$password,$database);
    // Check connection
    if (!$con) {
        echo 'Server error. Please try again sometime. CON';
    } else {
        //Selects all from the table
        $result = mysqli_query($con,"SELECT * FROM titles");
        while($row = mysqli_fetch_array($result))        {
            $_SESSION['varName'] =  $row['filmtitle'];
            echo "<div class='display'>";
            echo'<form action="Php/contollor.php?action=update"id="test"  method="post">';
            echo'<input type="text"  placeholder="Director" id="ID" value="' . $row['ID']. '"name="ID" hidden>';
            echo'<h1><textarea type="text"  placeholder="Movie Title" id="title" name="filmtitle" >'. $row['filmtitle']. '</textarea></h1>';
            echo "by<br/>";
            echo'<input type="text"  placeholder="Director" value="' . $row['director']. '"name="director">';
            echo '<div class="dir"> </div>';
            echo'<textarea type="text" placeholder="description" value=""name="description">'. $row['description']. '</textarea>';
            echo'<h4>' .'Film Run time : ' . $row['filmDuration']. ' Mins</h3>';
            echo'<h4>' .'Release : ' .$row['releaseDate'] . '</h3>';
            echo'<div class="badge22"><img width="50px" height="50px" src="../images/' .$row['cert'] . '.png"></div>';
echo'<input  value="Update" type="submit">';           

          
            echo'</form>'; echo "</div>";
        }
        mysqli_close($con);
    }
?>