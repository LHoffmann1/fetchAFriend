<template>
  <div id="app">
    <img  id = "logo-banner" src="../images/Banner.png" alt="logo"> 
    
    <div id="nav">
      <router-link class="link" id="home" v-bind:to="{ name: 'home' }">Home |</router-link>   <!--&nbsp;|&nbsp;-->
      <router-link class="link" v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''"> Logout</router-link> 
    </div>
    

    <router-view />
    <img  id = "logo-banner" src="../images/FaFFooter.png" alt="logo">
  </div>
</template>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Alegreya+Sans:wght@900&display=swap');

  #app {
  background-color: rgb(2, 59, 109);
  color: white; 
  font-family: 'Alegreya Sans', sans-serif;

  }
  .link {
    color: white;
    text-decoration: none; color: inherit;
    font-size: 24px;
    font-weight: bold;
    
    
  }
  #logo-banner{
   width: 100%;
  }
  #home{
    margin-left:20px;
  }

 
 
</style> 