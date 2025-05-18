import { USERS_URL } from '../lib/api.js';

export async function fetchUsers() {
  try {
    const token = localStorage.getItem('access_token');
    if (!token) {
      throw new Error('Usuário não autenticado. Token não encontrado.');
    }

    const res = await fetch(USERS_URL, {
      headers: {
        'Authorization': `Bearer ${token}`
      }
    });

    if (!res.ok) {
      const errorText = await res.text();
      throw new Error(`Erro ao buscar usuários: ${res.status} ${errorText}`);
    }

    return await res.json();
  } catch (error) {
    console.error('fetchUsers error:', error);
    throw error;
  }
}
