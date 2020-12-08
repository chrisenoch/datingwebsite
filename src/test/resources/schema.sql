
CREATE TABLE Category (
  id INTEGER NOT NULL AUTO_INCREMENT,
  `category` varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)

);

INSERT INTO `category` VALUES (42,'Movies');

CREATE TABLE country (
    id   INTEGER      NOT NULL AUTO_INCREMENT,
    name VARCHAR(128) NOT NULL,
    PRIMARY KEY (id)
);



