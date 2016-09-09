# use the "my_database" database
USE my_database;

# create a table called "watches"
CREATE TABLE watches
(
  id		INT		AUTO_INCREMENT PRIMARY KEY,
  model		VARCHAR(20)	NOT NULL,
  style		VARCHAR(6)	DEFAULT "Gents",
  price		DECIMAL(4,2)	NOT NULL  
);

# insert 5 records into the "watches" table
INSERT INTO watches (model, price) VALUES ("Panama",69.99);
INSERT INTO watches (model, style, price) VALUES ("Club", "Ladies", 59.99);
INSERT INTO watches (model, price) VALUES ("Avante",49.99);
INSERT INTO watches (model, style, price) VALUES ("Panama", "Ladies", 69.99);
INSERT INTO watches (model, price) VALUES ("Club",59.99);

# show all data in the "watches" table
SELECT * FROM watches;

# show data in "watches" grouped by style
SELECT model, style, price FROM watches ORDER BY 2;

# show data in "watches" in ascending price order
SELECT model, style, price FROM watches ORDER BY 3;

# delete this sample table
DROP TABLE watches;