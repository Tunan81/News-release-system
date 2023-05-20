// mock.js 模拟数据
const Mock = require('mockjs')
const Random = Mock.Random

let newsList = []

for (let i = 0; i < 10; i++) {
    let news = {
        id: Random.increment(),
        title: Random.ctitle(20, 50),
        releaseTime: Random.datetime()
    }
    newsList.push(news)
}


Mock.mock('https://mockapi.eolinker.com/6cw39F8a4f8b1d77e98fa5cf3c3fd55dd5dffd07078a7e0/news', 'get', {
    'list|10': [{
        'id|+1': 1,
        'title': '@ctitle(20, 50)',
        'releaseTime': '@datetime()'
    }]
})

console.log(Mock.mock('https://mockapi.eolinker.com/6cw39F8a4f8b1d77e98fa5cf3c3fd55dd5dffd07078a7e0/news', 'get', {
    'list|10': [{
        'id|+1': 1,
        'title': '@ctitle(20, 50)',
        'releaseTime': '@datetime()'
    }]
}))
