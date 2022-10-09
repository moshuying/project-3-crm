import Cookie from 'js-cookie'
// import {LOGIN} from "@/services/api";
// 401拦截
// const resp401 = {
//   /**
//    * 响应数据之前做点什么
//    * @param response 响应对象
//    * @param options 应用配置 包含: {router, i18n, store, message}
//    * @returns {*}
//    */
//   onFulfilled(response, options) {
//     const {message} = options
//     if (response.code === 401) {
//       message.error('无此权限')
//     }
//     return response
//   },
//   /**
//    * 响应出错时执行
//    * @param error 错误对象
//    * @param options 应用配置 包含: {router, i18n, store, message}
//    * @returns {Promise<never>}
//    */
//   onRejected(error, options) {
//     const {message} = options
//     const {response} = error
//     if (response.status === 401) {
//       message.error('无此权限')
//     }
//     return Promise.reject(error)
//   }
// }
//
const resp403 = {
  onFulfilled(response, options) {
    const {message} = options
    if (response.code === 1000) {
      message.error('没有权限访问！')
    }
    return response
  },
  onRejected(error, options) {
    const {message} = options
    const {response} = error
    if (response.status === 403) {
      message.error('请求被拒绝！没有权限访问！')
    }
    return Promise.reject(error)
  }
}
const resp500 = {
  onFulfilled(response, options) {
    const {message} = options
    if (response.code === 500) {
      message.error('服务器错误')
    }
    return response
  },
  onRejected(error, options) {
    const {message} = options
    const {response} = error
    if (response.status === 500) {
      message.error('服务器错误')
    }
    return Promise.reject(error)
  }
}
const respA = {
  onFulfilled(response, options) {
    const {message} = options
    if (response.code === 500) {
      message.error('服务器错误')
    }
    return response
  },
  onRejected(error, options) {
    const {message} = options
    const {response} = error
    if (response && response.status === 500) {
      message.error('服务器错误')
    }else{
      message.error('严重，网络错误！')
    }
    return Promise.reject(error)
  }
}

const reqCommon = {
  /**
   * 发送请求之前做些什么
   * @param config axios config
   * @param options 应用配置 包含: {router, i18n, store, message}
   * @returns {*}
   */
  onFulfilled(config, options) {
    const {message} = options
    const { xsrfCookieName} = config
    if (options.router.currentRoute.fullPath !== '/login' && xsrfCookieName && !Cookie.get(xsrfCookieName)) {
      message.warning('认证 token 已过期，请重新登录')
      options.router.push('/login')
    }
    return config
  },
  /**
   * 请求出错时做点什么
   * @param error 错误对象
   * @param options 应用配置 包含: {router, i18n, store, message}
   * @returns {Promise<never>}
   */
  onRejected(error, options) {
    const {message} = options
    message.error(error.message)
    return Promise.reject(error)
  }
}

export default {
  request: [reqCommon], // 请求拦截
  response: [resp500,resp403,respA] // 响应拦截
}
