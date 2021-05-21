import {request, METHOD} from '@/utils/request'
import {CUSTOMER_FOLLOW_UP_HISTORY} from './api'

/**
 * 获取员工管理明细信息，分页获取
 * @param page {{size: number, page: number}}
 * @returns {Promise<AxiosResponse<T>>}
 */
export async function list(page ) {
    return request(CUSTOMER_FOLLOW_UP_HISTORY, METHOD.GET, page||{"page": 1, "size": 10})
}

/**
 * 删除一个员工管理明细信息
 * @param id {string|number}
 * @returns {Promise<AxiosResponse<T>>}
 */
export async function deleteItem(id) {
    return request(CUSTOMER_FOLLOW_UP_HISTORY + '/' + id, METHOD.DELETE)
}

/**
 * 获取单个员工管理明细的详细信息
 * @param id
 * @returns {Promise<AxiosResponse<T>>}
 */
export async function getDetail(id) {
    return request(CUSTOMER_FOLLOW_UP_HISTORY + '/' + id, METHOD.GET)
}

/**
 * 修改员工管理明细信息
 * @param object {{
  "comment": "string",
  "customerid": 0,
  "inputuser": 0,
  "id":0,
  "tracedetails": "string",
  "traceresult": 0,
  "tracetime": "2021-05-21T12:29:28.902Z",
  "tracetype": 0,
  "type": 0
}}
 * @returns {Promise<AxiosResponse<T>>}
 */
export async function update(object) {
    return request(CUSTOMER_FOLLOW_UP_HISTORY, METHOD.PUT, object)
}

/**
 * 添加员工管理明细信息
 * @param object {{
  "comment": "string",
  "customerid": 0,
  "inputuser": 0,
  "tracedetails": "string",
  "traceresult": 0,
  "tracetime": "2021-05-21T12:29:28.902Z",
  "tracetype": 0,
  "type": 0
}}
 * @returns {Promise<AxiosResponse<T>>}
 */
export async function add(object) {
    return request(CUSTOMER_FOLLOW_UP_HISTORY, METHOD.POST, object)
}

export const type ={
    1:"客户跟进历史",
    0:"潜在开发计划"
}
export const traceresult={
    3:"优",
    2:"中",
    1:"差"
}
