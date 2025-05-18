<script>
  import { push } from 'svelte-spa-router';
  import { AUTH_URL } from '../lib/api';

  let email = '';
  let password = '';
  let remember = false;

  // Ao montar, tenta recuperar email e senha salvos
  // (atenção: salvar senha em localStorage não é seguro em produção!)
  onMount(() => {
    const savedEmail = localStorage.getItem('saved_email');
    const savedPassword = localStorage.getItem('saved_password');
    if (savedEmail && savedPassword) {
      email = savedEmail;
      password = savedPassword;
      remember = true;
    }
  });

  async function handleLogin() {
    const payload = {
      email,
      password
    };

    try {
      const response = await fetch(`${AUTH_URL}/authenticate`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(payload)
      });

      if (!response.ok) {
        const error = await response.text();
        alert(`Erro ao logar: ${error}`);
        return;
      }

      const data = await response.json();
      localStorage.setItem('access_token', data.access_token);
      localStorage.setItem('refresh_token', data.refresh_token);

      // Salva ou limpa email e senha conforme checkbox
      if (remember) {
        localStorage.setItem('saved_email', email);
        localStorage.setItem('saved_password', password);
      } else {
        localStorage.removeItem('saved_email');
        localStorage.removeItem('saved_password');
      }

      console.log('Login realizado com sucesso!');
      push('/efit-home');
    } catch (err) {
      console.error(err);
      alert('Erro inesperado no login.');
    }
  }

  function goToRegister() {
    push('/register');
  }

  import { onMount } from 'svelte';
</script>

<h1>Login</h1>

<form on:submit|preventDefault={handleLogin}>
  <div>
    <label for="email">Email:</label><br />
    <input id="email" type="email" bind:value={email} required />
  </div>

  <div style="margin-top: 1rem;">
    <label for="password">Senha:</label><br />
    <input id="password" type="password" bind:value={password} required />
  </div>

  <div style="margin-top: 1rem;">
    <label>
      <input type="checkbox" bind:checked={remember} />
      Salvar informações de login
    </label>
  </div>

  <div style="margin-top: 1.5rem;">
    <button type="submit">Logar</button>
    <button type="button" on:click={goToRegister} style="margin-left: 1rem;">Criar conta</button>
  </div>
</form>
