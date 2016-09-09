# use the "my_database" database
USE my_database;

# create a table called "shredders"
CREATE TABLE IF NOT EXISTS shredders
(
  model VARCHAR(8)	PRIMARY KEY, 
  type VARCHAR(25) 	DEFAULT "strip cut", 
  price DECIMAL(6,2)
);

# insert 4 records into the "shredders" table
INSERT INTO shredders (model, price) VALUES ("PS60", 64.99);
INSERT INTO shredders (model, price) VALUES ("PS70", 99.99);
INSERT INTO shredders (model, type, price) 
  VALUES ("PS400", "cross cut", 64.99);
INSERT INTO shredders (model, price) VALUES ("PS500", 29.95);

# show all data in the "shredders" table
SELECT * FROM shredders;

# show records where the model is a "cross cut" type
SELECT * FROM shredders WHERE type LIKE "%cross%";

# delete this sample table
DROP TABLE IF EXISTS shredders;