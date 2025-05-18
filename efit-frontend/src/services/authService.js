import { endpoints } from './api'

export async function register(userData) {
  const res = await fetch(endpoints.register, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(userData)
  })

  if (!res.ok) {
    const err = await res.json()
    throw new Error(err.message || 'Erro ao registrar')
  }

  return await res.json() // Deve conter access_token e refresh_token
}

export async function login(credentials) {
  const res = await fetch(endpoints.login, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(credentials)
  })

  if (!res.ok) {
    const err = await res.json()
    throw new Error(err.message || 'Erro ao autenticar')
  }

  return await res.json()
}
