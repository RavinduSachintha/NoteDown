<script>
    import {Route} from 'svelte-router-spa';
    import TopHeaderPublic from '../components/top_header_public.svelte';

    export let currentRoute;
    const params = {}
</script>

<div class="app">
    <TopHeaderPublic/>
    <section class="section">
        <Route {currentRoute} {params}/>
    </section>
</div>
