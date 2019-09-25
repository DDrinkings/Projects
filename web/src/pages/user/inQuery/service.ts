import request from 'umi-request'

export async function createIn(params: { amount: string, payType: string, modeType: string, comment: string, redComment: string }) {
    return request('/api/in', {
        params,
    })
}
