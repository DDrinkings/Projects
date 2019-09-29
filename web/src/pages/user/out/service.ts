import request from 'umi-request'

export async function login(data: { username: string }) {
  return request('/api/login', {
    method: 'post',
    data,
  })
}
