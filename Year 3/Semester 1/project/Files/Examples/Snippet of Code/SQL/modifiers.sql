# Use the "my_database" database
USE my_database;

# create a table called "products" with 5 columns
CREATE TABLE IF NOT EXISTS products
(
  id 	INT 		UNIQUE AUTO_INCREMENT,	
  code 	INT 		NOT NULL,
  name	VARCHAR(25) 	NOT NULL,
  qty	INT 		DEFAULT 1,
  price	DECIMAL(6,2) 	NOT NULL
);

# confirm the "products" table format
EXPLAIN products;

# delete this sample table
DROP TABLE products;

