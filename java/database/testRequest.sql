SELECT play_dates.meeting_date, play_dates.start_time, play_dates.duration, play_dates.location_street_address, play_dates.host_pet_id, pets.pet_id, pets.name, pets.breed, pets.birth_year, pets.gender, pets.temperament, pets.size, pets.spayed_neutered FROM request 
JOIN play_dates ON request.play_date_id= play_dates.play_date_id 
JOIN pets ON request.mate_id= pets.pet_id
JOIN user_pet ON play_dates.host_pet_id=user_pet.pet_id WHERE user_id=16 AND request.status_id=2 
GROUP BY play_dates.play_date_id, pets.pet_id 
ORDER BY meeting_date ASC; 

