import request from '@/utils/request'

export const getMinTitle = data => {
    return request({
        method: 'GET',
        url: `/newsCategory/list/${data}`
    })
}