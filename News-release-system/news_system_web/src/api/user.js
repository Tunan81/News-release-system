import request from '@/utils/request'

export default {
    /**
     * 1.登录
     * @param {*} param
     * @returns
     */
    login(param) {
        return request({
            url: '/user/login',
            method: 'post',
            data: param
        })
    },

    /**
     * 2.注册
     * @param {*} param
     * @returns
     */
    register(param) {
        return request({
            url: '/user/register',
            method: 'post',
            data: param
        })
    },

    /**
     * 找回密码
     * @param {*} param
     * @returns
     */
    findPassword(param) {
        return request({
            url: '/user/findPassword',
            method: 'post',
            data: param
        })
    }
}