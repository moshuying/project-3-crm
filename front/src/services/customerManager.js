import {request, METHOD} from '@/utils/request'
import {CUSTOMER_MANAGER} from './api'

/**
 * 获取员工管理明细信息，分页获取
 * @param page {{size: number, page: number}}
 * @returns {Promise<AxiosResponse<T>>}
 */
export async function list(page ) {
    return request(CUSTOMER_MANAGER, METHOD.GET, page||{"page": 1, "size": 10})
}

/**
 * 删除一个员工管理明细信息
 * @param id {string|number}
 * @returns {Promise<AxiosResponse<T>>}
 */
export async function deleteItem(id) {
    return request(CUSTOMER_MANAGER + '/' + id, METHOD.DELETE)
}

/**
 * 获取单个员工管理明细的详细信息
 * @param id
 * @returns {Promise<AxiosResponse<T>>}
 */
export async function getDetail(id) {
    return request(CUSTOMER_MANAGER + '/' + id, METHOD.GET)
}

/**
 * 修改员工管理明细信息
 * @param object {{
    "age": 18,
    "gender": 1,
    "id": 1,
    "inputtime": 1621539029000,
    "inputuser": 1,
    "job": 3,
    "name": "马云",
    "positivetime": 1621539062000,
    "qq": "100001",
    "seller": 1,
    "source": 17,
    "status": 1,
    "tel": "18888888888"
  }}
 * @returns {Promise<AxiosResponse<T>>}
 */
export async function update(object) {
    return request(CUSTOMER_MANAGER, METHOD.PUT, object)
}

/**
 * 添加员工管理明细信息
 * @param object {{
    "age": 18,
    "gender": 1,
    "inputuser": 1,
    "job": 3,
    "name": "马云",
    "positivetime": 1621539062000,
    "qq": "100001",
    "seller": 1,
    "source": 17,
    "status": 1,
    "tel": "18888888888"
  }}
 * @returns {Promise<AxiosResponse<T>>}
 */
export async function add(object) {
    return request(CUSTOMER_MANAGER, METHOD.POST, object)
}

export const statusMap = {
    "-2": "流失",
    "-1": "开发失败",
    "0": "潜在客户",
    "1": "正式客户",
    "2": "资源池客户",
}
