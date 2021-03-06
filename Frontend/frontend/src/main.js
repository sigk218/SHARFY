import Vue from 'vue'
import App from './App.vue'
import Router from 'vue-router'
import routes from './routes'
import store from './store'
import vuetify from './plugins/vuetify';
import Vuetify from 'vuetify/lib'
const firebase = require('firebase/app')
require('firebase/storage') 
import firebaseconfig from './plugins/firebaseconfig'
import infiniteScroll from 'vue-infinite-scroll'
// import ImageUploader from 'vue-image-upload-resize'

const originalPush = Router.prototype.push;
Router.prototype.push = function push(location) {
  window.scrollTo(0,0)
  return originalPush.call(this, location).catch(err => err)
}

Vue.use(Router)
Vue.use(Vuetify)
// Vue.use(ImageUploader)
firebase.initializeApp(firebaseconfig)
Vue.use(infiniteScroll)


const router = new Router({
  routes,
  mode: 'history',
})

new Vue({
  router,
  store,
  vuetify,
  render: h => h(App)
}).$mount('#app')

export default router