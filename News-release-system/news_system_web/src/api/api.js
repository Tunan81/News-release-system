import request from "@/utils/request";

// 获取新闻列表
export const getNewsList = data => {
    return request({
        method: 'POST',
        url: '/news/list',
        params: data
    })
}

// 获取热门新闻列表
export const getTopNewsList = data => {
    return request({
        method: 'POST',
        url: '/news/top',
        params: data
    })
}

// 获取新闻详情
export const getnew = data => {
    return request({
        method: 'GET',
        url: `/news/main/${data}`
    })
}
