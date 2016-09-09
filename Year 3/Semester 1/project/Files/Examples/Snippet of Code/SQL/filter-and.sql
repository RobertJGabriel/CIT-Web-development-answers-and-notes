# use the "my_database" database
USE my_database;

# create a table called "dining_sets"
CREATE TABLE IF NOT EXISTS dining_sets
(
  id		INT		AUTO_INCREMENT PRIMARY KEY,
  model		VARCHAR(25)	NOT NULL,
  color		VARCHAR(25)	NOT NULL,
  price		DECIMAL(6,2)	NOT NULL
);

# insert 5 records into the "dining_sets" table
INSERT INTO dining_sets (model, color, price) 
  VALUES ("Catalina", "Cherry", 349.99);

INSERT INTO dining_sets (model, color, price) 
  VALUES ("Bistro", "Silver", 99.99);

INSERT INTO dining_sets (model, color, price) 
  VALUES ("Michigan", "Silver", 179.99 );

INSERT INTO dining_sets (model, color, price) 
  VALUES ("Oregon", "Silver", 199.99);

INSERT INTO dining_sets (model, color, price) 
  VALUES ("Medina", "Black", 159.99);

# show all data in the "dining_sets" table
SELECT * FROM dining_sets;

# show all records where the color is "Silver"
# and the price is above 100.00
SELECT * FROM dining_sets 
WHERE color = "Silver" AND price > 100.00;

# delete this sample table
DROP TABLE IF EXISTS dining_sets;