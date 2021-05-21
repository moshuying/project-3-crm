import {request, METHOD} from '@/utils/request'
import {CUSTOMER_HANDOVER} from './api'

/**
 * 获取员工管理明细信息，分页获取
 * @param page {{size: number, page: number}}
 * @returns {Promise<AxiosResponse<T>>}
 */
export async function list(page ) {
    return request(CUSTOMER_HANDOVER, METHOD.GET, page||{"page": 1, "size": 10})
}

/**
 * 删除一个员工管理明细信息
 * @param id {string|number}
 * @returns {Promise<AxiosResponse<T>>}
 */
export async function deleteItem(id) {
    return request(CUSTOMER_HANDOVER + '/' + id, METHOD.DELETE)
}

/**
 * 获取单个员工管理明细的详细信息
 * @param id
 * @returns {Promise<AxiosResponse<T>>}
 */
export async function getDetail(id) {
    return request(CUSTOMER_HANDOVER + '/' + id, METHOD.GET)
}

/**
 * 修改员工管理明细信息
 * @param object {{
  "customerid": 0,
  "newseller": 0,
  "oldseller": 0,
  "transreason": "string",
  "transtime": "2021-05-21T06:33:22.300Z",
  "transuser": 0
}}
 * @returns {Promise<AxiosResponse<T>>}
 */
export async function update(object) {
    return request(CUSTOMER_HANDOVER, METHOD.PUT, object)
}

/**
 * 添加员工管理明细信息
 * @param object {{
  "customerid": 0,
  "id": 0,
  "newseller": 0,
  "oldseller": 0,
  "transreason": "string",
  "transtime": "2021-05-21T06:33:22.300Z",
  "transuser": 0
}}
 * @returns {Promise<AxiosResponse<T>>}
 */
export async function add(object) {
    return request(CUSTOMER_HANDOVER, METHOD.POST, object)
}
