CREATE TABLE `user` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `phone_number` varchar(256) NOT NULL,
  `password` varchar(256) NOT NULL,
  `user_name` varchar(256) NOT NULL,
  `registration_time` timestamp NOT NULL,
  `last_login_time` timestamp NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `phone_number` (`phone_number`)
  );
  
  CREATE TABLE `inventory` (
  `inventory_id` int NOT NULL AUTO_INCREMENT,
  `isbn` varchar(256) NOT NULL,
  `status` varchar(256) NOT NULL,
  `store_time` timestamp NOT NULL,
  PRIMARY KEY (`inventory_id`)
);

CREATE TABLE `book` (
  `isbn` varchar(256) NOT NULL,
  `name` varchar(256) NOT NULL,
  `author` varchar(256) NOT NULL,
  `introduction` varchar(3000) NOT NULL,
  PRIMARY KEY (`isbn`)
);

CREATE TABLE `borrowing_record` (
  `record_id` int NOT NULL AUTO_INCREMENT,
  `user_id` varchar(256) NOT NULL,
  `inventory_id` varchar(256) NOT NULL,
  `borrowing_time` timestamp NOT NULL,
  `return_time` timestamp,
  PRIMARY KEY (`record_id`)
);

DELIMITER //
CREATE PROCEDURE get_user_by_phone_number(IN input_user_phone_number varchar(256))
BEGIN
	SELECT user_id, phone_number, `password`, user_name, registration_time, last_login_time 
	FROM `user`
    WHERE phone_number = input_user_phone_number;
END //
CREATE PROCEDURE get_user_by_id(IN input_user_id INT) 
BEGIN 
	SELECT user_id, phone_number, `password`, user_name, registration_time, last_login_time 
	FROM `user` 
    WHERE user_id = input_user_id; 
END //
CREATE PROCEDURE insert_user(
        IN input_user_phone_number varchar(256), 
        IN input_password varchar(256), 
        IN input_user_name varchar(256), 
        IN input_registration_time timestamp, 
        IN input_last_login_time timestamp,
        OUT newUserId INT) 
BEGIN 
	INSERT INTO `user` (phone_number, password, user_name, registration_time, last_login_time) 
	VALUES(input_user_phone_number, input_password, input_user_name, input_registration_time, input_last_login_time) ; 
	SET newUserId = LAST_INSERT_ID(); 
END //
DELIMITER ;