<html>
<head></head>

<body>

<table>
	<tr><td>Title</td><td>Author</td><td>Description</td></tr>
	<?php 

		foreach ($books as $title => $book)
		{
			echo '<tr><td><a href="index.php?book='.$book->title.'">'.$book->title.'</a></td><td>'.$book->author.'</td><td>'.$book->description.'</td></tr>';
		}

	?>
</table>

</body>
</html>