<script>
    import {Route} from 'svelte-router-spa';
    import TopHeaderUser from '../components/top_header_user.svelte';

    export let currentRoute;
    const params = {}
</script>

<div class="app">
    <TopHeaderUser/>
    <section class="section">
        <Route {currentRoute} {params}/>
    </section>
</div>
