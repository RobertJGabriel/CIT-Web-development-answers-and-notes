# get the current full date object, current date nd time
SELECT NOW(), CURDATE(), CURTIME();

# use the my_database database
USE my_database;

# create a table called "labor_day"
CREATE TABLE IF NOT EXISTS labor_day
(
  id	INT	AUTO_INCREMENT PRIMARY KEY,
  date	DATETIME NOT NULL
);

# insert 1 record into the "labor_day" table
INSERT INTO labor_day (date) VALUES ("2014-09-05 12:45:30");

# get the name of the day
SELECT DAYNAME(date) FROM labor_day;

# get the day, month and year date components
SELECT DAYOFMONTH(date), MONTHNAME(date),YEAR(date) 
FROM labor_day;

# get the hour, minute and second time components
SELECT HOUR(date), MINUTE(date), SECOND(date) 
FROM labor_day;

# delete this sample table
DROP TABLE IF EXISTS labor_day;