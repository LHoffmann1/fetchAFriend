import axios from 'axios'

export default{ 
    createPet(pet) {
        return axios.post('/pet/addPuppy', pet);
    },
    listUserPets(id) {
        return axios.get(`/pet/userPets/${id}`);
    },
    updatePet(pet){
        return axios.put('/pet/pupdate/', pet);
    },
    removePet(petId){
        return axios.delete(`/pet/pupdate/${petId}`);
    }
}