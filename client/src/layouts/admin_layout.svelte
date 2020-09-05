<script>
    import {Route} from 'svelte-router-spa';
    import TopHeaderAdmin from '../components/top_header_admin.svelte';

    export let currentRoute;
    const params = {}
</script>

<div class="app">
    <TopHeaderAdmin/>
    <section class="section">
        <Route {currentRoute} {params}/>
    </section>
</div>
