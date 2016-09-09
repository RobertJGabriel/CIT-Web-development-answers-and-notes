# Use the "my_database" database
USE my_database;

# create a table called "user_log" with 3 columns
CREATE TABLE IF NOT EXISTS user_log
(
  id INT,
  date TIMESTAMP,
  first_name VARCHAR(20),
  last_name  VARCHAR(20)
);

# confirm the "user_log" table format
EXPLAIN user_log;

# delete this sample table
DROP TABLE user_log;

