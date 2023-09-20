import {request, METHOD} from '@/utils/request'
import {CUS_ENTERPRISE} from "@/services/api";
/**
 * @name: customerEnterprise
 * @author: Administrator
 * @date: 2022/11/8 19:04
 * @description：customerEnterprise
 * @update: 2022/11/8 19:04
 */

export async function add(object) {
    return request(CUS_ENTERPRISE, METHOD.POST, object)
}

export async function list(params) {
    return request(CUS_ENTERPRISE+"/list", METHOD.GET,params)
}

