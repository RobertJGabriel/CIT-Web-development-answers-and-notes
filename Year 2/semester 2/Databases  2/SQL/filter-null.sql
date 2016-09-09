# use the "my_database" database
USE my_database;

# create a table called "steam_irons"
CREATE TABLE IF NOT EXISTS steam_irons
(
  id		INT		AUTO_INCREMENT PRIMARY KEY,
  make		VARCHAR(25)	NOT NULL,
  model		VARCHAR(25)	NOT NULL,
  color		VARCHAR(25)
);

# insert 5 records into the "steam_irons" table
INSERT INTO steam_irons (make, model, color) 
  VALUES ("Philips", "GC3020", "Lilac");
INSERT INTO steam_irons (make, model) 
  VALUES ("Morphy Richards", "40608");
INSERT INTO steam_irons (make, model) 
  VALUES ("Tefal", "1819 Avantis");
INSERT INTO steam_irons (make, model) 
  VALUES ("Rowenta", "DM529");
INSERT INTO steam_irons (make, model, color) 
  VALUES ("Bosch", "TDA8360", "Blue");

# show the table format
EXPLAIN steam_irons;

# show all data in the "steam_irons" table
SELECT * FROM steam_irons;

# show all records where there is no specified color
SELECT * FROM steam_irons WHERE color IS NULL;

# delete this sample table
DROP TABLE IF EXISTS steam_irons;