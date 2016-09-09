# Use the "my_database" database
USE my_database;

# create a table called "cups" with 2 columns
CREATE TABLE IF NOT EXISTS cups
(
  id 	INT 	AUTO_INCREMENT PRIMARY KEY,	
  cup_pattern	VARCHAR(25)
);

# create a table called "saucers" with 2 columns
CREATE TABLE IF NOT EXISTS saucers
(
  id 	INT 	AUTO_INCREMENT,	
  scr_pattern	VARCHAR(25),
  PRIMARY KEY(id)
);

# confirm the "cups" and "saucers" table format
EXPLAIN cups;
EXPLAIN saucers;

# delete these sample tables
DROP TABLE cups, saucers;

