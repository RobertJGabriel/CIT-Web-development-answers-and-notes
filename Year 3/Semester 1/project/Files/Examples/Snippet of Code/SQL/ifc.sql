# use the "my_database" database
USE my_database;

# create a table called "bookshelf"
CREATE TABLE IF NOT EXISTS bookshelf
(
  reader	CHAR(50)	NOT NULL,
  book		CHAR(50)	NOT NULL
);

# insert 1 record into the "bookshelf" table
INSERT INTO bookshelf (reader, book) 
  VALUES ("new_to_SQL", "SQL in easy steps by Mike McGrath");

 
SELECT book 
AS "The Ideal Introduction to SQL"
FROM bookshelf 
WHERE reader = "new_to_SQL";

#DROP TABLE IF EXISTS bookshelf;