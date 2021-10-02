<template>
  <div id="main">
    <h1>Available Playdates:</h1>
    <table>
      <thead>
        <tr>
          <th class= "heading" colspan="5">Playdate Details</th>
          <th class="heading" colspan="3">Host Dog Info</th>
          <th class="heading"></th>
        <tr class="column-title">
          <th>Ideal<br> Playmate</th>
          <th>City</th>
          <th>Zip <br>Code</th>
          <th>Date</th>
          <th>Time</th>
          <th class="size">Size</th>
          <th>Temperament</th>
          <th>
            Spayed/ <br />
            Neutered?
          </th>
          <th>Pick This <br>Playdate</th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <td id="ideal-playmate"></td>
          <td>
            <input
              type="text"
              id="locationCityFilter"
              v-model="filter.locationCity"
            />
          </td>
          <td>
            <input
              type="text"
              id="locationZipcodeFilter"
              v-model="filter.locationZipcode"
            />
          </td>
          <td>
            <input type="text" id="dateFilter" v-model="filter.date" />
          </td>
          <td>
            <input type="text" id="timeFilter" v-model="filter.time" />
          </td>
          <td class="size">
            <input type="text" id="sizeFilter" v-model="filter.size" />
          </td>
          <td>
            <input
              type="text"
              id="temperamentFilter"
              v-model="filter.temperament"
            />
          </td>
          <td>
            <input
              type="text"
              id="spayedNeuteredFilter"
              v-model="filter.spayedNeutered"
            />
          </td>
          <td id="pick-play-date"></td>
        </tr>

        <tr
          id="playDateInfo"
          v-for="playDate in filteredList"
          v-bind:key="playDate.id"
        >
          <td>
            <b>Temperament:</b>
            <div
              v-for="description in playDate.mateDescription"
              v-bind:key="description.index"
            >
              {{ description }}
            </div>
            <br />
            <b>Size: </b><br>{{ playDate.mateSize }}
          </td>
          <td>
            {{ playDate.locationStreetAddress }} <br />
            {{ playDate.locationCity }}
          </td>
          <td>{{ playDate.locationZipcode }}</td>
          <td>{{ changeDateFormat(playDate.meetingDate)}}</td>
          <td>
            Start time: {{ changeTimeFormat(playDate.startTime)}} <br />
            Playtime: {{ playDate.duration }} min.
          </td>
          <td>{{ playDate.size }}</td>
          <td>{{ playDate.temperament }}</td>
          <td>{{ playDate.spayedNeutered }}</td>
          <td>
            <router-link
              class="link"
              id="pd-signup"
              v-bind:to="{
                name: 'pd-signup',
                params: { id: playDate.playDateId },
              }"
              v-bind:key="playDate.playDateId"
              ><play-date-signup v-bind:playdate="playdate" />Click for <br>Details</router-link
            >
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import playDateService from "@/services/PlayDateService";

export default {
  data() {
    return {
      filter: {
        locationCity: "",
        locationZipcode: "",
        date: "",
        time: "",
        mateDescription: "",
        size: "",
        temperament: "",
        spayedNeutered: "",
      },
      id: -1,
      availablePlaydates: [], //pull playDates from backend and put in created
    };
  },
  computed: {
    
    filteredList() {
      let filteredPlayDates = this.availablePlaydates;
      if (this.filter.locationCity != "") {
        filteredPlayDates = filteredPlayDates.filter((playDate) => {
          return playDate.locationCity
            .toLowerCase()
            .includes(this.filter.locationCity.toLowerCase());
        });
      }
      if (this.filter.locationZipcode != "") {
        filteredPlayDates = filteredPlayDates.filter((playDate) => {
          return playDate.locationZipcode.toString().includes(this.filter.locationZipcode);
        });
      }
      if (this.filter.date != "") {
        filteredPlayDates = filteredPlayDates.filter((playDate) => {
          return playDate.meetingDate
            .toLowerCase()
            .includes(this.filter.date.toLowerCase());
        });
      }
      if (this.filter.time != "") {
        filteredPlayDates = filteredPlayDates.filter((playDate) => {
          return playDate.startTime
            .toLowerCase()
            .includes(this.filter.time.toLowerCase());
        });
      }
      if (this.filter.size != "") {
        filteredPlayDates = filteredPlayDates.filter((playDate) => {
          return playDate.size
            .toLowerCase()
            .includes(this.filter.size.toLowerCase());
        });
      }
      if (this.filter.temperament != "") {
        filteredPlayDates = filteredPlayDates.filter((playDate) => {
          return playDate.temperament
            .toLowerCase()
            .includes(this.filter.temperament.toLowerCase());
        });
      }
      if (this.filter.spayedNeutered != "") {
        filteredPlayDates = filteredPlayDates.filter((playDate) => {
          return playDate.spayedNeutered
            .toLowerCase()
            .includes(this.filter.spayedNeutered.toLowerCase());
        });
      }
      return filteredPlayDates;
    },
  },
  methods:{ 
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
        }
  },
  created() {

    this.id = this.$store.state.user.id; 
    playDateService
      .getAvailablePlayDates(this.id)
      .then((response) => {
        this.availablePlaydates = response.data;
      })
      .catch((error) => {
        if (error.response) {
          window.alert("Missing Play Dates");
        } else if (error.request) {
          window.alert("Could not reach service");
        }
      });
  },
};
</script>

<style scoped>
h1 {
  color: white;
  margin-left: 20px;
  font-size: 50px;
}
#main {
  background-color: rgb(2, 59, 109);
}
table {
  max-width: 100%;
  margin-left: auto;
  margin-right: auto;
}
th {
  
  font-size: 28px;
  background-color:  rgb(25, 149, 180);
  color:white;
  
}
input{
  font-family: 'Alegreya Sans', sans-serif;
  color:  rgb(2, 59, 109);
  font-size:18px;
}
.heading{
 
  font-size: 40px;
  color:white;
}

td {
  font-size: 20px;
}
#playDateInfo {
  margin-bottom: 30px;
}
td,
th {
  text-align: center;
  
}
tr:nth-child(even){
background-color: rgb(63, 182, 212)
}
tr:nth-child(odd){
background-color:  rgb(25, 149, 180)
}
table,
th,
td {
  border: 3px solid  rgb(2, 59, 109);
  border-collapse: collapse;
  
}
.link {
  color: white;
  text-decoration: none;
  color: inherit;
  font-size: 24px;
}


#sizeFilter{
  width:100px;
}
#dateFilter{
  width:125px;
}
#locationZipcodeFilter{
  width: 90px;
}
#spayedNeuteredFilter{
  width: 150px;
}
#pd-signup{
  font-weight: bold;
}
#timeFilter{
  width:175px;
  
}
#pick-play-date{
  width: 135px;
}
#temperamentFilter{
  width: 180px;
}
#ideal-playmate{
  width: 140px;
}

@media only screen and (max-width: 550px) {
}
</style>