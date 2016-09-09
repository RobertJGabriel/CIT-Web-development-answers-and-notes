<?php
    echo '<div id="catch">';
    echo '<form action="Php/contollor.php?action=delete" method="post">';
    echo "<h3> Delete Movie<h3>";
    include_once'Php/config.php';
    $con=mysqli_connect($host,$username,$password,$database);
    
    // Check connection
    if (mysqli_connect_errno())   {
        echo "Failed to connect to MySQL: " . mysqli_connect_error();
    }

    $result = mysqli_query($con,"SELECT * FROM titles");
    echo "<select placeholder='Cert' name='id'>";
    while($row = mysqli_fetch_array($result))   {
        echo "<option value='". $row['ID'] ."'> " . $row['filmtitle'] ."</option> ";
    }

    mysqli_close($con);
    echo " </select><br/>";
    echo '<input class="button" type="submit">';
    echo '</form>';
    echo ' </div>';
    ?>