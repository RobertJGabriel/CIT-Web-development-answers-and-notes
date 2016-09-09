USE my_database;	# Use the "my_database" database

# create a table called "dishes" with 3 columns
CREATE TABLE IF NOT EXISTS dishes
(
  id 		INT		NOT NULL,	
  pattern	VARCHAR(25)	NOT NULL,
  price		DECIMAL(6,2)
);

EXPLAIN dishes;		# confirm the "dishes" table format

# Update the "dishes" table
ALTER TABLE dishes 
  ADD PRIMARY KEY(id),
  ADD COLUMN code INT UNIQUE NOT NULL,
  CHANGE pattern dish_pattern VARCHAR(25) NOT NULL,
  DROP COLUMN price;

EXPLAIN dishes;		# confirm the "dishes" table format
DROP TABLE dishes;	# delete this sample table