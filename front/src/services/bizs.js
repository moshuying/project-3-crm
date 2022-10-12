import {request, METHOD} from '@/utils/request'
import {BIZS} from './api'


/**
 * 方法主要给外部人员使用，添加报备商机。
 {
  "bizDesc": "string",
  "bizEntName": "string",
  "bizStage": "string",
  "contactEntId": 0,
  "contactId": 0,
  "contactName": "string",
  "contactTel": "string",
  "createTime": "2022-10-11T01:06:36.218Z",
  "customerEntId": 0,
  "employeeId": 0,
  "employeeName": "string",
  "id": 0,
  "productDesc": "string",
  "productId": 0,
  "productName": "string"
}
 * @returns {Promise<AxiosResponse<T>>}
 */
export async function add(object) {
    return request(BIZS, METHOD.POST, object)
}
