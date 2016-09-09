<?php 
    echo '<div id="catch">';
    echo '<form action="Php/contollor.php?action=insert"  method="post">';
    echo '<h3>Add a Movie<h3>';
    echo '<input type="text"placeholder="Title "  name="filmtitle"required >';
    echo '<input type="text" placeholder="description" name="description"required >';

	
	 echo '<input type="number" placeholder="Hours" name="filmDuration" min="1" max="60" required>';

     echo '<input type="number" placeholder="Mins" name="filmDurationMin" min="0" max="60" required>';
	
	
	
    echo '<input type="date" placeholder="Relelase date" name="releaseDate"required >';
    echo '<input type="text" placeholder="Director" name="director"required ><br/>';
    echo '<select placeholder="Cert" name="cert">';
    echo '<option value="PG">PG</option>';
    echo '<option value="12">12</option>';
    echo '<option value="15">15</option>';
    echo '<option value="18">18</option>';
    echo '</select> ';
    echo '<input class="button"  type="submit">';
    echo '</form>';
	echo '</div>';
?>