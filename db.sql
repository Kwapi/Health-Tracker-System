DROP TABLE member CASCADE;
DROP TABLE physicalHealth CASCADE;
DROP TABLE weightProgress CASCADE;
DROP TABLE exercise CASCADE;
DROP TABLE exerciseProgress CASCADE;
DROP TABLE meal CASCADE;
DROP TABLE mealProgress CASCADE;
DROP TABLE goal CASCADE;
DROP TABLE member_goals CASCADE;
DROP TABLE foodItem CASCADE;


CREATE TABLE member
(
	id 				SERIAL,
	email 			VARCHAR(100),
	password		VARCHAR(20),
	username 		VARCHAR (100),
	forename		VARCHAR (100),
	surname			VARCHAR (100),
	
	PRIMARY KEY (id)
	
);



CREATE TABLE physicalHealth
(
	ID 				SERIAL,
	heightcm 		INT,
	memberID		INT REFERENCES member(id) ON DELETE CASCADE,
	PRIMARY KEY (id)


);

CREATE TABLE weightProgress
(
	id				 SERIAL,
	physicalHealthID INT	REFERENCES physicalHealth(id) ON DELETE CASCADE,
	weightDate 		 VARCHAR(10),
	weight			 INT,
	
	PRIMARY KEY (id)
);


CREATE TABLE exercise
(
	id				SERIAL,
	name			VARCHAR(100),
	exerciseType		VARCHAR(100),
	calPerUnit		INT,
	
	PRIMARY KEY (id)
);

CREATE TABLE exerciseProgress
(
	id				SERIAL,
	memberID 		INT REFERENCES member(id) ON DELETE CASCADE,
	exerciseDate		VARCHAR(10),
	exerciseStartTime	VARCHAR(10),	
	duration		INT,
	exerciseID		INT REFERENCES exercise(id) ON DELETE SET NULL,
	
	
	PRIMARY KEY (id)
);

CREATE TABLE foodItem
(
	id				SERIAL,
	name			VARCHAR(100),
	foodType		VARCHAR(100),
	calPerUnit		INT,
	
	PRIMARY KEY (id)
);

CREATE TABLE  meal
(
	id				SERIAL,
	name			VARCHAR(100),
	calPerUnit		INT,
	
	PRIMARY KEY (id)
);


CREATE TABLE mealProgress
(
	id				SERIAL,
	memberID		INT REFERENCES member(id) ON DELETE CASCADE,
	mealID			INT REFERENCES meal(id) ON DELETE SET NULL,
	mealTime		INT,
	mealDate		VARCHAR(10),
	amount			INT,
	
	PRIMARY KEY (id)
);


CREATE TABLE goal
(
	id				SERIAL,
	goalType		INT,
	goalDate		VARCHAR(10),
	goalStart		VARCHAR(10),
	goalDeadline		VARCHAR(10),
	target			INT,
	notified		BOOLEAN,
	
	PRIMARY KEY (id)
);

CREATE TABLE member_goals
(
	id				SERIAL,
	memberID		INT REFERENCES member(id) ON DELETE CASCADE,
	goalID			INT REFERENCES goal(id) ON DELETE CASCADE,
	
	PRIMARY KEY (id)
);





	





---------------------------------------------
----POPULATING DATABASE FOR TESTING PURPOSES

INSERT INTO member(email, password, username, forename, surname)
	VALUES ('t_email', 't_pass', 't_user', 't_fname', 't_sname');
INSERT INTO member(email, password, username, forename, surname)
	VALUES ('j.smith@foo.bar', 'smithj', 'smithj', 'Jon', 'Smith');
INSERT INTO member(email, password, username, forename, surname)
	VALUES ('gable@fake.mail', 'moneyman', 'moneyman', 'Mark', 'Gable');
INSERT INTO member(email, password, username, forename, surname)
	VALUES ('hrm@no-mail.xyz', 'henmar', 'henmar', 'Hendry', 'Marcus');
INSERT INTO member(email, password, username, forename, surname)
	VALUES ('nida@oplc.yada', 'daniel1', 'daniel1', 'Nigel', 'Danielson');
INSERT INTO member(email, password, username, forename, surname)
	VALUES ('Af82@msn.aurora', 'amanda99', 'amanda99', 'Amanda', 'Flatly');
	
