DROP TABLE user;
DROP TABLE friend_list;

CREATE TABLE user (
	user_id INT AUTO_INCREMENT,
	display_name varchar(20) NOT NULL UNIQUE,
	ipv4_address varchar(15),
	listening_port INT,
	last_active TIMESTAMP,
	PRIMARY KEY (user_id)
);

CREATE TABLE friend_list (
	user_id INT,
	friend_id INT,
	PRIMARY KEY (user_id, friend_id),
	FOREIGN KEY (user_id) REFERENCES user(user_id),
	FOREIGN KEY (friend_id) REFERENCES user(user_id)
);

INSERT INTO user (display_name, ipv4_address, listening_port, last_active) VALUES ('JAKE', '10.0.0.100', 8000, NULL);
INSERT INTO user (display_name, ipv4_address, listening_port, last_active) VALUES ('SAURIN', NULL, NULL, NULL);
INSERT INTO user (display_name, ipv4_address, listening_port, last_active) VALUES ('JEFF', NULL, NULL, NULL);
INSERT INTO user (display_name, ipv4_address, listening_port, last_active) VALUES ('SUPERMAN', NULL, NULL, NULL);
INSERT INTO user (display_name, ipv4_address, listening_port, last_active) VALUES ('BATMAN', NULL, NULL, NULL);
INSERT INTO user (display_name, ipv4_address, listening_port, last_active) VALUES ('SPIDERMAN', NULL, NULL, NULL);

INSERT INTO friend_list VALUES (1, 2);
INSERT INTO friend_list VALUES (1, 3);

INSERT INTO friend_list VALUES (2, 1);
INSERT INTO friend_list VALUES (2, 3);
INSERT INTO friend_list VALUES (2, 4);
INSERT INTO friend_list VALUES (2, 5);
INSERT INTO friend_list VALUES (2, 6);

INSERT INTO friend_list VALUES (3, 1);
INSERT INTO friend_list VALUES (3, 2);
