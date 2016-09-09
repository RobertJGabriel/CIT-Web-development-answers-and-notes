<html>
<head>
<title>
</title>
<link rel="stylesheet" type="text/css" href="mystyle.css">
</head>
<body>
<div id="header">
<h1>Delete A Movie</h1>
</div>
<div id="main">

<form action="insert.php" method="post">
<select placeholder="Cert" name="cert">
   <option value="PG">PG</option>
   <option value="12">12</option>
   <option value="15">15</option>
   <option value="18">18</option>
 </select> 

<input type="text"placeholder="Title " name="filmtitle">
<input type="date" placeholder="Relelase date" name="releaseDate">
<input type="time" placeholder="Flim time" name="filmDuration">
<input type="text" placeholder="Director" name="director">
<input type="text" placeholder="description" name="description">
<input class="button" type="submit">
</form>

</div>
</body>
</html>