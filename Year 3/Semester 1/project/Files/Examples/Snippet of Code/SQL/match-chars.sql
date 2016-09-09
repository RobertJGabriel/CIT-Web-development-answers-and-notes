# use the "my_database" database
USE my_database;

# create a table called "glass_sets"
CREATE TABLE IF NOT EXISTS glass_sets
(
  id	INT		AUTO_INCREMENT PRIMARY KEY,
  name	VARCHAR(25)	NOT NULL, 
  price DECIMAL(6,2)	NOT NULL
);

# insert 5 records into the "glass_sets" table
INSERT INTO glass_sets (name, price) 
  VALUES ("Monaco", 6.99);
INSERT INTO glass_sets (name, price) 
  VALUES ("Cavendish", 4.99);
INSERT INTO glass_sets (name, price) 
  VALUES ("Mosaic", 6.99);
INSERT INTO glass_sets (name, price) 
  VALUES ("Blue Reef", 8.99);
INSERT INTO glass_sets (name, price) 
  VALUES ("Silver Swirl", 14.99);

# show all data in the "glass_sets" table
SELECT * FROM glass_sets;

# show records where the name matches a search pattern 
SELECT * FROM glass_sets WHERE name LIKE "mo_a__";

# this table not deleted so it can be used by match-regexp.sql