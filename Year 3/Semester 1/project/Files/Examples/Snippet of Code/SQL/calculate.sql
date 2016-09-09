# use the "my_database" database
USE my_database;

# create a table called "wines"
CREATE TABLE IF NOT EXISTS wines
(
  id		INT	AUTO_INCREMENT	PRIMARY KEY,
  type		CHAR(10)	NOT NULL,
  price		DECIMAL(6,2)	NOT NULL,
  quantity	INT		DEFAULT 0
);

# insert 3 records into the "wines" table
INSERT INTO wines (type, price, quantity) 
  VALUES ("Red", 10.00, 12);

INSERT INTO wines (type, price, quantity) 
  VALUES ("White", 9.00, 12);

INSERT INTO wines (type, price, quantity) 
  VALUES ("Rose", 8.00, 6);

# generate calculated fields
SELECT	type,
	price AS bottle,
	quantity AS qty,
	price * quantity AS subtotal,
	(price * quantity) * (6 / 100) AS tax,
	(price * quantity) + 
	  (price * quantity) * (6 / 100) AS total
FROM wines ;

# delete this sample table
DROP TABLE wines;