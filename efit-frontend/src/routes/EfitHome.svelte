<script>
    import { onMount } from 'svelte';
    import { fetchUsers } from '../services/userService.js';
    import { startTraining } from '../services/trainingService.js';
    import { TrainingType } from '../constants/trainingTypes.js';
  
    let users = [];
    let error = '';
    let loading = true;
    let successMessage = '';
  
    async function loadUsers() {
      try {
        users = await fetchUsers();
      } catch (err) {
        error = err.message || 'Erro ao carregar usuários.';
      } finally {
        loading = false;
      }
    }
  
    async function handleTraining() {
      successMessage = '';
      error = '';
  
      try {
        if (users.length === 0) throw new Error('Nenhum usuário disponível.');
        const training = await startTraining(users[0].id, TrainingType.BALANCED);
        successMessage = `Treino iniciado: ${training.trainingTypeName} às ${new Date(training.startedAt).toLocaleTimeString()}`;
      } catch (err) {
        error = err.message || 'Erro ao iniciar treino.';
      }
    }
  
    onMount(() => {
      loadUsers();
    });
  </script>
  
  <style>
    .train-btn {
      background-color: #3f51b5;
      color: white;
      border: none;
      padding: 0.8rem 1.5rem;
      font-size: 1.1rem;
      font-weight: 600;
      border-radius: 6px;
      cursor: pointer;
      transition: background-color 0.3s ease;
      margin-top: 1.5rem;
    }
  
    .train-btn:hover {
      background-color: #303f9f;
    }
  
    ul.user-list {
      margin-top: 1.5rem;
      padding-left: 1.2rem;
    }
  
    ul.user-list li {
      margin-bottom: 0.4rem;
    }
  
    .error {
      color: red;
      margin-top: 1rem;
    }
  
    .success {
      color: green;
      margin-top: 1rem;
    }
  </style>
  
  <h1>Bem-vindo à Efit!</h1>
  <p>Você está logado com sucesso.</p>
  
  <button class="train-btn" on:click={handleTraining}>
    Treinar agora
  </button>
  
  {#if successMessage}
    <p class="success">{successMessage}</p>
  {/if}
  
  {#if loading}
    <p>Carregando usuários...</p>
  {:else if error}
    <p class="error">{error}</p>
  {:else if users.length === 0}
    <p>Nenhum usuário encontrado.</p>
  {:else}
    <ul class="user-list">
      {#each users as user}
        <li><strong>{user.name}</strong> — {user.email}</li>
      {/each}
    </ul>
  {/if}
  