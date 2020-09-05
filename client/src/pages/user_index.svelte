<script>
    import {Route} from 'svelte-router-spa';
    import {routes} from '../routes';

    export let currentRoute;
    const params = {}
</script>

<Route {currentRoute} {params}/>
