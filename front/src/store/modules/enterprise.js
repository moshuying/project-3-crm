export default {
  namespaced: true,
  state: {
    curEnterprise: null,  //当前选中的企业
    advanceProps: null,  //获取企业的高级属性
   // roles: null,
   // routesConfig: null
  },
  getters: {
    curEnterprise: state => {
      return state.curEnterprise
    },
    // permissions: state => {
    //   if (!state.permissions) {
    //     try {
    //       const permissions = localStorage.getItem(process.env.VUE_APP_PERMISSIONS_KEY)
    //       state.permissions = JSON.parse(permissions)
    //       state.permissions = state.permissions ? state.permissions : []
    //     } catch (e) {
    //       console.error(e.message)
    //     }
    //   }
    //   return state.permissions
    // },
    // roles: state => {
    //   if (!state.roles) {
    //     try {
    //       const roles = localStorage.getItem(process.env.VUE_APP_ROLES_KEY)
    //       state.roles = JSON.parse(roles)
    //       state.roles = state.roles ? state.roles : []
    //     } catch (e) {
    //       console.error(e.message)
    //     }
    //   }
    //   return state.roles
    // },
    // routesConfig: state => {
    //   if (!state.routesConfig) {
    //     try {
    //       const routesConfig = localStorage.getItem(process.env.VUE_APP_ROUTES_KEY)
    //       state.routesConfig = JSON.parse(routesConfig)
    //       state.routesConfig = state.routesConfig ? state.routesConfig : []
    //     } catch (e) {
    //       console.error(e.message)
    //     }
    //   }
    //   return state.routesConfig
    // }
  },
  mutations: {
    setCurEnterprise (state, curEnterprise) {
      //state.curEnterprise = null;
      state.curEnterprise = curEnterprise
      //localStorage.setItem(process.env.VUE_APP_USER_KEY, JSON.stringify(user))
    },
    // setPermissions(state, permissions) {
    //   state.permissions = permissions
    //   localStorage.setItem(process.env.VUE_APP_PERMISSIONS_KEY, JSON.stringify(permissions))
    // },
    // setRoles(state, roles) {
    //   state.roles = roles
    //   localStorage.setItem(process.env.VUE_APP_ROLES_KEY, JSON.stringify(roles))
    // },
    // setRoutesConfig(state, routesConfig) {
    //   state.routesConfig = routesConfig
    //   localStorage.setItem(process.env.VUE_APP_ROUTES_KEY, JSON.stringify(routesConfig))
    // }
  }
}
