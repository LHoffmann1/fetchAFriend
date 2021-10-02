DROP TABLE IF EXISTS "request" CASCADE;
DROP TABLE IF EXISTS "user_pet" CASCADE;
DROP TABLE IF EXISTS "pets" CASCADE;
DROP TABLE IF EXISTS "play_dates" CASCADE;
DROP TABLE IF EXISTS "location" CASCADE;
DROP TABLE IF EXISTS "play_date_statuses" CASCADE;


CREATE TABLE "pets" (
  "pet_id" serial,
  "name" varchar(50),
  "breed" varchar(30),
  "birth_year" int,
  "gender" varchar(10),
  "temperament" varchar(1000),
  "size" varchar(100),
  "spayed_neutered" varchar(5),
  "image" varchar(100),
  PRIMARY KEY ("pet_id")
);

CREATE TABLE "user_pet" (
  "pet_id" int NOT NULL,
  "user_id" int NOT NULL,
  CONSTRAINT "PK_user_pet" PRIMARY KEY ("pet_id", "user_id"),
  CONSTRAINT "FK_user_pet_user" FOREIGN KEY ("user_id") REFERENCES "users" ("user_id"),
  CONSTRAINT "FK_user_pet_pets" FOREIGN KEY ("pet_id") REFERENCES "pets" ("pet_id")
  );

ALTER TABLE "users" ADD "email" varchar(320) UNIQUE;

CREATE TABLE "play_date_statuses" (
  "status_id" serial,
  "status" varchar(20),
  PRIMARY KEY ("status_id")
);

CREATE TABLE "play_dates" (
  "play_date_id" serial,
  "host_pet_id" int,
  "mate_pet_id" int,
  "location_street_address" varchar (100),
  "location_city" varchar (100),
  "location_zipcode" int,
  "meeting_date" date,
  "start_time" time,
  "duration" int,
  "mate_description" varchar(100),
  "mate_size" varchar (20),
  "status_id" int,
  PRIMARY KEY ("play_date_id"),
  CONSTRAINT "FK_play_dates.status_id"
    FOREIGN KEY ("status_id")
      REFERENCES "play_date_statuses"("status_id"),
  CONSTRAINT "FK_play_dates.mate_pet_id"
    FOREIGN KEY ("mate_pet_id")
      REFERENCES "pets"("pet_id"),
  CONSTRAINT "FK_play_dates.host_pet_id"
    FOREIGN KEY ("host_pet_id")
      REFERENCES "pets"("pet_id")
);

CREATE TABLE "request" (
  "request_id" serial,
  "play_date_id" int,
  "status_id" int,
  "mate_id" int,
  PRIMARY KEY ("request_id"),
  CONSTRAINT "FK_request.play_date_id"
    FOREIGN KEY ("play_date_id")
      REFERENCES "play_dates"("play_date_id"),
  CONSTRAINT "FK_request.status_id"
    FOREIGN KEY ("status_id")
      REFERENCES "play_date_statuses"("status_id"),
  CONSTRAINT "FK_request.mate_id"
    FOREIGN KEY ("mate_id")
      REFERENCES "pets"("pet_id")
);

INSERT INTO play_date_statuses (status) VALUES ('Available');
INSERT INTO play_date_statuses (status) VALUES ('Pending');
INSERT INTO play_date_statuses (status) VALUES ('Confirmed');
INSERT INTO play_date_statuses (status) VALUES ('Cancelled');
INSERT INTO play_date_statuses (status) VALUES ('Declined');

 

