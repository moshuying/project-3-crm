import {request, METHOD} from '@/utils/request'
import {ROLE} from './api'

/**
 * 获取角色信息，分页获取
 * @param page {{size: number, page: number}}
 * @returns {Promise<AxiosResponse<T>>}
 */
export async function list(page ) {
    return request(ROLE, METHOD.GET, page||{"page": 1, "size": 10})
}

/**
 * 添加角色信息
 * @param object {{
        "name": "市场经理",
        "permission": "1",
        "sn": "Market Manager"
    }}
 * @returns {Promise<AxiosResponse<T>>}
 */
export async function add(object) {
    return request(ROLE, METHOD.POST, object)
}

/**
 * 删除一个角色信息
 * @param id {string|number}
 * @returns {Promise<AxiosResponse<T>>}
 */
export async function deleteItem(id) {
    return request(ROLE + '/' + id, METHOD.DELETE)
}

/**
 * 获取单个角色的详细信息
 * @param id
 * @returns {Promise<AxiosResponse<T>>}
 */
export async function getDetail(id) {
    return request(ROLE + '/' + id, METHOD.GET)
}

/**
 * 修改角色信息
 * @param object {{
        "id": 4,
        "name": "市场经理",
        "permission": "1",
        "sn": "Market Manager"
    }}
 * @returns {Promise<AxiosResponse<T>>}
 */
export async function update(object) {
    return request(ROLE, METHOD.PUT, object)
}

