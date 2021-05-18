import {request, METHOD} from '@/utils/request'
import {EMPLOYEE} from './api'

/**
 * 获取角色信息，分页获取
 * @param page {{size: number, page: number}}
 * @returns {Promise<AxiosResponse<T>>}
 */
export async function list(page ) {
    return request(EMPLOYEE, METHOD.GET, page||{"page": 1, "size": 10})
}

/**
 * 添加角色信息
 * @param object {{
  "admin": 0,
  "age": 22,
  "dept": 2,
  "email": "c@c.c",
  "hiredate": "2021-05-16T01:09:17.045Z",
  "name": "testErT3",
  "password": "string",
  "roleIds": [4,5,8,9],
  "state": 0
}}
 * @returns {Promise<AxiosResponse<T>>}
 */
export async function add(object) {
    return request(EMPLOYEE, METHOD.POST, object)
}

/**
 * 删除一个角色信息
 * @param id {string|number}
 * @returns {Promise<AxiosResponse<T>>}
 */
export async function deleteItem(id) {
    return request(EMPLOYEE + '/' + id, METHOD.DELETE)
}

/**
 * 获取单个角色的详细信息
 * @param id
 * @returns {Promise<AxiosResponse<T>>}
 */
export async function getDetail(id) {
    return request(EMPLOYEE + '/' + id, METHOD.GET)
}

/**
 * 修改角色信息
 * @param object {{
  "id":1008,
  "admin": 0,
  "age": 22,
  "dept": 2,
  "email": "c@c.c",
  "hiredate": "2021-05-16T01:09:17.045Z",
  "name": "testErT3",
  "password": "string",
  "roleIds": [8,9,10,11,12],
  "state": 0
}}
 * @returns {Promise<AxiosResponse<T>>}
 */
export async function update(object) {
    return request(EMPLOYEE, METHOD.PUT, object)
}

