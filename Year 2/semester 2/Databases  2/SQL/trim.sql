# use the "my_database" database
USE my_database;

# create a table called "padded"
CREATE TABLE IF NOT EXISTS padded
(
  id	INT	AUTO_INCREMENT PRIMARY KEY,
  str1 CHAR(10), str2 CHAR(10), str3 CHAR(10)
);

# insert 2 records into the "padded" table
INSERT INTO padded (str1, str2, str3) 
  VALUES (" MySQL    ", " Data     ", " Bases    ");
INSERT INTO padded (str1, str2, str3) 
  VALUES (" are      ", " great    ", " fun !    ");

# show all data in the "padded" table
SELECT * FROM padded;

# retrieve 2 trimmed concatenated calculated fields
SELECT CONCAT( TRIM(str1), RTRIM(str2), TRIM(str3) ) 
FROM padded WHERE id = 1;
SELECT CONCAT( TRIM(str1), RTRIM(str2), RTRIM(str3) ) 
FROM padded WHERE id = 2;


# delete this sample table
DROP TABLE padded;