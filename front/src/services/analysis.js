import {METHOD, request} from '@/utils/request'
import {ANALYSIS} from './api'

export async function list(params) {
    return request(ANALYSIS, METHOD.POST, params)
}
