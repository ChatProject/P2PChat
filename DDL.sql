DROP TABLE user;
DROP TABLE friends_list;

CREATE TABLE user (
	user_id INT AUTO_INCREMENT,
	display_name varchar(20) NOT NULL UNIQUE,
	PRIMARY KEY (user_id)
);

CREATE TABLE friend_list (
	user_id INT,
	friend_id INT,
	PRIMARY KEY (user_id, friend_id),
	FOREIGN KEY (user_id) REFERENCES user(user_id),
	FOREIGN KEY (friend_id) REFERENCES user(user_id)
);

INSERT INTO user (display_name) VALUES ('JAKE');
INSERT INTO user (display_name) VALUES ('SAURIN');
INSERT INTO user (display_name) VALUES ('JEFF');
INSERT INTO user (display_name) VALUES ('SUPERMAN');
INSERT INTO user (display_name) VALUES ('BATMAN');
INSERT INTO user (display_name) VALUES ('SPIDERMAN');

INSERT INTO friend_list VALUES (1, 2);
INSERT INTO friend_list VALUES (1, 3);

INSERT INTO friend_list VALUES (2, 1);
INSERT INTO friend_list VALUES (2, 2);
INSERT INTO friend_list VALUES (2, 3);
INSERT INTO friend_list VALUES (2, 4);
INSERT INTO friend_list VALUES (2, 5);
INSERT INTO friend_list VALUES (2, 6);

INSERT INTO friend_list VALUES (3, 1);
INSERT INTO friend_list VALUES (3, 2);
