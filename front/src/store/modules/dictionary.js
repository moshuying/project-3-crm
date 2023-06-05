export default {
  namespaced: true,

  state: {
    dictionaryList: undefined,
    dictionaryDetails: null,
    curDictionary: null,
  },

  getters: {
    user: state => {
      if (!state.user) {
        try {
          const user = localStorage.getItem(process.env.VUE_APP_USER_KEY)
          state.user = JSON.parse(user)
        } catch (e) {
          console.error(e)
        }
      }
      return state.user
    },



  },

  mutations: {
    // setUser (state, user) {
    //   state.user = user
    //   localStorage.setItem(process.env.VUE_APP_USER_KEY, JSON.stringify(user))
    // },
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
