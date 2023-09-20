import Vue from 'vue'
import App from './App.vue'
import {initRouter} from './router'
import './theme/index.less'
import Antd from 'ant-design-vue'
import Viser from 'viser-vue'
import VueFormulate from '@braid/vue-formulate'
import '@/mock'
import store from './store'
import 'animate.css/source/animate.css'
import '@braid/vue-formulate/dist/snow.min.css'
import Plugins from '@/plugins'
import {initI18n} from '@/utils/i18n'
import bootstrap from '@/bootstrap'
import 'moment/locale/zh-cn'
import constant from '@/utils/constant'

const router = initRouter(store.state.setting.asyncRoutes)
const i18n = initI18n('CN', 'US')
Vue.use(VueFormulate)
Vue.use(Antd)
Vue.config.productionTip = false
Vue.use(constant)
Vue.use(Viser)
Vue.use(Plugins)

bootstrap({router, store, i18n, message: Vue.prototype.$message})

new Vue({
  router,
  store,
  i18n,
  render: h => h(App),
}).$mount('#app')
