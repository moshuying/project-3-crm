import {request, METHOD} from '@/utils/request'
import {PERMISSION} from './api'

/**
 * 获取权限信息，分页获取
 * @param page {{size: number, page: number}}
 * @returns {Promise<AxiosResponse<T>>}
 */
export async function list(page ) {
    return request(PERMISSION, METHOD.GET, page||{"page": 1, "size": 10})
}

/**
 * 删除一个权限信息
 * @param id {string|number}
 * @returns {Promise<AxiosResponse<T>>}
 */
export async function deleteItem(id) {
    return request(PERMISSION + '/' + id, METHOD.DELETE)
}

/**
 * 获取单个权限的详细信息
 * @param id
 * @returns {Promise<AxiosResponse<T>>}
 */
export async function getDetail(id) {
    return request(PERMISSION + '/' + id, METHOD.GET)
}

/**
 * 修改权限信息
 * @param object {{
  "expression": "string",
  "id": 0,
  "name": "string"
}}
 * @returns {Promise<AxiosResponse<T>>}
 */
export async function update(object) {
    return request(PERMISSION, METHOD.PUT, object)
}

/**
 * 添加权限信息
 * @param object {{
  "expression": "string",
  "name": "string"
}}
 * @returns {Promise<AxiosResponse<T>>}
 */
export async function add(object) {
    return request(PERMISSION, METHOD.POST, object)
}
