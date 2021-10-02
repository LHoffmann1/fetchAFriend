<template>
  <div id="main">
      <h1>Your Requests to Approve:</h1>
      <div id="tile">
      <div id="request-tile" v-for="request in requests" 
      v-bind:key="request.index"
      v-bind:request="request"
      v-bind:value="request"
       >
      <form id="info" v-on:submit.prevent="approvePlayDate(request.mateId, request.playDateId)">
        <b>Date: </b><div class="data"> {{changeDateFormat(request.playdate.meetingDate)}}</div> <br>
        <b>Start Time:</b><div class="data"> {{changeTimeFormat(request.playdate.startTime)}} </div> <br>
        <b>Play Time: </b><div class="data">{{request.playdate.duration}} minutes</div>  <br>
        <b>Location: </b><div class="data">{{request.playdate.locationStreetAddress}}</div> 
        <br>
        <h3>Potential Playmate:</h3>
        <b>Name: </b> <div class="data">{{request.mateName}} </div> <br>
        <b>Breed: </b><div class="data">{{request.mateBreed}}</div>  <br>
        <b>Size:</b> <div class="data">{{request.mateSize}} </div> <br>
        <b>Year of Birth:</b> <div class="data">{{request.mateBirthYear}} </div> <br>
        <b>Spayed/Neutered?</b> <div class="data">{{request.mateSpayedNeutered}} </div> <br>
        <b>Temperament: </b><div class="data">{{request.mateTemperament}} </div> <br>

      <button class="button-submit">Pick this Pup!</button>
     </form></div>
      </div>

  </div>

</template>
<script>
import playDateService from "@/services/PlayDateService";
export default {
    data(){
        return {
            id: -1,
            requests: [],
           
        }
    },
    created(){
        this.id = this.$store.state.user.id;

        playDateService.getMyRequestsToApprove(this.id).then((response)=>{
            this.requests = response.data;
        })
        .catch((err) => {
        console.error(err + " nothing returned");
      });

    },
    methods: {
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
      approvePlayDate(idMate, idPlayDate) {
      
        playDateService.updateRequest(idPlayDate, idMate)
        .then((response) => {
          if(response.status >= 200 && response.status < 300) {
            window.alert("Your playdate is conFURRmed!")
            this.$router.go();
          }
        })
        .catch((error) => {
          if (error.response) {
            window.alert("Bad Dog!");
          } else if (error.request) {
            window.alert("Could not reach service");
          }
        });
      },
       
        changeDateFormat(insertDate){
 let myArr= insertDate.split('-');
 return myArr[1]+"-"+myArr[2]+"-"+myArr[0];
    }

    }

}
</script>

<style scoped>
#request-tile {
  border: 5px white dotted;
  font-size: 30px;
  padding-left:20px;
  padding-bottom: 20px;
  background-color:  rgb(25, 149, 180);
  margin: 5px;
  width: 320px;
 
}
#tile{
  display:flex;
  flex-wrap: wrap;
}
h3{
  font-size: 34px;
}
#main{
  display: flex;
  flex-direction:column;
  margin-left: 20px;
}
#info{
  line-height: 30px;
  padding-top: 30px;
}
button {
  height: 30px;
  font-size: 18px;
  background-color: white;
  color: rgb(2, 59, 109);
  font-weight: bold;
  border: 5px white solid;
  margin-top:20px;
  font-family: 'Alegreya Sans', sans-serif;
}
h1{
  font-size:42px;
}
.data{
  font-size: 26px;
  display:inline;
  margin-left: 10px;
}
</style>