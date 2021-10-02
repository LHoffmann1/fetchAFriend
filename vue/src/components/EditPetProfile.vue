<template>
  <div>
    <form v-on:submit.prevent="updatePet">
      <label id="heading"> Which of Your Pets Would You Like to Edit: </label>
      <select
        class="form"
        v-model="selectedDogId"
        v-on:change="returnSelectedDog"
      >
        <option
          v-for="doggo in dogsByUserId"
          v-bind:key="doggo.petId"
          v-bind:value="doggo.petId"
          required
          selected
        >
          {{ doggo.name }}
        </option>
        >
      </select>
      <br /><br /><br />
      <label for="name">Name: </label>
      <input
        type="text"
        id="name"
        v-bind:name="dog.name"
        v-model="dog.name"
        required
      />
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

      <label for="temperament">Temperament: </label>
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
          Friendly: These dogs love everyone they encounter!!
        </option>
        <option value="protective">
          Protective: These human devoted pups and may prefer to socialize from
          a distance!
        </option>
        <option value="dominant">
          Dominant: These are the leaders of the pack and like to be in charge!
        </option>
        <option value="curious">
          Curious: These dogs are most interested in exploring their
          surroundings!
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
      <br />
      <input id="vaccination" type="checkbox" required />
      <label for="vaccination" id="vaccine"
        >By checking this box, you certify your pet is up to date on all legally
        required vaccinations.</label
      >
      <br /><br /><button class="button-submit">Submit</button>
      <button class="button-delete" v-on:click.prevent="removePet">Delete Pup</button>
      <button class="button-cancel">
        <router-link id="cancelBtn" to="/">Cancel</router-link>
      </button>
    </form>
  </div>
</template>

<script>
import petService from "@/services/PetService";
export default {
  data() {
    return {
      dogsByUserId: [],
      dog: {
        name: "",
        breed: "",
        birthYear: "",
        gender: "",
        size: "",
        spayedNeutered: "",
        temperament: "",
        petId: -1,
      },
      id: -1,
      selectedDogId: -1,
    };
  },
  methods: {
    returnSelectedDog() {
      for (let i = 0; i < this.dogsByUserId.length; i++) {
        if (this.dogsByUserId[i].petId == this.selectedDogId) {
          this.dog = this.dogsByUserId[i];
          break;
        }
      }
    },
    updatePet() {
      petService
        .updatePet(this.dog)
        .then((response) => {
          if (response.status >= 200 && response.status < 300) {
            window.alert("A round of apPAWS! Your pup has been updated!");
            this.$router.push("/");
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

    removePet() {
      if (
        confirm(
          "Are you PAWSitive you want to delete this pup? This action cannot be undone."
        )
      ) {
          petService
            .removePet(this.dog.petId)
            .then((response) => {
              if (response.status === 204) {
                window.alert("The pup was deleted");
                this.$router.push("/");
              }
            })
            .catch((error) => {
              if (error.response) {
                window.alert(
                  "Error removing puppy. Response received was '" +
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
  },

  created() {
    this.id = this.$store.state.user.id;

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
button {
  margin-left: 10px;
  height: 40px;
  font-size: 18px;
  background-color: white;
  color: rgb(2, 59, 109);
  font-weight: bold;
  border: 3px rgb(2, 59, 109) solid;
  font-family: "Alegreya Sans", sans-serif;
}
select {
  font-family: "Alegreya Sans", sans-serif;
  color: rgb(2, 59, 109);
  font-size: 20px;
  width: 300px;
  border: 3px solid rgb(2, 59, 109);
}
input {
  font-family: "Alegreya Sans", sans-serif;
  color: rgb(2, 59, 109);
  font-size: 18px;
  width: 290px;
  border: 3px solid rgb(2, 59, 109);
}
form {
  width: 700px;
  margin-right: auto;
  margin-left: auto;
  border: white 5px dotted;
  padding-top: 20px;
  padding-left: 20px;
  padding-bottom: 20px;
  background-image: url("../../images/PawPrints.png");
  border: 10px rgb(25, 149, 180) dotted;
  color: rgb(2, 59, 109);
}
.form {
  font-size: 30px;
}
#cancelBtn {
  color: rgb(2, 59, 109);
  text-decoration: none;
}
label {
  font-size: 24px;
  display: inline-block;
  width: 200px;
}
#heading {
  font-size: 30px;
  display: inline;
}
#vaccination {
  margin-left: -140px;
  display: inline;
}
#vaccine {
  margin-left: -135px;
  font-size: 18px;
  display: inline;
}
</style>