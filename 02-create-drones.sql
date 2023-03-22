-- -----------------------------------------------------
-- Schema drone-management-system
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `drone`;

CREATE SCHEMA `drone`;
USE `drone` ;
-- -----------------------------------------------------
-- Table `drone`.`drone`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `drone`.`drone` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `drone_name` VARCHAR(255) NOT NULL, 
  `serial_number` VARCHAR(255) NOT NULL,
  `weight` DOUBLE PRECISION,
   `battery_capacity` INTEGER ,
  `state` ENUM('IDLE', 'LOADING', 'LOADED', 'DELIVERING', 'DELIVERED', 'RETURNING'),
  `model_type` ENUM('LIGHTWEIGHT', 'MIDDLEWEIGHT', 'CRUISERWEIGHT', 'HEAVYWEIGHT'),
  `is_available` BIT DEFAULT 1,
   `date_created` DATETIME(6) DEFAULT NULL,
  `last_updated` DATETIME(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
  
) 
ENGINE=InnoDB
AUTO_INCREMENT = 1;
-- -----------------------------------------------------
-- Table `drone`.`medication`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `drone`.`medication` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  `weight` DOUBLE PRECISION,
  `code` VARCHAR(255) NULL DEFAULT NULL,
  `image` VARCHAR(255) NULL DEFAULT NULL,
   `CREATED_AT` DATETIME(6) DEFAULT NULL,
  `UPDATED_AT` DATETIME(6) DEFAULT NULL,
   PRIMARY KEY (`id`),
   KEY `fk_drone_id` (`drone_id`),
  CONSTRAINT `fk_drone_id` FOREIGN KEY (`drone_id`) REFERENCES `drone` (`id`))
ENGINE=InnoDB
AUTO_INCREMENT = 1;


-- -----------------------------------------------------
-- Add sample data
-- -----------------------------------------------------

INSERT INTO drone(drone_name, serial_number, battery_capacity, state, model_type, is_available, date_created, date_updated)
 VALUES ('Mahmoud', 'SN-00000001', 100, 'IDLE', 'LIGHTWEIGHT', 1, NOW(), NOW());

INSERT INTO medication (name, weight, code, image, CREATED_AT, UPDATED_AT)
VALUES ('Envelop', 50, 'stringurl' , NOW(),NOW());




