import {request, METHOD} from '@/utils/request'
import {PRODUCTS} from './api'


/**
 * 方法主要给外部人员使用，添加报备商机。
 * @returns {Promise<AxiosResponse<T>>}
 */
export async function add(object) {
    return request(PRODUCTS, METHOD.POST, object)
}


/**
 * 分页获取产品
 * @param page {{size: number, page: number}}
 * @returns {Promise<AxiosResponse<T>>}
 */
export async function list(page ) {
    return request(PRODUCTS+"/list", METHOD.GET, page||{"page": 1, "size": 10})
}
