# use the "my_database" database
USE my_database;

# create a table called "items" with 3 records
CREATE TABLE IF NOT EXISTS items
(
  id		INT		PRIMARY KEY,
  vendor	INT		NOT NULL,
  name		CHAR(20)	NOT NULL,
  price		DECIMAL(6,2)	NOT NULL
);
INSERT INTO items (id, vendor, name, price) 
  VALUES (601, 2, "Elephants", 147.50);
INSERT INTO items (id, vendor, name, price) 
  VALUES (602, 2, "Reindeers",  123.00);
INSERT INTO items (id, vendor, name, price) 
  VALUES (603, 1, "Alligators", 185.00);

# create a table called "vendors" with 2 records
CREATE TABLE IF NOT EXISTS vendors
(
  id		INT	PRIMARY KEY,
  name		CHAR(20)	NOT NULL
);
INSERT INTO vendors (id, name) VALUES (1, "Alpha Inc");
INSERT INTO vendors (id, name) VALUES (2, "Zeta Inc");

# create a table called "orders" with 3 records
CREATE TABLE IF NOT EXISTS orders
(
  num	INT	PRIMARY KEY,
  item	INT	NOT NULL,
  qty	INT	NOT NULL
);
INSERT INTO orders (num, item, qty) VALUES (2805, 603, 10);
INSERT INTO orders (num, item, qty) VALUES (2806, 603, 5);
INSERT INTO orders (num, item, qty) VALUES (2807, 601, 10);

# display order number, quantity, item name, vendor 
# and total order value of order number 2805
SELECT 	orders.num 			AS Number, 
	orders.qty 			AS Qty,
	items.name			AS Toy, 
	vendors.name			AS Vendor,
	items.price * orders.qty	AS Total
FROM 	items, vendors, orders
WHERE 	vendors.id = items.vendor
AND	items.id = orders.item
AND 	orders.num = 2805;

# delete these sample tables
DROP TABLE IF EXISTS items;
DROP TABLE IF EXISTS vendors;
DROP TABLE IF EXISTS orders;