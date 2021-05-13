import {request, METHOD} from '@/utils/request'
import {DEPARTMENT} from './api'

/**
 * 获取部门信息，分页获取
 * @param page {{size: number, page: number}}
 * @returns {Promise<AxiosResponse<T>>}
 */
export async function list(page = {
    "page": 1,
    "size": 10
}) {
    return request(DEPARTMENT, METHOD.GET, page)
}

/**
 * 添加部门信息
 * @param object {{
  "name": "测试部门",
  "sn": "test department"
}}
 * @returns {Promise<AxiosResponse<T>>}
 */
export async function add(object) {
    return request(DEPARTMENT, METHOD.POST, object)
}

/**
 * 删除一个部门信息
 * @param id {string|number}
 * @returns {Promise<AxiosResponse<T>>}
 */
export async function deleteItem(id) {
    return request(DEPARTMENT + '/' + id, METHOD.DELETE)
}

/**
 * 获取单个部门的详细信息
 * @param id
 * @returns {Promise<AxiosResponse<T>>}
 */
export async function getDetail(id) {
    return request(DEPARTMENT + '/' + id, METHOD.GET)
}

/**
 *
 * @param object {{
   "id":1,
  "name": "name",
  "sn": "test department"
}}
 * @returns {Promise<AxiosResponse<T>>}
 */
export async function update(object) {
    console.log(METHOD.PATCH)
    return request(DEPARTMENT, METHOD.PATCH, object)
}

