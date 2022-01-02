<template>
  <div id="main">
      <h1>Pending Host Approval: </h1>
      <div id="tile">
      <div id="pending-tile" v-for="request in requests" 
      v-bind:key="request.index"
      v-bind:request="request"
      v-bind:value="request"
      >
        <b><div> {{request.mateName}} is waiting to hear back from {{request.hostName}}</div></b><br>
        <div id="image"> <img id= "image-link" v-bind:src="request.mateImage"/> </div>
        <b>Date: </b><div class="data"> {{changeDateFormat(request.playdate.meetingDate)}}</div> <br>
        <b>Start Time: </b> <div class="data">{{changeTimeFormat(request.playdate.startTime)}}</div> <br>
       <b> Play Time:</b> <div class="data">{{request.playdate.duration}} minutes</div> <br>
       <b> Location:</b><div class="data"> {{request.playdate.locationStreetAddress}}</div> 
        <br>
      </div></div>
  </div>
</template>

<script>
import playDateService from "@/services/PlayDateService";
export default {
    data() {
        return {
            id: -1,
            requests: []
        }
    },
    created() {
        this.id = this.$store.state.user.id;

        playDateService.getPendingPlayDates(this.id).then((response)=>{
            this.requests = response.data;
        })
        .catch((err) => {
        console.error(err + " nothing returned");
      });

    },
      methods:{
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
        changeDateFormat(insertDate){
 let myArr= insertDate.split('-');
 return myArr[1]+"-"+myArr[2]+"-"+myArr[0];
    }

}
    }


</script>

<style scoped>
#pending-tile {
    border: 5px white dotted;
  font-size: 30px;
  padding-left:20px;
  padding-bottom: 20px;
   line-height: 30px;
   background-color:rgb(25, 149, 180);
     margin: 5px;
     width: 300px;
     padding-top: 30px;
}
#main{
  display: flex;
  flex-direction:column;
  margin-left: 20px;
}
#tile{
  display: flex;
  flex-wrap: wrap;
}
h1{
  font-size:42px;
}
.data{
  font-size: 26px;
  display:inline;
  margin-left: 10px;
}
img{
  width:150px;
  border: white 3px solid; 
}

</style>