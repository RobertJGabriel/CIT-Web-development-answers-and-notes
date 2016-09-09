# use the "my_database" database
USE my_database;

# create a table called "products" with 3 records
CREATE TABLE IF NOT EXISTS products
( id INT PRIMARY KEY, name CHAR(20) NOT NULL );
INSERT INTO products (id, name) VALUES (111, "Socket");
INSERT INTO products (id, name) VALUES (222, "Widget");
INSERT INTO products (id, name) VALUES (333, "Sprocket");

# create a table called "orders" with 3 records
CREATE TABLE IF NOT EXISTS orders
( 
  num 		INT 		PRIMARY KEY, 
  product 	INT,
  qty		INT,
  client	CHAR(20) 
);
INSERT INTO orders (num, product, qty, client) 
  VALUES (3570, 222, 1000, "Archie");
INSERT INTO orders (num, client) 
  VALUES (5223, "Bernie");
INSERT INTO orders (num, product, qty, client) 
  VALUES (4364, 111, 800, "Connie");

# display all products - including those with no orders
SELECT 	p.name		AS Product,
	o.num 		AS OrderNumber, 
	o.qty 		AS Quantity,
	o.client 	AS Client
FROM products AS p LEFT OUTER JOIN orders AS o
ON p.id = o.product ORDER BY p.name;

# display all orders - including those with no products
SELECT 	o.num 		AS OrderNumber, 
	p.name		AS Product,
	o.qty 		AS Quantity,
	o.client 	AS Client
FROM products AS p RIGHT OUTER JOIN orders AS o
ON p.id = o.product ORDER BY o.num;

# delete these sample tables
DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS orders;