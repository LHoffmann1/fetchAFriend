<template>
  <div id="main">
    <div id="play-date-details">
      <h1>Playdate Details:</h1>
      <h3>Location:</h3>
      <div class="info" id="address">
        {{ playDate.locationStreetAddress }} <br />
        {{ playDate.locationCity }}, {{ playDate.locationZipcode }} <br />
      </div>
      <h3>Date:</h3>
      <div class="info" id="date">
        {{ changeDateFormat(playDate.meetingDate) }} Beginning at:
        {{ changeTimeFormat(playDate.startTime) }} <br />Anticipated Length:
        {{ playDate.duration }} minutes <br />
      </div>
      <h3>Host Information:</h3>
      <div class="info" id="host">
        Name: {{ playDate.name }}<br />
        Breed: {{ playDate.breed }}<br />
        Size: {{ playDate.size }} <br />Temperament: {{ playDate.temperament }}
        <br />Spayed/Neutered: {{ playDate.spayedNeutered }} <br /><br />
        {{ playDate.name }} plays well with dogs who are
        <span
          v-for="description in playDate.mateDescription"
          v-bind:key="description.index"
          >{{ description }} </span
        >
        and are {{ playDate.mateSize }} in size.
      </div>
      <br />
      <h3>Request to Join:</h3>
      <form v-on:submit.prevent="sendRequest">
        <label> Which of Your Pets Will Be Attending: </label>
        <select class="form" v-model="playDate.matePetId">
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
          >
        </select>
        <br /><br /><button class="button-submit">Submit</button>
        <button class="button-cancel">
          <router-link id="cancelBtn" to="/play-date/available/"
            >Cancel</router-link
          >
        </button>
      </form>
    </div>

    <!--<div v-for="description in playDate.mateDescription" v-bind:key="description.index">-->
  </div>
</template>

<script>
import playDateService from "@/services/PlayDateService";
import petService from "@/services/PetService";
export default {
  data() {
    return {
      playDateId: -1,
      playDate: {},
      id: -1,
      dogsByUserId: [],
    };
  },
  methods: {
    changeDateFormat(insertDate){
 let myArr= insertDate.split('-');
 return myArr[1]+"-"+myArr[2]+"-"+myArr[0];
},
    changeTimeFormat(givenTime){
            let timeArr= givenTime.split(":");
            if (timeArr[0]<12){
                return timeArr[0]+":"+timeArr[1]+" AM";
            } else if(timeArr[0]==12) {
                return timeArr[0]+":"+timeArr[1]+" PM"
            } else {
                return timeArr[0]-12+":"+timeArr[1]+" PM"
            }
        },
  
    sendRequest() {
      const newRequest = {
        playDateId: this.playDateId,
        mateId: this.playDate.matePetId,
      };
      
      playDateService
        .createRequest(newRequest)
        .then((response) => {
          if (response.status === 201) {
            window.alert("Bow-wow! Your request has been sent!");
            this.$router.push("/");
          }
        })
        .catch((error) => {
          if (error.response) {
            window.alert("Bad Request");
          } else if (error.request) {
            window.alert("Could not reach service");
          }
        });
  }
  },
  created() {
    this.playDateId = this.$route.params.id;
    console.log(this.playDateId);
    playDateService
      .getAvailablePlayDatesByPlayDateId(this.playDateId)

      .then((response) => {
        this.playDate = response.data;
      })
      .catch((error) => {
        if (error.response) {
          window.alert("Missing Play Date");
        } else if (error.request) {
          window.alert("Could not reach service");
        }
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
  }
  
};
</script>

<style scoped>
#main {
  background-color: rgb(2, 59, 109);
  color: rgb(2, 59, 109);
  padding-bottom: 50px;
  font-weight: bold;
}
#play-date-details {
  margin-left: auto;
  margin-right: auto;
  width: 600px;
  border: 10px rgb(25, 149, 180) dotted;
  padding-bottom: 20px;
  background-image: url("../../images/PawPrints.png");
}
h1 {
  font-size: 48px;
  margin-left: 20px;
  color: rgb(2, 59, 109);
}
h3 {
  font-size: 32px;
  margin-left: 20px;
}
.info {
  font-size: 24px;
  margin-left: 20px;
}
label {
  font-size: 24px;
  margin-left: 20px;
}
button {
  height: 40px;
  font-size: 18px;
  background-color: white;
  color: rgb(2, 59, 109);
  font-weight: bold;
  border: 3px rgb(2, 59, 109) solid;
  margin-left: 20px;
  font-family: "Alegreya Sans", sans-serif;
}
#cancelBtn {
  height: 30px;
  font-size: 18px;
  background-color: white;
  color: rgb(2, 59, 109);
  font-weight: bold;
  border: 5px white solid;
  text-decoration: none;
  font-family: "Alegreya Sans", sans-serif;
}
option {
  font-size: 18px;
}
select {
  font-size: 20px;
  font-family: "Alegreya Sans", sans-serif;
  color: rgb(2, 59, 109);
  width: 175px;
  border: rgb(2, 59, 109) 3px solid;
}
</style>