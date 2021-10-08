<template>
  <div id="body">
    <form id="create-play-date" v-on:submit.prevent="savePlayDate">
      <h1 id="title">Create Your Playdate:</h1>
      <label for="host-pet-id"
        >Which Of Your Pets <br> Is Looking For A Playmate:
      </label>
      <select class="form" v-model="playDate.hostPetId">
        <option
          v-for="dog in dogsByUserId"
          v-bind:key="dog.petId"
          v-bind:hostPetId="hostPetId"
          v-bind:value="dog.petId"
          required
          selected
        >
          {{ dog.name }}
        </option>
      </select>
      <br />
      <label for="locationAddress">Location Address: </label>
      <input class="form" type="text" v-model="playDate.locationStreetAddress" required />
      <br />
      <label for="locationCity">Location City: </label>
      <input class="form" type="text" v-model="playDate.locationCity" required />
      <br />
      <label for="locationZipcode">Location Zip Code: </label>
      <input
        class="form"
        type="text"
        pattern="(?=.*\d).{5,}"
        v-model="playDate.locationZipcode"
        required
      />
      <br />
      <label for="meetingDate">Meeting Date: </label>
      <input
        class="form"
        type="date"
        v-model="playDate.meetingDate"
        v-bind:min="this.currentDate"
        required
      />
      <br />
      <label for="start-time">Start Time: </label>
      <input
        class="form"
        type="time"
        min="06:00"
        max="20:00"
        step="600"

        v-model="playDate.startTime"
        required
      />
      <br />
      <label for="duration">Duration In Minutes: </label>
      <input
        class="form"
        type="number"
        min="30"
        max="120"
        step="15"
        v-model="playDate.duration"
        required
      />
      <br />
      
      <label for="mate-size">Preferred Playmate Size: </label>
      <select
        class="form"
        name="mate-size"
        id="mate-size"
        v-model="playDate.mateSize"
        required
      >
        <option value="extra small" selected>
          Extra Small (for example: Chihuahua, Maltese, Yorkshire Terrier)
        </option>
        <option value="small" selected>
          Small (for example: Beagle, French Bulldog, Boston Terrier)
        </option>
        <option value="medium" selected>
          Medium (for example: Border Collie, Corgi, Spaniel)
        </option>
        <option value="large" selected>
          Large (for example: Lab, Golden Retriever, Australian Shepherd)
        </option>
        <option value="extra large" selected>
          Extra Large (for example: Great Dane, St. Bernard, Newfoundland)
        </option>
        <option value="no preference" selected>No Preference</option>
      </select>
      <label id="temperament" for="description"
        >Preferred Playmate Temperament(s):
      </label>
      <br />
       <div id="temperament-list">
      <input
        class="form"
        type="checkbox"
        value="energetic"
        id="description"
        v-model="playDate.description"
      />
      <label class="checkbox" for="energetic"
        >High Energy: These dogs never tire and can chase a ball for
        hours!</label
      >
      <br />
      <input
        class="form"
        type="checkbox"
        value="timid"
        id="description"
        v-model="playDate.description"
      />
      <label class="checkbox" for="timid"
        >Timid: Once these pups feel comfortable, they make great
        playmates!</label
      >
      <br />
      <input
        class="form"
        type="checkbox"
        value="friendly"
        id="description"
        v-model="playDate.description"
      />
      <label class="checkbox" for="friendly"
        >Friendly: These dogs love everyone they encounter and have never met a
        stranger!</label
      >
      <br />
      <input
        class="form"
        type="checkbox"
        value="protective"
        id="description"
        v-model="playDate.description"
      />
      <label class="checkbox" for="protective"
        >Protective: These human devoted pups may prefer
        to socialize from a distance!</label
      >
      <br />
      <input
        class="form"
        type="checkbox"
        value="dominant"
        id="description"
        v-model="playDate.description"
      />
      <label class="checkbox" for="dominant"
        >Dominant: These are the leaders of the pack and like to be in
        charge!</label
      >
      <br />
      <input
        class="form"
        type="checkbox"
        value="curious"
        id="description"
        v-model="playDate.description"
      />
      <label class="checkbox" for="curious"
        >Curious: These dogs are most interesting in exploring their
        surroundings!</label
      >
      <br />
      <input
        class="form"
        type="checkbox"
        value="relaxed"
        id="laid-back"
        v-model="playDate.description"
      />
      <label class="checkbox" for="relaxed"
        >Laid Back: These pups are content to lounge around and soak in the
        sun!</label
      >
      <br />
      <input
        class="form"
        type="checkbox"
        value="submissive"
        id="submissive"
        v-model="playDate.description"
      />
      <label class="checkbox" for="submissive"
        >Submissive: These dogs are happy to let others take the lead!</label
      >
      <br />
      <input
        class="form"
        type="checkbox"
        value="no preference"
        id="nopreference"
        v-model="playDate.description"
      />
      <label class="checkbox" for="no preference" >No Preference</label>
      <br />
