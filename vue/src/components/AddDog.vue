<template>
  <div id="main">
    <div id="form">
      <h1>Tell Us About Your Dog:</h1>
      <form v-on:submit.prevent="saveDog">
        <label for="name">Name: </label>
        <input type="text" id="name" v-model="dog.name" required />
        <br />
        <br />

        <label for="breed">Breed: </label>
        <select id="breed" v-model="dog.breed" required>
          <option
            v-for="breed in this.$store.state.breeds"
            v-bind:key="breed"
            v-bind:breed="breed"
            v-bind:value="breed"
            selected
          >
            {{ breed }}
          </option>
        </select>
        <br />
        <br />

        <label for="birthYear">Year of Birth: </label>
        <input
          type="number"
          min="2000"
          max="current"
          step="1"
          id="birthYear"
          v-model="dog.birthYear"
          required
        />
        <br />
        <br />

        <label for="gender">Gender: </label>
        <select id="dogGender" v-model="dog.gender" required>
          <option value="Male" selected>Male</option>
          <option value="Female" selected>Female</option>
        </select>
        <br />
        <br />

        <label for="size">Approximate size: </label>
        <select id="dogSize" v-model="dog.size" required>
          <option value="extra small" selected>
            Extra Small (ex. Chihuahua, Maltese, Yorkshire Terrier)
          </option>
          <option value="small" selected>
            Small (ex. Beagle, French Bulldog, Boston Terrier)
          </option>
          <option value="medium" selected>
            Medium (ex. Border Collie, Corgi, Spaniel)
          </option>
          <option value="large" selected>
            Large (ex. Lab, Golden Retriever, Australian Shepherd)
          </option>
          <option value="extra large" selected>
            Extra Large (ex. Great Dane, Saint Bernard, Newfoundland)
          </option>
        </select>
        <br />
        <br />

        <label for="spayedNeutered">Spayed/Neutered? </label>
        <select
          id="spayedNeuteredSelection"
          v-model="dog.spayedNeutered"
          required
        >
          <option value="yes" selected>Yes</option>
          <option value="no" selected>No</option>
        </select>
        <br />
        <br />

        <label for="temperament"
          >Temperament:
        </label>
        <select
          name="temperaments"
          id="temperaments"
          v-model="dog.temperament"
          required
        >
          <option value="energetic">
            High Energy: These dogs can chase a ball for hours!
          </option>
          <option value="timid">
            Timid: Once these pups feel comfortable, they make great playmates!
          </option>
          <option value="friendly">
            Friendly: These dogs love everyone they encounter!
          </option>
          <option value="protective">
            Protective: These human devoted pups and may
            prefer to socialize from a distance!
          </option>
          <option value="dominant">
            Dominant: These are the leaders of the pack and like to be in
            charge!
          </option>
          <option value="curious">
            Curious: These dogs are most interested in exploring their surroundings!
          </option>
          <option value="relaxed">
            Laid Back: These pups are content to lounge around and soak in the
            sun!
          </option>
          <option value="submissive">
            Submissive: These dogs are happy to let others take the lead!
          </option>
        </select>
        <br />
        <br />
        <!-- <input type="file" @change>
<button @click ="onUpload">Upload</button> -->
        <input id="vaccination" type="checkbox" required />
        <label for="vaccination" id="vaccine"
          >By checking this box, you certify your pet is up to date on all
          legally required vaccinations.</label
        >

        <br />
        <br />
        <button class="button-submit">Submit</button>
        <button class="button-cancel">
        <router-link id="cancelBtn" :to="{ name: 'home' }">Cancel</router-link></button>
      </form>
    </div>
  </div>
</template>

<script>
import petService from "@/services/PetService";
//import pupPicService from "@/services/PupPicService";

export default {
  data() {
    return {
      selectedFile: null,
      dog: {
        name: "",
        breed: "",
        birthYear: "",
        gender: "",
        size: "",
        spayedNeutered: "",
        temperament: "",
        image: "",
      },
     
    };
  },
  //created(){
  //   const breeds = this.$store.state.breeds
  // },
  methods: {
    // onFileSelected(event) {
    //     this.selectedFile= event.target.files[0]
    // },
    // onUpload() {
    //     const fd= new FormData();
    //     fd.append('image', this.selectedFile, this.selectedFile.name)
    //     axios.post('', fd
    //     .then((respsone) => {
    //     //     if(response.status === 201) {
    //     //         this.$router.push("/") //need to update once landing page is created
    //     //     }
    //     // }).catch((error) => {
    //     //     if(error.response) {
    //     //         window.alert('Bad Request');
    //     //     }
    //     //     else if (error.request) {
    //     //         window.alert('Could not reach service');
    //     //     }
    //     // })
    //     // }
  //
    saveDog() {
      let pupImage = this.$store.state.photos[Math.floor(Math.random() * 18)]
  
      const newDog = {
        name: this.dog.name,
        breed: this.dog.breed,
        birthYear: this.dog.birthYear,
        gender: this.dog.gender,
        size: this.dog.size,
        spayedNeutered: this.dog.spayedNeutered,
        temperament: this.dog.temperament,
        image: pupImage //this will now be random dog photo from within assets folder
      }

      petService
        .createPet(newDog)
        .then((response) => {
          if (response.status === 201) {
            window.alert("Bow-wow! Your dog has been added!");
            this.$router.push('/');
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
  }
  }

</script>

<style scoped>
#main {
  background-color: rgb(2, 59, 109);
  color: white;
  padding-bottom: 50px;
}
#form{
width: 725px;
margin-right: auto;
  margin-left: auto;
  border: white 5px dotted;
  background-image: url("../../images/PawPrints.png");  
  border: 10px rgb(25, 149, 180) dotted;
  color:rgb(2, 59, 109);
  
}
form {
  font-size: 24px;
  margin-left: 20px; 

}
label{
  display: inline-block;
  width: 200px;
  
} 
h1{
    margin-left: 10px;
}
input{
  font-size: 18px;
  width: 290px;
  border: 3px solid rgb(2, 59, 109);
  font-family: 'Alegreya Sans', sans-serif;
  color:rgb(2, 59, 109);
  
}
select{
  font-size:20px;
  width: 300px;
  border: 3px solid rgb(2, 59, 109);
  font-family: 'Alegreya Sans', sans-serif;
  color:rgb(2, 59, 109);
}
button {
  height: 40px;
  font-size: 18px;
  background-color: white;
  color: rgb(2, 59, 109);
  font-weight: bold;
  border: 5px white solid;
  border: 3px solid rgb(2, 59, 109);
  font-family: 'Alegreya Sans', sans-serif;
}
#cancelBtn{
   height: 30px;
  font-size: 18px;
  background-color: white;
  color: rgb(2, 59, 109);
  font-weight: bold;
  border: 5px white solid;
  text-decoration: none;
}
.button-submit{
    margin-right: 10px;
    margin-bottom: 25px;
}
#vaccine{
  font-size: 18px;
  margin-left: -135px;
  text-align: justify;
  width: 270px;
  display: inline;
  
}
#vaccination{
 margin-left: -140px;
 display: inline;
}

</style>