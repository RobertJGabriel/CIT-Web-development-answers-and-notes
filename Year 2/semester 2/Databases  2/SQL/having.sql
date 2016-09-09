# use the "my_database" database
USE my_database;

# create a table called "sofabeds"
CREATE TABLE IF NOT EXISTS sofabeds
(
  id		INT	AUTO_INCREMENT	PRIMARY KEY,
  name		CHAR(10)	NOT NULL,
  color		CHAR(10)	NOT NULL,
  price		DECIMAL(6,2)	NOT NULL
);

# insert 5 records into the "sofabeds" table
INSERT INTO sofabeds (name, color, price) 
  VALUES ("Milan", "Blue", 199.99);
INSERT INTO sofabeds (name, color, price) 
  VALUES ("Firenze", "Red", 144.99);
INSERT INTO sofabeds (name, color, price) 
  VALUES ("Vivaldi", "Terracotta", 199.99);
INSERT INTO sofabeds (name, color, price) 
  VALUES ("Vienna", "Blue", 164.99);
INSERT INTO sofabeds (name, color, price) 
  VALUES ("Roma", "Red", 249.99);

# display all data in the "sofabeds" table
SELECT * FROM sofabeds;

# get the number of items for each color
# where the price exceeds 150.00 and
# when there is more than 1 item for that color
SELECT color, COUNT(*) AS  num_items_over_150
FROM sofabeds WHERE price >= 150.00
GROUP BY color HAVING COUNT(*) > 1;

# delete this sample table
DROP TABLE IF EXISTS sofabeds;