</div>
      
      <br />
      <br />
      <button class="button-submit">Submit</button>
      <button class="button-cancel">
        <router-link id="cancelButton" :to="{ name: 'home' }">Cancel</router-link></button>
    </form>
  </div>
</template>

<script>
import playDateService from "@/services/PlayDateService";
import petService from "@/services/PetService";

export default {
  data() {
    return {
      // form: "",
      // checkboxes: [],
      // checkboxLength: "",
      // firstCheckbox: "",

      currentDate: "",

      dogsByUserId: [],

      id: -1,

      playDate: {
        hostPetId: "",
        locationStreetAddress: "",
        locationCity: "",
        locationZipcode: "",
        meetingDate: "",
        startTime: "",
        duration: "",
        description: [],
        mateSize: "",
      },
    };
  },
  methods: {
    savePlayDate() {
      const newPlayDate = {
        hostPetId: this.playDate.hostPetId,
        locationStreetAddress: this.playDate.locationStreetAddress,
        locationCity: this.playDate.locationCity,
        locationZipcode: this.playDate.locationZipcode,
        meetingDate: this.playDate.meetingDate,
        startTime: this.playDate.startTime,
        duration: this.playDate.duration,
        mateDescription: this.playDate.description,
        mateSize: this.playDate.mateSize,
      };

      playDateService
        .createPlayDate(newPlayDate)
        .then((response) => {
          if (response.status === 201) {
            window.alert("Play Date Added!"); 
            this.$router.push('/');
          }
        })
        .catch((error) => {
          if (error.response) {
            window.alert("Invalid Playdate");
          } else if (error.request) {
            window.alert("Could not reach service");
          }
        });
    },
  },
  created() {
    //Current Date from Server
    playDateService.getCurrentDate().then((response) => {
      this.currentDate = response.data;
    });

    //Logged in user's ID
    this.id = this.$store.state.user.id;
    console.log(this.id);

    petService
      .listUserPets(this.id)
      .then((dogData) => {
        this.dogsByUserId = dogData.data;
      })
      .catch((err) => {
        console.error(err + " nothing returned");
      });
  },
};
</script>

<style scoped>
#body {
  background-color: rgb(2, 59, 109);
  color: white;
  padding-bottom:50px;
}
#create-play-date {
  border: white 5px dotted;
  padding: 20px;
  font-size: 30px;
  width:700px;
  margin-left: auto;
  margin-right: auto;
  background-color: rgb(2, 59, 109);
  background-image: url("../../images/PawPrints.png");
  border: 10px rgb(25, 149, 180) dotted;
  color:rgb(2, 59, 109);
  
}
.form {
  margin-bottom: 20px;
  
}

#title {
  font-size: 40px;
  font-weight: bold;
  text-decoration: underline;
  text-align: center;
}

#temperament {
  margin-bottom: 10px;
}
.button-submit {
  margin-right: 10px;
  margin-bottom: 25px;
  
}
label{
  display: inline-block;
  width: 400px;
}
button{
    height: 40px;
  font-size: 20px;
  background-color: white;
  color: rgb(2, 59, 109);
  font-weight: bold;
  border: 5px white solid;
  font-family: 'Alegreya Sans', sans-serif;
  border: 3px solid rgb(2, 59, 109);
}
#cancelButton{
   height: 30px;
  font-size: 20px;
  background-color: white;
  color: rgb(2, 59, 109);
  font-weight: bold;
  border: 5px white solid;
  text-decoration: none;
  
}
input, select{
  font-size:24px;
  width: 400px;
  font-family: 'Alegreya Sans', sans-serif;
  color: rgb(2, 59, 109);
  border: 3px solid rgb(2, 59, 109);
}
input{
  width: 390px;
}
#temperament-list{
  margin-left: -200px;

  
}
.checkbox {
  font-size: 20px;
  display: inline;
  margin-left:-185px;
  
  
}


</style>