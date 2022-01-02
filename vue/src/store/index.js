import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)

/*
 * The authorization header is set for axios when you login but what happens when you come back or
 * the page is refreshed. When that happens you need to check for the token in local storage and if it
 * exists you should set the header so that it will be attached to each request
 */
const currentToken = localStorage.getItem('token')
const currentUser = JSON.parse(localStorage.getItem('user'));

if(currentToken != null) {
  axios.defaults.headers.common['Authorization'] = `Bearer ${currentToken}`;
}

export default new Vuex.Store({
  state: {
    token: currentToken || '',
    user: currentUser || {},
    breeds: ['Afghan Hound',
    'Airedale Terrier',
    'Akita',
    'Alaskan Malamute',
    'American Pit Bull Terrier',
    'American Staffordshire Terrier',
    'Anatolian Shepherd Dog',
    'Australian Cattle Dog',
    'Australian Shepherd',
    'Basenji',
    'Basset Hound',
    'Beagle',
    'Belgian Malinois',
    'Bernese Mountain Dog',
    'Besenji',
    'Bichon Frise',
    'Bloodhound',
    'Border Collie',
    'Border Terrier',
    'Boston Terrier',
    'Bouviers des Flandres',
    'Boxer',
    'Boykin Spaniel',
    'Brittany',
    'Brussels Griffon',
    'Bull Terrier',
    'Bulldog',
    'Bullmastiff',
    'Cairn Terrier',
    'Cane Corso',
    'Cardigan Welsh Corgi',
    'Cavalier King Charles Spaniel',
    'Chesapeake Bay Retriever',
    'Chihuahua',
    'Chinese Crested',
    'Chow-Chow',
    'Cocker Spaniel',
    'Collie',
    'Corgi',
    'Coton de Tulear',
    'Dachshund',
    'Dalmation',
    'Doberman Pinscher',
    'Dogo Argentino',
    'Dogue de Bordeaux',
    'English Setter',
    'English Springer Spaniel',
    'French Bulldog',
    'German Pointer',
    'German Shepherd',
    'Giant Schnauzer',
    'Golden Retriever',
    'Goldendoodle',
    'Great Dane',
    'Great Pyrenees',
    'Greater Swiss Mountain Dog',
    'Havanese',
    'Irish Setter',
    'Irish Wolfhound',
    'Italian Greyhound',
    'Jack Russell Terrier',
    'Japanese Spitz',
    'Keeshonden',
    'Labradoodle',
    'Labrador Retriever',
    'Lagotti Romagnoli',
    'Leonberger',
    'Lhasa Apso',
    'Maltese',
    'Mastiff',
    'Mini American Shepherd',
    'Miniature Pinscher',
    'Miniature Schnauzer',
    'Mixed Breed',
    'Neapolitan Mastiff',
    'Newfoundland',
    'Norwegian Elkhound',
    'Old English Sheepdog',
    'Papillon',
    'Pekinese',
    'Pembroke Welsh Corgi',
    'Pointer',
    'Pomeranian',
    'Poodle',
    'Portuguese Water Dog',
    'Pug',
    'Rat Terrier',
    'Rhodesian Ridgeback',
    'Rottweiler',
    'Saint Bernard',
    'Samoyed',
    'Scottish Terrier',
    'Shar Pei',
    'Shetland Sheepdog',
    'Shiba Inu',
    'Shih Tzu',
    'Siberian Husky',
    'Soft Coated Wheaten Terrier',
    'Staffordshire Bull Terrier',
    'Standard Schnauzer',
    'Tibetan Terrier',
    'Vizsla',
    'Weimaraner',
    'Welsh Corgi',
    'West Highland Terrier',
    'Whippet',
    'Wirehaired Pointing Griffon',
    'Yorkshire Terrier',
    'Other'
  ], 
  photos: [
    'https://images.dog.ceo/breeds/setter-gordon/n02101006_2907.jpg',
    'https://images.dog.ceo/breeds/labrador/n02099712_5263.jpg',
'https://images.dog.ceo/breeds/pug/n02110958_4043.jpg',
'https://images.dog.ceo/breeds/pekinese/n02086079_3774.jpg', 
'https://images.dog.ceo/breeds/mix/Noah01.jpg',
'https://images.dog.ceo/breeds/terrier-norwich/n02094258_3598.jpg',
'https://images.dog.ceo/breeds/bullterrier-staffordshire/n02093256_3812.jpg',
'https://images.dog.ceo/breeds/springer-english/n02102040_4738.jpg',
'https://images.dog.ceo/breeds/terrier-irish/n02093991_4626.jpg',
'https://images.dog.ceo/breeds/collie-border/n02106166_2685.jpg',
'https://images.dog.ceo/breeds/leonberg/n02111129_77.jpg',
'https://images.dog.ceo/breeds/sheepdog-english/n02105641_9319.jpg',
'https://images.dog.ceo/breeds/chihuahua/n02085620_952.jpg',
'https://images.dog.ceo/breeds/spaniel-cocker/n02102318_3415.jpg',
'https://images.dog.ceo/breeds/sheepdog-shetland/n02105855_3434.jpg',
'https://images.dog.ceo/breeds/brabancon/n02112706_37.jpg',
'https://images.dog.ceo/breeds/papillon/n02086910_4999.jpg',
'https://images.dog.ceo/breeds/pinscher-miniature/n02107312_2478.jpg'

],
  },
  mutations: {
    SET_AUTH_TOKEN(state, token) {
      state.token = token;
      localStorage.setItem('token', token);
      axios.defaults.headers.common['Authorization'] = `Bearer ${token}`
    },
    SET_USER(state, user) {
      state.user = user;
      localStorage.setItem('user',JSON.stringify(user));
    },
    LOGOUT(state) {
      localStorage.removeItem('token');
      localStorage.removeItem('user');
      state.token = '';
      state.user = {};
      axios.defaults.headers.common = {};
    }
  }
})
