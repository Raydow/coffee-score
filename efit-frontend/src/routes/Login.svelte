<script>
    import goto from 'svelte-spa-router'
  
    let email = ''
    let password = ''
  
    function login() {
      // Simula login e navega para Home
      console.log('Login:', email, password)
      goto('/home')
    }
  
    function goToRegister() {
      goto('/register')
    }
  </script>
  
  <div>
    <h2>Login</h2>
    <input type="email" placeholder="Email" bind:value={email} />
    <input type="password" placeholder="Senha" bind:value={password} />
    <button on:click={login}>Entrar</button>
    <button on:click={goToRegister}>Criar Conta</button>
  </div>
  