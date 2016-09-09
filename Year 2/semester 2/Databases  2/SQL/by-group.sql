# use the "my_database" database
USE my_database;

# create a table called "cabinets"
CREATE TABLE IF NOT EXISTS cabinets
(
  id		INT	AUTO_INCREMENT	PRIMARY KEY,
  wood		CHAR(10)	NOT NULL,
  item		CHAR(20)	NOT NULL
);

# insert 5 records into the "cabinets" table
INSERT INTO cabinets (wood, item) 
  VALUES ("Pine", "Bookcase");
INSERT INTO cabinets (wood, item) 
  VALUES ("Beech", "Bookcase");
INSERT INTO cabinets (wood, item) 
  VALUES ("Oak", "Bookcase");
INSERT INTO cabinets (wood, item) 
  VALUES ("Pine", "Display Case");
INSERT INTO cabinets (wood, item) 
  VALUES ("Oak", "Display Case");

# display all data in the "cabinets" table
SELECT * FROM cabinets;

# get the number of items for each type of wood
SELECT wood, COUNT(*) AS  num_items
FROM cabinets 
GROUP BY wood;

# get the number of woods for each type of item
SELECT item, COUNT(*) AS  num_woods
FROM cabinets 
GROUP BY item;

# delete this sample table
DROP TABLE IF EXISTS cabinets;