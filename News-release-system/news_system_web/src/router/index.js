import Vue from 'vue'
import VueRouter from 'vue-router'
import store from "@/store";

Vue.use(VueRouter)

const routes = [
    {
        path: '/userHome',
        name: 'userHome',
        component: () => import('../views/user/UserHome.vue'),
        children: [
            {path: 'index', name: 'index', component: () => import('../views/user/page/index.vue')},
            {path: 'info', name: 'Info', component: () => import('../views/user/page/Info.vue')},
            {path: 'introduce', name: 'Introduce', component: () => import('../views/user/page/Introduce.vue')},
            {path: 'education', name: 'Education', component: () => import('../views/user/page/Education.vue')},
            {path: 'star', name: 'Star', component: () => import('../views/user/page/Star.vue')},
            {path: 'competition', name: 'Competition', component: () => import('../views/user/page/Competition.vue')},
            {path: 'history', name: 'History', component: () => import('../views/user/page/History.vue')},
            {path: 'services', name: 'Services', component: () => import('../views/user/page/Services.vue')},
            {path: 'news', name: 'News', component: () => import('../views/user/page/News.vue'),},
            {path: 'moreMessage', name: 'moreMessage', component: () => import('../views/user/page/moreMessage.vue')},
        ]
    },
    {
        path: '/MapView',
        name: 'MapView',
        component: () => import('../views/user/page/MapView.vue')
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
        path: '/',
        redirect: '/userHome/index'
    },
    {
        path: '/infoUs',
        name: 'infoUs',
        component: () => import('../views/user/page/InfoUs.vue')
    },
    {
        path: '/test',
        name: 'test',
        component: () => import('../views/test.vue')
    }
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
            // 当且仅当path不为空的时候才去设置路由
            if (item.path) {
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


// to 将要访问的路径
// from 代表从哪个路径跳转而来
// next 是一个函数，表示放行
//     next()  放行    next('/login')  强制跳转
router.beforeEach((to, from, next) => {
    // 设置当前的路由名称
    localStorage.setItem("currentPathName", to.name)
    // 设置当前的路由路径
    store.commit("setPath")
    // 未找到路由的情况
    if (!to.matched.length) {
        // 获取本地存储的菜单
        const storeMenus = localStorage.getItem("menus")
        if (storeMenus) {
            next("*")
        } else {
            // 跳回登录页面
            next("/login")
        }
    }
    next()
})

export default router
