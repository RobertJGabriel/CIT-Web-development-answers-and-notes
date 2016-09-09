# use the "my_database" database
USE my_database;

# create a table called "multimeters"
CREATE TABLE IF NOT EXISTS multimeters
(
  id		INT		AUTO_INCREMENT PRIMARY KEY,
  model		CHAR(10)	NOT NULL,
  price		DECIMAL(4,2)	NOT NULL
);

# insert 3 records into the "multimeters" table
INSERT INTO multimeters (model, price) 
  VALUES ("Standard", 11.75);
INSERT INTO multimeters (model, price) 
  VALUES ("Super", 19.50);
INSERT INTO multimeters (model, price) 
  VALUES ("DeLuxe", 24.99);

# display all data in the "multimeters" table
SELECT * FROM multimeters;

# get the average price
SELECT AVG(price) AS avg_price
FROM multimeters;

# get the maximum price and minimum price
SELECT MAX(price) AS max_price, MIN(price) AS min_price
FROM multimeters;

# delete this sample table
DROP TABLE IF EXISTS multimeters;