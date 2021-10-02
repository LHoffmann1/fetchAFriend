DELETE FROM user_pet;
DELETE FROM request;
DELETE FROM play_dates;
DELETE FROM pets;
DELETE FROM users;

//Could not figure out how to reset the ids...

//users must be created due to authentication


INSERT INTO pets (name, breed, birth_year, gender, temperament, size, spayed_neutered, image) 
VALUES ('Gerry','Labradoodle','2010','Male','energetic','large','yes','https://images.dog.ceo/breeds/labradoodle/labradoodle-forrest.png'),
('Dobby','Doberman Pinscher','2011','Female','protective','large','yes','https://images.dog.ceo/breeds/doberman/n02107142_5181.jpg'),
('Victoria','Old English Sheepdog','2012','Male','timid','extra large','yes','https://images.dog.ceo/breeds/sheepdog-english/n02105641_534.jpg'),
('Melanie','Havanese','2014','Female','curious','small','no','https://images.dog.ceo/breeds/havanese/00100trPORTRAIT_00100_BURST20191222103956878_COVER.jpg'),
('George','Akita','2015','Male','friendly','medium','no','https://images.dog.ceo/breeds/akita/An_Akita_Inu_resting.jpg');

--create relationships of users with pets
INSERT INTO user_pet(user_id, pet_id)
VALUES (13,1),(16,2),(13,3),(16,4),(16,5);

INSERT INTO play_dates (host_pet_id, location_street_address, location_city, location_zipcode, 
meeting_date, start_time, duration, mate_description, mate_size, status_id) 
VALUES('1','1 Richmond Hwy','Alexandria','22306','2021-10-04','9:30:00','30','[friendly,curious]','small',1),
('2','2 Washington Street','Alexandria','22301','2021-10-05','10:30:00','30','[timid,submissive]','medium',1),
('3','3 Duke St','Alexandria','22301','2021-10-06','11:00:00','30','[dominant]','small',1),
('4','4 Jefferson Dr','Springfield','22153','2021-10-07','12:30:00','30','[friendly,curious]','small',1),
('5','5 Lincoln Lane','Arlington','20301','2021-10-08','13:00:00','30','[relaxed,submissive]','large',1);

