# use the "my_database" database
USE my_database;

# create a table called "star_orders"
CREATE TABLE IF NOT EXISTS star_orders
(
  id		INT		AUTO_INCREMENT	PRIMARY KEY,
  order_num	INT		NOT NULL,
  cost_each	DECIMAL(6,2)	NOT NULL,
  quantity	INT		DEFAULT 1
);

# insert 5 records into the "star_orders" table
INSERT INTO star_orders (order_num, cost_each, quantity) 
  VALUES (10030, 217.50, 2);
INSERT INTO star_orders (order_num, cost_each) 
  VALUES (10031, 72.50);
INSERT INTO star_orders (order_num, cost_each) 
  VALUES (10032, 299.75);
INSERT INTO star_orders (order_num, cost_each) 
  VALUES (10031, 29.25);
INSERT INTO star_orders (order_num, cost_each) 
  VALUES (10031, 148.25);

# display all data in the "star_orders" table
SELECT * FROM star_orders;

# get total cost for order number 10031
SELECT SUM(cost_each) AS total_for_order_10031 
FROM star_orders 
WHERE order_num = 10031;

# get total cost for order number 10030
SELECT SUM(cost_each * quantity) AS total_for_order_10030 
FROM star_orders 
WHERE order_num = 10030;

# delete this sample table
DROP TABLE IF EXISTS star_orders;