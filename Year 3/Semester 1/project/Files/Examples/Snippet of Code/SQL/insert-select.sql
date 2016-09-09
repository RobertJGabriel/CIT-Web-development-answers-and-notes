# use the "my_database" database
USE my_database;

# create a table named "bath_towels" with 3 columns
CREATE TABLE bath_towels
(
  code	VARCHAR(8)	NOT NULL PRIMARY KEY,
  name	VARCHAR(20)	NOT NULL,
  color VARCHAR(20)	DEFAULT "White"
);

# insert 2 records into the "bath_towels" table
INSERT INTO bath_towels ( code, name, color )
VALUES ( "821/9735", "Harvest", "Beige" );

INSERT INTO bath_towels ( code, name, color )
VALUES ( "821/9628", "Wine", "Maroon" );

# show all tables in the "my_database" database
SHOW TABLES;

# show all "bath_towels" data
SELECT * FROM bath_towels;

# show all "towels" data
SELECT * FROM towels;

INSERT INTO bath_towels ( code, name, color )
SELECT * FROM towels;

# show all "bath_towels" data
SELECT * FROM bath_towels;


