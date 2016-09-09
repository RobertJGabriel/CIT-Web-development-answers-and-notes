# List all databases
SHOW DATABASES;

# Use the "my_database" database
USE my_database;

# create a table called "fruit" with 3 columns
CREATE TABLE IF NOT EXISTS fruit
(
  id INT,
  name TEXT,
  color TEXT
);

# show that the table has been created
SHOW TABLES;

# confirm the "fruit" table format
EXPLAIN fruit;

