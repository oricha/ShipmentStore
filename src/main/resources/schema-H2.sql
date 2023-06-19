CREATE TABLE product (
                      id   INTEGER PRIMARY KEY AUTO_INCREMENT,
                      name VARCHAR(64) NOT NULL,
                      quantity INTEGER NOT NULL);
CREATE TABLE shipment (
                         id   INTEGER PRIMARY KEY AUTO_INCREMENT,
                         status VARCHAR(64) ,
                         PLANNEDDELIVERYDATE DATE,
                         category VARCHAR(64) NOT NULL,
                         product_id INTEGER );