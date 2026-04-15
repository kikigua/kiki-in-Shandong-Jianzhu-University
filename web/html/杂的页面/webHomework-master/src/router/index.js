import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '../views/HomeView.vue'
import myFavorite from "@/views/myFavorite";

Vue.use(VueRouter)
const routes = [
    {
        path: '/login',
        name: 'login',
        component: () => import(/* webpackChunkName: "about" */ '../components/login/login')
    },
    {
        path: '/',
        name: 'home',
        component: HomeView
    },
    {
        path: '/myFavorite',
        component: myFavorite
    },
    {
        path: '/about',
        name: 'about',
        component: () => import(/* webpackChunkName: "about" */ '../components/box/myBox.vue')
    },

]

const router = new VueRouter({
    routes
})

export default router