INSERT INTO physicalHealth(heightcm, memberID)
	VALUES (-1, 1);
INSERT INTO physicalHealth(heightcm, memberID)
	VALUES (188, 2);
INSERT INTO physicalHealth(heightcm, memberID)
	VALUES (210, 3);
INSERT INTO physicalHealth(heightcm, memberID)
	VALUES (160, 4);
INSERT INTO physicalHealth(heightcm, memberID)
	VALUES (185, 5);
INSERT INTO physicalHealth(heightcm, memberID)
	VALUES (180, 6);

INSERT INTO weightProgress(physicalHealthID, weightDate, weight)
	VALUES (1, '2015-04-14', -1);
INSERT INTO weightProgress(physicalHealthID, weightDate, weight)
	VALUES (2, '2015-04-14', 86182);
INSERT INTO weightProgress(physicalHealthID, weightDate, weight)
	VALUES (3, '2015-04-14', 10400);
INSERT INTO weightProgress(physicalHealthID, weightDate, weight)
	VALUES (4, '2015-04-14', 81646);
INSERT INTO weightProgress(physicalHealthID, weightDate, weight)
	VALUES (5, '2015-04-14', 65500);
INSERT INTO weightProgress(physicalHealthID, weightDate, weight)
	VALUES (6, '2015-04-14', 82000);

INSERT INTO exercise(name, exerciseType, calPerUnit)
	VALUES ('t_name', 't_exercise', -1);
INSERT INTO exercise(name, exerciseType, calPerUnit)
	VALUES ('Running', 'time', 5);
INSERT INTO exercise(name, exerciseType, calPerUnit)
	VALUES ('Swimming', 'time', 8);
INSERT INTO exercise(name, exerciseType, calPerUnit)
	VALUES ('Cycling', 'time', 5);
INSERT INTO exercise(name, exerciseType, calPerUnit)
	VALUES ('Football', 'time', 6);
INSERT INTO exercise(name, exerciseType, calPerUnit)
	VALUES ('Weight Lifting', 'time', 10);
INSERT INTO exercise(name, exerciseType, calPerUnit)
	VALUES ('Tennis', 'time', 7);
INSERT INTO exercise(name, exerciseType, calPerUnit)
	VALUES ('Coding', 'time', 12);
INSERT INTO exercise(name, exerciseType, calPerUnit)
	VALUES ('Walking', 'time', 3);

INSERT INTO exerciseProgress(memberID, exerciseDate, duration, exerciseID)
	VALUES (1, '2015-04-14', -1, 1);

INSERT INTO foodItem(name, foodType, calPerUnit)
	VALUES ('t_name', 't_ftype', -1);

INSERT INTO meal(name, calPerUnit)
	VALUES ('t_name', -1);
INSERT INTO meal(name, calPerUnit)
	VALUES ('Pizza', 500);
INSERT INTO meal(name, calPerUnit)
	VALUES ('Cereal', 200);
INSERT INTO meal(name, calPerUnit)
	VALUES ('Burger', 400);
INSERT INTO meal(name, calPerUnit)
	VALUES ('Sandwich', 300);
INSERT INTO meal(name, calPerUnit)
	VALUES ('Porridge', 200);
INSERT INTO meal(name, calPerUnit)
	VALUES ('Lasagna', 400);
INSERT INTO meal(name, calPerUnit)
	VALUES ('Sauce of Joost with Chips', 500);
INSERT INTO meal(name, calPerUnit)
	VALUES ('Chocolate Bar', 50);
INSERT INTO meal(name, calPerUnit)
	VALUES ('HongKong Fusion Special Rice', 9001);


INSERT INTO mealProgress(memberID, mealID, mealDate, amount)
	VALUES (1, 1, '2015-04-14', -1);

INSERT INTO goal(goalType, goalDate, goalStart, goalDeadline, target)
	VALUES (3, '2015-04-14', '2015-04-14', '2015-04-19', 20);

INSERT INTO goal(goalType, goalDate, goalStart, goalDeadline, target)
	VALUES (0, '2015-04-14', '2015-04-14', '2015-04-24', 120000);

INSERT INTO member_goals(memberID, goalID)
	VALUES (2, 1);
INSERT INTO member_goals(memberID, goalID)
	VALUES (2, 2);
	
