import request from '@/utils/request'

export default {
    /**
     * 1.获取请求权限
     * @returns
     */
    getRequestCode(email) {
        return request({
            url: '/common/code/request',
            method: 'post',
            data: {
                email: email
            }
        })
    },

    /**
     * 2.获取邮箱验证码
     * @param {*} email
     * @returns
     */
    getEmailCode(email, code) {
        let param = {}
        param['email'] = email
        param['code'] = code
        return request({
            url: '/common/code/email',
            method: 'post',
            data: param
        })
    }
}