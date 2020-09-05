import PublicLayout from './layouts/public_layout.svelte';
import AdminLayout from './layouts/admin_layout.svelte';
import UserLayout from './layouts/user_layout.svelte';
import SignIn from './pages/sign_in.svelte';
import Home from './pages/home.svelte';
import Category from './pages/category.svelte';

function isAdmin() {
    //check if user is admin and returns true or false
    return true;
}

function isUser() {
    //check if user is admin and returns true or false
    return true;
}

const routes = [
    {name: '/', redirectTo: 'home'},
    {name: 'home', component: Home, layout: PublicLayout},
    {name: 'sign-in', component: SignIn, layout: PublicLayout},
    {
        name: 'admin', component: AdminLayout, onlyIf: {guard: isAdmin, redirect: '/sign-in'},
        nestedRoutes: [
            {name: 'index', redirectTo: '/admin/home'},
            {name: 'home', component: Home},
            {name: 'category', component: Category}
        ]
    },
    {
        name: 'user', component: UserLayout, onlyIf: {guard: isUser, redirect: '/sign-in'},
        nestedRoutes: [
            {name: 'index', redirectTo: '/user/home'},
            {name: 'home', component: Home},
            {name: 'category', component: Category}
        ]
    },
];

export {routes}
