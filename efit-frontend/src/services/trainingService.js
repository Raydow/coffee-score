import { TRAININGS_URL } from '../lib/api.js';

export async function startTraining(userId, trainingTypeName) {
  try {
    const token = localStorage.getItem('access_token');
    if (!token) {
      throw new Error('Usuário não autenticado. Token não encontrado.');
    }

    const res = await fetch(TRAININGS_URL, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${token}`
      },
      body: JSON.stringify({ userId, trainingTypeName })
    });

    if (!res.ok) {
      const errorText = await res.text();
      throw new Error(`Erro ao iniciar treino: ${res.status} ${errorText}`);
    }

    return await res.json();
  } catch (error) {
    console.error('startTraining error:', error);
    throw error;
  }
}
