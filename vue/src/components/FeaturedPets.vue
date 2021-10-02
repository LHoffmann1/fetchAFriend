<template>
<div id = "main">
    <h1>Featured Pups</h1>
    <div id ="tile">
  <div id="photo"><img id = "featured-pet" src="../../images/FeaturedPups/Pup5.png"></div></div>
  </div>
</template>

<script>
export default {

}
</script>

<style>
#main{
    display: flex;
    flex-direction:column;

}
h1{
    font-size: 42px;
}
#tile{
    display: flex;
    
    
}
#photo{
    display:flex;
    
    border: 10px dotted rgb(25, 149, 180);

}

</style>