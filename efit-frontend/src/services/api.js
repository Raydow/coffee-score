const API_DOMAIN = 'http://localhost:8080'
const API_BASE = '/api/v1'

const API_URL = `${API_DOMAIN}${API_BASE}`

export const endpoints = {
  register: `${API_URL}/auth/register`,
  login: `${API_URL}/auth/authenticate`
}
