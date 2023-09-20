import {request, METHOD} from '@/utils/request'
import {PRODUCTS} from './api'


export default function (){





}


/**
 * 新增或者保存产品
 * @returns {Promise<AxiosResponse<T>>}
 */
export async function addOrUpdata(object) {
    return request(PRODUCTS, METHOD.POST, object)
}



/**
 * 删除产品
 * @returns {Promise<AxiosResponse<T>>}
 */
export async function del(id) {
    return request(PRODUCTS+"/"+id, METHOD.DELETE)
}


/**
 * 分页获取产品
 * @param page {{size: number, page: number}}
 * @returns {Promise<AxiosResponse<T>>}
 */
export async function list(page ) {
    return request(PRODUCTS+"/list", METHOD.GET, page||{"page": 1, "size": 10})
}


