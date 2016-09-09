# use the "my_database" database
USE my_database;

# create a table called "top_5_films"
CREATE TABLE top_5_films
(
  position	INT		PRIMARY KEY,
  title 	VARCHAR(25)	NOT NULL,	
  year		INT		NOT NULL
);

# insert 5 records into the "top_5" table
INSERT INTO top_5_films (position, title, year) 
	VALUES (1, "Citizen Kane", 1941);
INSERT INTO top_5_films (position, title, year) 
	VALUES (2, "Casablanca",1942);
INSERT INTO top_5_films (position, title, year) 
	VALUES (3, "The Godfather", 1972);
INSERT INTO top_5_films (position, title, year) 
	VALUES (4, "Gone With The Wind",1939);
INSERT INTO top_5_films (position, title, year) 
	VALUES (5, "Lawrence Of Arabia", 1962);

# show all data in "top_5_films" in descending position order
SELECT * FROM top_5_films ORDER BY position DESC;

# show all data in "top_5_films" in ascending year order
SELECT * FROM top_5_films ORDER BY year ASC;

# show all data in "top_5_films" in alphabetical order
SELECT * FROM top_5_films ORDER BY title ASC;

# delete this sample table
DROP TABLE top_5_films;