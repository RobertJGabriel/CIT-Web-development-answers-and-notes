# use the "my_database" database
USE my_database;

# show all "bath_towels" data
SELECT * FROM bath_towels;

# delete two specific rows
DELETE FROM bath_towels WHERE code = "821/9735";
DELETE FROM bath_towels WHERE code = "821/7355";

# show all "bath_towels" data
SELECT * FROM bath_towels;

# delete all remaining rows in the "bath_towels" table
DELETE FROM bath_towels;

# show all "bath_towels" data
SELECT * FROM bath_towels;

# delete the "towels" and "bath_towels" tables
DROP TABLE towels;
DROP TABLE bath_towels;

# show tables are removed
SHOW TABLES;