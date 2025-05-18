<script>
  import { push } from 'svelte-spa-router';
  import { AUTH_URL } from '../lib/api';
  import { onMount } from 'svelte';

  let name = '';
  let email = '';
  let password = '';
  let birthDate = '';
  let height = '';
  let weight = '';
  let gender = 'MALE';
  let remember = false;

  onMount(() => {
    const savedEmail = localStorage.getItem('saved_email');
    const savedPassword = localStorage.getItem('saved_password');
    if (savedEmail && savedPassword) {
      email = savedEmail;
      password = savedPassword;
      remember = true;
    }
  });

  async function handleRegister() {
    const payload = {
      name,
      email,
      password,
      birthDate,
      height: parseFloat(height),
      weight: parseFloat(weight),
      gender,
      userRole: 'USER',
      active: true
    };

    try {
      const response = await fetch(`${AUTH_URL}/register`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(payload)
      });

      if (!response.ok) {
        const error = await response.text();
        alert(`Erro ao cadastrar: ${error}`);
        return;
      }

      const data = await response.json();
      alert('Conta criada com sucesso!');
      console.log(data);

      if (remember) {
        localStorage.setItem('saved_email', email);
        localStorage.setItem('saved_password', password);
      } else {
        localStorage.removeItem('saved_email');
        localStorage.removeItem('saved_password');
      }

      push('/');
    } catch (err) {
      console.error(err);
      alert('Erro inesperado ao cadastrar.');
    }
  }

  function goBack() {
    push('/');
  }
</script>

<h1>Criar Conta</h1>

<form on:submit|preventDefault={handleRegister}>
  <div>
    <label for="name">Nome:</label><br />
    <input id="name" type="text" bind:value={name} required />
  </div>

  <div style="margin-top: 1rem;">
    <label for="email">Email:</label><br />
    <input id="email" type="email" bind:value={email} required />
  </div>

  <div style="margin-top: 1rem;">
    <label for="password">Senha:</label><br />
    <input id="password" type="password" bind:value={password} required />
  </div>

  <div style="margin-top: 1rem;">
    <label for="birthDate">Data de nascimento:</label><br />
    <input id="birthDate" type="date" bind:value={birthDate} required />
  </div>

  <div style="margin-top: 1rem;">
    <label for="height">Altura (em metros):</label><br />
    <input id="height" type="number" step="0.01" min="0" bind:value={height} required />
  </div>

  <div style="margin-top: 1rem;">
    <label for="weight">Peso (em kg):</label><br />
    <input id="weight" type="number" step="0.1" min="0" bind:value={weight} required />
  </div>

  <div style="margin-top: 1rem;">
    <label for="gender">Gênero:</label><br />
    <select id="gender" bind:value={gender} required>
      <option value="MALE">Masculino</option>
      <option value="FEMALE">Feminino</option>
    </select>
  </div>

  <div style="margin-top: 1rem;">
    <label>
      <input type="checkbox" bind:checked={remember} />
      Salvar informações de login
    </label>
  </div>

  <div style="margin-top: 1.5rem;">
    <button type="submit">Cadastrar</button>
    <button type="button" on:click={goBack} style="margin-left: 1rem;">Voltar</button>
  </div>
</form>
