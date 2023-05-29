import Vue from 'vue'
import VueRouter from 'vue-router'
import store from "@/store";

Vue.use(VueRouter)

const routes = [
    {
        path: '/userHome',
        name: 'userHome',
        component: () => import('../views/front/UserHome.vue'),
        children: [
            {path: 'index', name: 'index', component: () => import('../views/front/page/index.vue')},
            {path: 'info', name: 'Info', component: () => import('../views/front/page/Info.vue')},
            {path: 'introduce', name: 'Introduce', component: () => import('../views/front/page/Introduce.vue')},
            {path: 'education', name: 'Education', component: () => import('../views/front/page/Education.vue')},
            {path: 'star', name: 'Star', component: () => import('../views/front/page/Star.vue')},
            {path: 'competition', name: 'Competition', component: () => import('../views/front/page/Competition.vue')},
            {path: 'history', name: 'History', component: () => import('../views/front/page/History.vue')},
            {path: 'services', name: 'Services', component: () => import('../views/front/page/Services.vue')},
            {path: 'news', name: 'News', component: () => import('../views/front/page/News.vue'),},
            {path: 'moreMessage', name: 'moreMessage', component: () => import('../views/front/page/moreMessage.vue')},
        ]
    },
    {
        path: '/MapView',
        name: 'MapView',
        component: () => import('../views/front/page/MapView.vue')
    },
    {
        path: '/login',
        name: 'Login',
        component: () => import('../views/Login.vue')
    },
    {
        path: '/register',
        name: 'Register',
        component: () => import('../views/Register.vue')
    },
    {
        path: '*',
        name: '404',
        component: () => import('../components/404.vue')
    },
    {
        path: '/RePwd',
        name: 'RePwd',
        component: () => import('../views/RePwd.vue')
    },
    {
        path:'/',
        redirect:'/userHome/index'　　//默认显示
    },
    {
        path: '/infoUs',
        name: 'infoUs',
        component: () => import('../views/front/InfoUs.vue')
    },
]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})
// 提供一个重置路由的方法
export const resetRouter = () => {
    router.matcher = new VueRouter({
        mode: 'history',
        base: process.env.BASE_URL,
        routes
    })
}

export const setRoutes = () => {
    const storeMenus = localStorage.getItem("menus");
    if (storeMenus) {
        // 拼装动态路由
        const manageRoute = {
            path: '/',
            name: 'Manage',
            component: () => import('../views/Manage.vue'),
            redirect: "/home",
            children: [
                {path: 'person', name: '', component: () => import('../views/Person.vue')},
            ]
        }
        const menus = JSON.parse(storeMenus)
        menus.forEach(item => {
            if (item.path) { // 当且仅当path不为空的时候才去设置路由
                let itemMenu = {
                    path: item.path.replace("/", ""),
                    name: item.name,
                    component: () => import('../views/' + item.pagePath + '.vue')
                }
                manageRoute.children.push(itemMenu)
            } else if (item.children.length) {
                item.children.forEach(item => {
                    if (item.path) {
                        let itemMenu = {
                            path: item.path.replace("/", ""),
                            name: item.name,
                            component: () => import('../views/' + item.pagePath + '.vue')
                        }
                        manageRoute.children.push(itemMenu)
                    }
                })
            }
        })
        // 获取当前的路由对象名称数组
        const currentRouteNames = router.getRoutes().map(v => v.name)
        if (!currentRouteNames.includes("Manage")) {
            // 动态添加到现在的路由对象中去
            router.addRoute(manageRoute)
        }
    }
}

// 重置的时候就去设置路由
setRoutes()

router.beforeEach((to, from, next) => {
    localStorage.setItem("currentPathName", to.name)  // 设置当前的路由名称
    store.commit("setPath") // 设置当前的路由路径
    // 未找到路由的情况
    if (!to.matched.length) {
        const storeMenus = localStorage.getItem("menus")
        if (storeMenus) {
            next("*")
        } else {
            // 跳回登录页面
            next("/login")
        }
    }
    if (to.path === "/userHome") { return next("/userHome/index")}
    // 其他的情况都放行
    next()
})

export default router
