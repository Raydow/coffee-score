<script>
  import Router from 'svelte-spa-router';

  import Home from './routes/Home.svelte';
  import About from './routes/About.svelte';
  import Register from './routes/Register.svelte';
  import EfitHome from './routes/EfitHome.svelte';
  import NotFound from './routes/NotFound.svelte';

  const routes = {
    '/': Home,
    '/about': About,
    '/register': Register,
    '/efit-home': EfitHome,
    '*': NotFound
  };
</script>

<style>
  nav {
    background-color: #1e1e2f;
    padding: 0.75rem 1.5rem;
    display: flex;
    justify-content: flex-end;
    gap: 1.5rem;
    box-shadow: 0 2px 8px rgb(0 0 0 / 0.1);
  }

  nav a {
    color: #cfd8dc;
    text-decoration: none;
    font-weight: 600;
    padding: 0.5rem 0.8rem;
    border-radius: 5px;
    transition: background-color 0.25s ease, color 0.25s ease;
  }

  nav a:hover {
    background-color: #3f51b5;
    color: white;
  }

  nav a.logoff {
    background-color: #e53935;
    color: white;
  }

  nav a.logoff:hover {
    background-color: #b71c1c;
  }
</style>

<nav>
  <a href="#/" class="logoff">Logoff</a>
  <a href="#/about">Sobre</a>
</nav>

<main>
  <Router {routes} />
</main>
