<template>
  <div id="main">
      <h1>Confirmed Playdates: </h1>
      <div id = "tile">
      <div id="upcoming-tile" v-for="playDate in playDates"
      v-bind:key="playDate.index"
      v-bind:playDate="playDate"
      v-bind:value="playDate">
        <b><div id="heading">{{playDate.userPetName}} & {{playDate.mateName}}</div></b><br>
         <div id="image"> <img id= "image-link" v-bind:src="playDate.hostImage"/> 
         <img id= "image-link" v-bind:src="playDate.mateImage"/> </div>
        <b>Date: </b><div class="data"> {{changeDateFormat(playDate.meetingDate)}} </div><br>
        <b>Start Time: </b> <div class="data">{{changeTimeFormat(playDate.startTime)}} </div><br>
        <b>Play Time:</b> <div class="data"> {{playDate.duration}} minutes </div><br>
        <b>Location: </b> <div class="data">{{playDate.locationStreetAddress}}<br>
        
       <div id = address> {{playDate.locationCity}}, {{playDate.locationZipcode}} </div> </div>
        <button id="cancelButton" v-on:click.prevent="cancelPlayDate(playDate.playDateId)">Cancel</button></div>
        <br>
      </div>
  </div>
</template>

<script>
import playDateService from "@/services/PlayDateService";
export default {
    data() {
        return{
            id: -1,
            playDates: [],
           
        }; 
    },
    created() {
        this.id = this.$store.state.user.id; 

        playDateService.getAllConfirmedPlayDates(this.id)
        .then((response) => {
            this.playDates = response.data;
        })
        .catch((error) => {
            if (error.response) {
                window.alert("Bad Dog!");
            } else if (error.request) {
                window.alert("Could not reach service");
            }
        });
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
        },
    
  
    cancelPlayDate(playDateId) {
      if (
        confirm(
          "Are you PAWSitive you want to cancel this playdate? This action cannot be undone."
        )
      ) {
         // let playDateId = this.playDate.playDateId;
          playDateService
            .cancelPlayDate(playDateId)
            .then((response) => {
              if (response.status >= 200 && response.status < 300) {
                window.alert("This playdate was cancelled");
                this.$router.go();
              }
            })
            .catch((error) => {
              if (error.response) {
                window.alert(
                  "Error cancelling playdate. Response received was '" +
                  error.response.statusText +
                  "'.");
              } else if (error.request) {
                window.alert("Server could not be reached.");
              } else {
                window.alert("Request could not be created.");
              }
            });
        
      }
    },

}
}

</script>

<style scoped>
#upcoming-tile {
    border: 5px white dotted;
  font-size: 30px;
  padding-left:20px;
  padding-bottom: 20px;
  padding-top: 40px;
     line-height: 30px;
   background-color: rgb(25, 149, 180);
     margin: 5px;
     width: 350px;
     
}
#main{
  display: flex;
  flex-direction:column;
  margin-left: 20px;
}
#tile{
    display:flex;
    flex-wrap: wrap;
}
h1{
  font-size:42px;
}
#heading{
  font-size:34px;
}
.data{
    margin-left: 10px;
    display:inline;
    font-size: 26px;
}
#address{
    margin-left: 125px;
}
button {
  height: 40px;
  font-size: 18px;
  background-color: white;
  color: rgb(2, 59, 109);
  font-weight: bold;
  border: 3px solid rgb(2, 59, 109);
  font-family: 'Alegreya Sans', sans-serif;
}
img{
  
  width: 150px;
  height:130px;
  
  border: 3px white solid;
}
#image :nth-child(2){
  margin-left:10px;
}
</style>