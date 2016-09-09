

<form action="Php_Code/insert.php"  method="post">
<h3> Add  a Movie<h3>

<input type="text"placeholder="Title " value= "<?php  ?>" name="filmtitle">
<input type="text" placeholder="description" name="description">
<input type="time" placeholder="Flim time" name="filmDuration">
<input type="date" placeholder="Relelase date" name="releaseDate">

<input type="text" placeholder="Director" name="director">


<select placeholder="Cert" name="cert">
   <option value="PG">PG</option>
   <option value="12">12</option>
   <option value="15">15</option>
   <option value="18">18</option>
 </select> 
<input class="button"  type="submit">
</form>
