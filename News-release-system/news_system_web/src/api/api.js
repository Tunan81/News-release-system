import request from "@/utils/request";


/**
 * 获取新闻查看量
 * @returns {*}
 */
export const getViews = () => {
    return request({
        method: 'GET',
        url: '/news/getViews'
    })
}