//跨域代理前缀
// const API_PROXY_PREFIX='/api'
// const BASE_URL = process.env.NODE_ENV === 'production' ? process.env.VUE_APP_API_BASE_URL : API_PROXY_PREFIX
const BASE_URL = process.env.VUE_APP_API_BASE_URL
// const BASE_URL = process.env.VUE_APP_API_DEV_URL
module.exports = {
  BASE_URL,
  // LOGIN: `${BASE_URL}/login`,
  LOGIN: `${BASE_URL}/account/token`,
  // ROUTES: `${BASE_URL}/routes`,
  GOODS: `${BASE_URL}/goods`,
  GOODS_COLUMNS: `${BASE_URL}/columns`,
  DEPARTMENT:`${BASE_URL}/department`, // method CRUD
  ROLE:`${BASE_URL}/role`, // method CRUD
  PERMISSION:`${BASE_URL}/permission`, // method CRUD
  EMPLOYEE:`${BASE_URL}/employee`, // method CRUD
  DICTIONARY_CONTENTS:`${BASE_URL}/dictionary/contents`, // method CRUD
  DICTIONARY_DETAILS:`${BASE_URL}/dictionary/details`, // method CRUD
  CUSTOMER_MANAGER:`${BASE_URL}/customer/manager`, // method CRUD
  CUSTOMER_HANDOVER:`${BASE_URL}/customer/handover`, // method CRUD
}
