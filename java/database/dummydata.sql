--DELETE FROM user_pet;
--DELETE FROM request;
--DELETE FROM play_dates;
--DELETE FROM pets;
--DELETE FROM users;

--Could not figure out how to reset the ids...

--users must be created due to authentication


INSERT INTO pets (name, breed, birth_year, gender, temperament, size, spayed_neutered, image) 
VALUES ('Webster','Labradoodle','2010','Male','energetic','large','yes','https://images.dog.ceo/breeds/labradoodle/labradoodle-forrest.png'),
('Lily','Doberman Pinscher','2011','Female','curious','large','yes','https://images.dog.ceo/breeds/doberman/n02107142_5181.jpg'),
('Gus','Bernese Mountain Dog','2012','Male','timid','large','yes','https://images.dog.ceo/breeds/mountain-bernese/n02107683_5026.jpg'),
('Doogal','Havanese','2014','Male','relaxed','small','no','https://images.dog.ceo/breeds/havanese/00100trPORTRAIT_00100_BURST20191222103956878_COVER.jpg'),
('Winston','Mixed Breed','2015','Male','friendly','medium','no','https://images.dog.ceo/breeds/finnish-lapphund/mochilamvan.jpg'),
('Quinn', 'Jack Russell Terrier', '2019', 'Female', 'energetic', 'small', 'no','https://images.dog.ceo/breeds/terrier-russell/jack2.jpg'),
('Gonzo', 'Rhodesian Ridgeback', '2016', 'Male', 'friendly', 'large', 'yes','https://images.dog.ceo/breeds/ridgeback-rhodesian/n02087394_7777.jpg'),
('Cadillac', 'Great Dane', '2017', 'Male', 'relaxed', 'extra large', 'no','https://images.dog.ceo/breeds/dane-great/n02109047_22481.jpg'),
('Boss', 'German Shepherd', '2016', 'Male', 'protective', 'large', 'yes','https://images.dog.ceo/breeds/malinois/n02105162_5364.jpg'),
('Tank', 'Bulldog', '2014', 'Male', 'curious', 'medium', 'yes','https://images.dog.ceo/breeds/bulldog-english/jager-2.jpg'),
('Pip', 'Mixed Breed', '2019', 'Female', 'timid', 'small', 'yes','https://images.dog.ceo/breeds/schipperke/n02104365_9873.jpg'),
('Olaf','Great Pyrenees','2018','Male','relaxed','extra large','yes','https://images.dog.ceo/breeds/pyrenees/n02111500_9306.jpg');

--create relationships of users with pets
INSERT INTO user_pet(user_id, pet_id)
VALUES (7,1),(10,2),(8,3),(3,4),(11,5),(10,6),(3,7),(12,8), (12,9),(13,10), (13,11),(8,12);

INSERT INTO play_dates (host_pet_id, location_street_address, location_city, location_zipcode, 
meeting_date, start_time, duration, mate_description, mate_size, status_id) 
VALUES('1','56 Dunwood Dr.','Puptown','98765','2022-01-13','9:30:00','30','[friendly,curious]','no preference',1),
('2','14 Kirby Way','Dogville','23456','2022-01-07','10:30:00','60','[timid,submissive]','medium',1),
('3','46142 Jopfry Rd.','Dogville','24680','2022-01-07','11:00:00','75','[relaxed]','large',1),
('4','126 Lakeview Dr.','Puptown','98765','2022-01-10','12:30:00','60','[friendly,curious]','small',1),
('5','634 Mitchell St.','Woofville','56789','2022-01-14','13:00:00','45','[relaxed,submissive]','medium',1),
('6','14 Kirby Way','Dogville','23456','2022-01-05','10:00:00','75','[energetic,curious]','no preference',1),
('6','14 Kingsley Drive','Dogville','24680','2022-01-09','11:00:00','45','[energetic]','no preference',1),
('7','1457 Linkside Dr.','Puptown','98768','2022-01-08','14:00:00','60','[relaxed,submissive]','large',1),
('8','945 Player St.','Dogville','23456','2022-01-07','10:00:00','60','[energetic,friendly]','no preference',1),
('10','634 Didee St','Woofville','56789','2022-01-09','08:30:00','30','[relaxed, friendly','no preference',1);

INSERT INTO play_dates (host_pet_id,mate_pet_id, location_street_address, location_city, location_zipcode, 
meeting_date, start_time, duration, mate_description, mate_size, status_id) 
VALUES('1','8','56 Dunwood Dr.','Puptown','98765','2022-01-05','9:30:00','30','[friendly,curious]','no preference',3),
('3','9','14 Parson Way','Dogville','23456','2022-01-05','10:30:00','60','[timid,relaxed]','large',3),
('4','11','126 Lakeview Dr.','Puptown','98765','2022-01-05','12:30:00','60','[friendly,energetic]','small',3),
('2','8','3588 Kirk Dr.','Puptown','98765','2022-01-06','12:30:00','60','[friendly,energetic]','large',3);

INSERT INTO request (play_date_id,status_id, mate_id)
VALUES (3,2,2),(8,2,10),(1, 2, 5);