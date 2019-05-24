// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import axios from 'axios'
import qs from 'qs'

axios.default.withCredentials = true
Vue.config.productionTip = false
Vue.prototype.$http = axios
Vue.prototype.$qs = qs
axios.defaults.baseURL = '/'
import VueResource from 'vue-resource'
import MintUI from 'mint-ui'
import 'mint-ui/lib/style.css'

Vue.use(MintUI)
Vue.use(VueResource)
Vue.config.productionTip = false
Vue.use(ElementUI)
Vue.use(router)

const plugin = { install (Vue, options) {  Vue.component() } }
Vue.use(plugin)
new Vue({
    el: '#app',
    router,Element,
    components: { App },
    template: '<App/>'
  }
)
