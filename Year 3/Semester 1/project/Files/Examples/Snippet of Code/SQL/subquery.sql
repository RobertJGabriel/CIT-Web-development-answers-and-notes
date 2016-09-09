# use the "my_database" database
USE my_database;

# create a table called "customers"
CREATE TABLE IF NOT EXISTS customers
( acc_num INT PRIMARY KEY, name	CHAR(20) NOT NULL );

# insert 2 records into the "customers" table
INSERT INTO customers (acc_num, name) VALUES (123, "T.Smith");
INSERT INTO customers (acc_num, name) VALUES (124, "P.Jones");

# create a table called "orders"
CREATE TABLE IF NOT EXISTS orders
( ord_num INT PRIMARY KEY, acc_num INT NOT NULL );

# insert 2 records into the "orders" table
INSERT INTO orders (ord_num, acc_num) VALUES (3, 123);
INSERT INTO orders (ord_num, acc_num) VALUES (4, 124);

# display all data in the "customers" and "orders" tables
SELECT * FROM customers;
SELECT * FROM orders;

# retrieve the name of the customer placing order 4
SELECT ord_num, customers.acc_num, name
FROM customers, orders
WHERE customers.acc_num = orders.acc_num 
AND orders.ord_num = 4;

# delete these sample tables
DROP TABLE IF EXISTS customers;
DROP TABLE IF EXISTS orders;