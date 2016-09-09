CREATE DATABASE IF NOT EXISTS site_db;

USE site_db;

CREATE TABLE IF NOT EXISTS shop (
item_id INT UNSIGNED NOT NULL AUTO_INCREMENT,
item_name VARCHAR(20) NOT NULL,
item_desc VARCHAR(200) NOT NULL,
item_img VARCHAR(20) NOT NULL,
item_price DECIMAL(4,2) NOT NULL,
PRIMARY KEY (item_id)
);

/*
INSERT INTO shop ( item_name, item_desc, item_img, item_price )
VALUES ( "Cow", "A friendly field buddy.", "images/cow.png",19.99 ); 
INSERT INTO shop ( item_name, item_desc, item_img, item_price )
VALUES ( "Dog", "A friendly lap buddy.", "images/dog.png",14.99 );
INSERT INTO shop ( item_name, item_desc, item_img, item_price )
VALUES ( "Goat", "A friendly mountain buddy.", "images/goat.png",16.99 );
INSERT INTO shop ( item_name, item_desc, item_img, item_price )
VALUES ( "Leopard", "A friendly spotted buddy.", "images/leopard.png",17.99 );
INSERT INTO shop ( item_name, item_desc, item_img, item_price )
VALUES ( "Rhino", "A friendly jungle buddy.", "images/rhino.png",29.99 );
*/

INSERT INTO shop 
( item_name, item_desc, item_img, item_price )
VALUES 
( "Cow", "A friendly field buddy.", "images/cow.png",19.99 ), 
( "Dog", "A friendly lap buddy.", "images/dog.png",14.99 ),
( "Goat", "A friendly mountain buddy.", "images/goat.png",16.99 ),
( "Leopard", "A friendly spotted buddy.", "images/leopard.png",17.99 ),
( "Rhino", "A friendly jungle buddy.", "images/rhino.png",29.99 );