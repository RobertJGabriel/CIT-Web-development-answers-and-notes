# use the "my_database" database
USE my_database;

# create a table called "rings"
CREATE TABLE IF NOT EXISTS rings
(
  id		INT		AUTO_INCREMENT PRIMARY KEY,
  stone		CHAR(10)	NOT NULL,
  price		DECIMAL(4,2)	NOT NULL
);

# insert 5 records into the "rings" table
INSERT INTO rings (stone, price) VALUES ("Ruby", 40.00);
INSERT INTO rings (stone, price) VALUES ("Emerald", 40.00);
INSERT INTO rings (stone, price) VALUES ("Diamond", 60.00);
INSERT INTO rings (stone, price) VALUES ("Diamond", 50.00);
INSERT INTO rings (stone, price) VALUES ("Garnet", 40.00);

# display all data in the "rings" table
SELECT * FROM rings;

# get the total number of rows
SELECT COUNT(price) AS num_prices
FROM rings;

# get the number of unique rows
SELECT COUNT(DISTINCT price) AS num_distinct_prices
FROM rings;

# get all the unique stone values
SELECT DISTINCT stone AS unique_stone_names FROM rings;

# delete this sample table
DROP TABLE IF EXISTS rings;