# use the "my_database" database
USE my_database;

# create a table called "employees"
CREATE TABLE employees
(
  id		INT		AUTO_INCREMENT PRIMARY KEY,
  first_name	VARCHAR(20)	NOT NULL,
  last_name	VARCHAR(20)	NOT NULL
);

# insert 7 records into the "employees" table
INSERT INTO employees (first_name, last_name) 
	VALUES ("Arthur", "Smith");
INSERT INTO employees (first_name, last_name) 
	VALUES ("Peter", "Jones");
INSERT INTO employees (first_name, last_name) 
	VALUES ("Ann", "Smith");
INSERT INTO employees (first_name, last_name) 
	VALUES ("Sandra", "Williams");
INSERT INTO employees (first_name, last_name) 
	VALUES ("Andrew", "Smith");
INSERT INTO employees (first_name, last_name) 
	VALUES ("Paul", "Jones");
INSERT INTO employees (first_name, last_name) 
	VALUES ("Sally", "Williams");
# show all data in the "employees" table
SELECT * FROM employees;

# show both names sorted alphabetically
SELECT first_name, last_name FROM employees 
ORDER BY last_name, first_name;

# delete this sample table
DROP TABLE employees;