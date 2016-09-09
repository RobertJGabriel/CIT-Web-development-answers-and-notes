# use the "my_database" database
USE my_database;

# create a table called "backpacks"
CREATE TABLE IF NOT EXISTS backpacks
(
  id INT AUTO_INCREMENT PRIMARY KEY,
  make VARCHAR(8), model VARCHAR(25), price DECIMAL(6,2)
);

# insert 4 records into the "backpacks" table
INSERT INTO backpacks (make, model, price) 
  VALUES ("Adidas", "NYC Uptown", 17.99);
INSERT INTO backpacks (make, model, price) 
  VALUES ("Nike", "Arrow", 11.99);
INSERT INTO backpacks (make, model, price) 
  VALUES ("Nike", "Sevilla", 13.99);
INSERT INTO backpacks (make, model, price) 
  VALUES ("Reebok", "Streetsport", 11.99);

# show all data in the "backpacks" table
SELECT * FROM backpacks;

# show records where make is "Nike" or "Reebok"
# and price is 11.99 - without explicit evaluation order
SELECT * FROM backpacks 
WHERE make = "Nike" OR make = "Reebok" AND price = 11.99;

# show records where make is "Nike" or "Reebok"
# and price is 11.99 - with explicit evaluation order
SELECT * FROM backpacks 
WHERE ( make = "Nike" OR make = "Reebok" ) 
AND price = 11.99;

# delete this sample table
DROP TABLE IF EXISTS backpacks;