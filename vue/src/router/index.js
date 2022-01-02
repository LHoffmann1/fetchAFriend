import Vue from 'vue'
import Router from 'vue-router'
import Home from '../views/Home.vue'
import Login from '../views/Login.vue'
import Logout from '../views/Logout.vue'
import Register from '../views/Register.vue'
import store from '../store/index'
import AddPet from '../views/AddPet.vue'
import PlayDateView from '../views/PlayDateView.vue'
import PlayDateSearch from '../views/PlayDateSearch.vue'
import PDSignup from '../views/PDSignup.vue'
import EditPetProfileView from '../views/EditPetProfileView.vue'


Vue.use(Router)

/**
 * The Vue Router is used to "direct" the browser to render a specific view component
 * inside of App.vue depending on the URL.
 *
 * It also is used to detect whether or not a route requires the user to have first authenticated.
 * If the user has not yet authenticated (and needs to) they are redirected to /login
 * If they have (or don't need to) they're allowed to go about their way.
 */

const router = new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home, 
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/login",
      name: "login",
      component: Login,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/logout",
      name: "logout",
      component: Logout,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/register",
      name: "register",
      component: Register,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/pet/add",
      name: "add-pet",
      component: AddPet,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/play-date/add/",
      name: "play-date-view",
      component: PlayDateView,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/play-date/available/",
      name: "play-date-search",
      component: PlayDateSearch,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/play-date/signup/:id",
      name: "pd-signup",
      component: PDSignup,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/petProfile",
      name: "pet-profile",
      component: EditPetProfileView,
      meta: {
        requiresAuth: true
      }
    }
  ],

  scrollBehavior() {
    return {x: 0, y:500}
  }
})

router.beforeEach((to, from, next) => {
  // Determine if the route requires Authentication
  const requiresAuth = to.matched.some(x => x.meta.requiresAuth);

  // If it does and they are not logged in, send the user to "/login"
  if (requiresAuth && store.state.token === '') {
    next("/login");
  } else {
    // Else let them go to their next destination
    next();
  }
});

export default router;
