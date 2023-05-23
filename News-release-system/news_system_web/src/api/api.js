import request from "@/utils/request";


// 获取新闻列表
export const getNewsList = data => {
    return request({
        method: 'POST',
        url: '/news/list',
        params: data
    })
}

export const getnew = data => {
    return request({
        method: 'GET',
        url: `/news/main/${data}`
    })
}