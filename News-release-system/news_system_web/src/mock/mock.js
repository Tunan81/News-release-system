import Mock from "mockjs";

// 模拟获取用户总数
Mock.mock("/api/usernums", "get", () => {
    return {
        code: 200,
        data: Mock.Random.natural(100, 1000),
    };
});

// 模拟获取文章总数
Mock.mock("/api/newsnums", "get", () => {
    return {
        code: 200,
        data: Mock.Random.natural(500, 2000),
    };
});

// 模拟获取H1总数
Mock.mock("/api/h1nums", "get", () => {
    return {
        code: 200,
        data: Mock.Random.natural(50, 500),
    };
});

// 模拟获取H2总数
Mock.mock("/api/h2nums", "get", () => {
    return {
        code: 200,
        data: Mock.Random.natural(100, 1000),
    };
});

// 模拟获取折线图数据
Mock.mock("/api/line", "get", () => {
    let data = [];
    for (let i = 0; i < 7; i++) {
        data.push(Mock.Random.natural(0, 20));
    }
    return {
        code: 200,
        data: data,
    };
});

// 模拟获取饼图数据
Mock.mock("/api/pie", "get", () => {
    let data = [];
    const names = [
        "简介",
        "章程",
        "专业介绍",
        "学院领导",
        "学院风纪",
        "学习建设",
        "考风考纪",
        "竞赛项目一览",
        "往期竞赛项目",
        "书院简介",
        "归德讲堂",
        "通知公告",
        "工作动态",
    ];
    for (let i = 0; i < names.length; i++) {
        data.push({
            value: Mock.Random.natural(1, 30),
            name: names[i],
        });
    }
    return {
        code: 200,
        data: data,
    };
});
