<template>
  <div id="main">
      <h1 id="title">Your Pet DeTAILS: </h1>
      <div id="tile">
      <div id="pet-tile" v-for="pet in pets" 
      v-bind:key="pet.index"
      v-bind:pet="pet"
      v-bind:value="pet"
       >
        {{pet.name}}   
       
       <div id="image"> <img id= "image-link" v-bind:src="pet.image"
  width="200"/> </div></div>
      </div>

  </div>
</template>

<script>
import petService from "@/services/PetService";
export default {
    data() {
        return {
            id: -1, 
            pets: []
        }
    }, 
    created() {
        this.id = this.$store.state.user.id;

        petService.listUserPets(this.id).then((response)=>{
            this.pets = response.data;
        })
        .catch((err) => {
        console.error(err + " nothing returned");
      });
    }

}
</script>

<style scoped>
#pet-tile {
  border: 5px white dotted;
  font-size: 36px;
  padding-left:20px;
  padding-bottom: 20px;
  padding-top:20px;
  line-height: 30px;
  width: 250px;
  background-color:  rgb(25, 149, 180);
    margin: 5px;
    
    

}

#main{ 
  display: flex;
  flex-direction:column;
  margin-left: 20px;
}
#tile{
  display:flex;
  flex-wrap:wrap;
  
}
#image{
  margin-top: 10px;
  

}

h1{
  font-size:42px;
}

</style>