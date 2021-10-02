import axios from 'axios';

const http = axios.create({
  baseURL: "https://dog.ceo/api/breeds/image"
});

export default {
    list() {
        return http.get('/random');
    },

}