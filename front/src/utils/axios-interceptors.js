import Cookie from 'js-cookie'

const respError = {
  onFulfilled(response, options) {

    console.log("onFulfilled res:",response)

    const {message} = options
    if (response.code === 500) {
      message.error('服务器错误')
    }
    return response
  },
  onRejected(error, options) {
    const {message} = options
    const {response} = error

    console.log("onRejected res:",response)

    if (response.status !== 200 && response.data) {
       errMsg(response.data , message)
    }else{
      message.error('resp500,服务器错误')
    }
    return Promise.reject(error)
  }
}


function  errMsg(data , message){
  switch(data.code)
  {
    case 1000:
      message.error('权限不足,'+data.message)
      break;
    case 2000:
      message.error('查询失败,'+data.message)
      break;
    case 2001:
      message.error('保存失败,'+data.message)
      break;
    case 2002:
      message.error('查询失败,'+data.message)
      break;
    case 2003:
      message.error('更新失败,'+data.message)
      break;
    case 2004:
      message.error('删除失败,'+data.message)
      break;
    case 4001:
      message.error('数据库异常,'+data.message)
      break;
    case 4002:
      message.error('认证异常,'+data.message)
      break;
    case 4003:
      message.error('验证异常,'+data.message)
      break;
    default:
      message.error('未知错误,'+data.message)
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
  response: [respError] // 响应拦截
}
