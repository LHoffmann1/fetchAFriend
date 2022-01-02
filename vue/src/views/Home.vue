<template>
  <div class="home">
    <aside>
      <br><br>
      <div id="name">{{ this.$store.state.user.username }} </div>
      <br>
      <div class = "action-link">
      <router-link class="link" id="add-pet" v-bind:to="{name:'add-pet'}">Add a Pet </router-link> <br> </div>    <!--&nbsp;|&nbsp; -->
      <div class = "action-link">
      <router-link class="link" id="create-playdate" v-bind:to="{name:'play-date-view'}">Create A Playdate</router-link><br></div> 
      <div class = "action-link">
      <router-link class="link" id="search-play-dates" v-bind:to="{name: 'play-date-search'}">Search Playdates</router-link></div>
      <div class = "action-link">
      <router-link class="link" id="pet-profile" v-bind:to="{name:'pet-profile'}">Edit Pet Profile</router-link> <br> </div>
     
    </aside>
    <div id="requests">
      <your-requests-to-approve /> </div>
      <div id="confirmed">
      <your-confirmed-play-dates /></div>
      <div id="waiting"><your-pending-play-dates /></div>
      <div id= "pets"><your-pets /></div>
      <div id="featured"><featured-pets/></div>
      
  </div>
</template>

<script>
import YourRequestsToApprove from '../components/YourRequestsToApprove.vue'
import YourConfirmedPlayDates from '../components/YourConfirmedPlayDates.vue'
import YourPendingPlayDates from '../components/YourPendingPlayDates.vue'
import YourPets from '../components/YourPets.vue'
import FeaturedPets from '../components/FeaturedPets.vue'
export default {
  name: "home",
  components: { YourRequestsToApprove, YourConfirmedPlayDates, YourPendingPlayDates, YourPets, FeaturedPets },
};
</script>

<style scoped>
  .home {
    display: grid;
    grid-template-columns: 300px 1fr 1fr;
    grid-template-areas: 
    "aside pets featured"
    "aside confirmed confirmed"
    "aside requests waiting"
    
    ;
    background-color: rgb(2, 59, 109);
    color: white; 
  }
  aside {
    grid-area: aside;
    color: white;
    border-right: white 5px dotted;
  }
  #requests{
    grid-area: requests;
    
    
  }
  #featured{
    grid-area: featured;
  }
  
  #confirmed{
    grid-area: confirmed;
    
  }
  #waiting{
    grid-area: waiting;
    
  }
  #pets{
    grid-area: pets;
  }
 
  .link{
    color: white;
    text-decoration: none; color: inherit;
    font-size: 32px;
    margin-left: 20px;
    grid-area: footer;
    
  }
  .action-link{
  margin-bottom:15px;
  }
  #name {
    font-size: 40px;
    margin-left: 20px;
    color: #a6dcef
  }

</style>
