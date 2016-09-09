# Use the "my_database" database
USE my_database;

# create a table called "dogs" with 2 columns
CREATE TABLE IF NOT EXISTS dogs
(
  id INT,
  breed TEXT
);

# show that the table has been created
SHOW TABLES;

# confirm the "dogs" table format
EXPLAIN dogs;

# delete the "dogs" and "fruit" tables
DROP TABLE IF EXISTS dogs, fruit;

# show that the tables have been deleted
SHOW TABLES;



