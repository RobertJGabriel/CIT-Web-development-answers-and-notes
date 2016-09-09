# use the "my_database" database
USE my_database;

# create a table named "towels" with 3 columns
CREATE TABLE towels
(
  code	VARCHAR(8)	NOT NULL PRIMARY KEY,
  name	VARCHAR(20)	NOT NULL,
  color VARCHAR(20)	DEFAULT "White"
);

# insert 3 records into the "towels" table
INSERT INTO towels ( code, name, color )
VALUES ( "821/7355", "Dolphin", "Blue" );

INSERT INTO towels ( color, code, name )
VALUES ( "Lilac", "830/1921", "Daisy" );

INSERT INTO towels ( code, name )
VALUES ( "830/2078", "Starburst" );

# show all "towels" data
SELECT * FROM towels;


