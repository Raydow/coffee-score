<script>
  import Router from 'svelte-spa-router';

  import Home from './routes/Home.svelte';
  import About from './routes/About.svelte';
  import Register from './routes/Register.svelte';
  import NotFound from './routes/NotFound.svelte';

  const routes = {
    '/': Home,
    '/about': About,
    '/register': Register,
    '*': NotFound
  };
</script>

<nav>
  <a href="#/">Home</a>
  <a href="#/about">Sobre</a>
</nav>

<main>
  <Router {routes} />
</main>
