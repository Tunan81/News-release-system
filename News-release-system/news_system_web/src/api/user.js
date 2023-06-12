import request from '@/utils/request'


export const userRules = data => {
    return request({
        method: 'GET',
        url: `/user/${data}`
    })
}

export default {
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