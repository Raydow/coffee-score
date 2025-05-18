<script>
    import { onMount } from 'svelte';
    import { fetchUsers } from '../services/userService.js';
  
    let users = [];
    let error = '';
    let loading = true;
  
    async function loadUsers() {
      try {
        users = await fetchUsers();
      } catch (err) {
        error = err.message || 'Erro ao carregar usuários.';
      } finally {
        loading = false;
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
  </style>
  
  <h1>Bem-vindo à Efit!</h1>
  <p>Você está logado com sucesso.</p>
  
  <button class="train-btn" on:click={() => alert('Vamos treinar!')}>
    Treinar agora
  </button>
  
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
  