# use the "my_database" database
USE my_database;

# create a table called "customers"
CREATE TABLE IF NOT EXISTS customers
( acc_num INT PRIMARY KEY, name	CHAR(20) NOT NULL );

# insert 3 records into the "customers" table
INSERT INTO customers (acc_num, name) VALUES (123, "T.Smith");
INSERT INTO customers (acc_num, name) VALUES (124, "P.Jones");
INSERT INTO customers (acc_num, name) VALUES (125, "H.Nicks");

# create a table called "orders"
CREATE TABLE IF NOT EXISTS orders
( ord_num INT PRIMARY KEY, acc_num INT NOT NULL );

# insert 5 records into the "orders" table
INSERT INTO orders (ord_num, acc_num) VALUES (1, 123);
INSERT INTO orders (ord_num, acc_num) VALUES (2, 124);
INSERT INTO orders (ord_num, acc_num) VALUES (3, 125);
INSERT INTO orders (ord_num, acc_num) VALUES (4, 125);
INSERT INTO orders (ord_num, acc_num) VALUES (5, 123);

# display all data in the "customers" and "orders" tables
SELECT * FROM customers; SELECT * FROM orders;

# get the number of orders per customer
SELECT name, customers.acc_num, COUNT(*) AS number_of_orders
FROM  customers, orders
WHERE customers.acc_num = orders.acc_num
GROUP BY name
ORDER BY customers.acc_num;

# delete these sample tables
DROP TABLE IF EXISTS customers;
DROP TABLE IF EXISTS orders;