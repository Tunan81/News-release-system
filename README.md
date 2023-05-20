# News-release-system
## 一、数据库设计

1. 用户表

```sql
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `sex` varchar(10) NOT NULL,
  `hobbit` varchar(255) DEFAULT NULL,
  `degree` varchar(255) DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  `role` varchar(50) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;
```

2. 新闻表

```sql
CREATE TABLE `news` (
  `nid` int(11) NOT NULL AUTO_INCREMENT COMMENT '新闻',
  `title` varchar(255) NOT NULL COMMENT '标题',
  `content` longtext NOT NULL COMMENT '内容',
  `type` varchar(255) DEFAULT NULL COMMENT '类别',
  `release_time` datetime DEFAULT NULL COMMENT '发布时间',
  `user_id` int(11) NOT NULL COMMENT '发布人',
  `status` int(11) DEFAULT '0' COMMENT '状态（0-未审核，1-审核通过，2-审核通过）',
  `top` int(11) DEFAULT '0' COMMENT '是否头条（0-否，1-是）',
  `views` int(11) DEFAULT NULL COMMENT '游览量',
  `likes` int(11) DEFAULT NULL COMMENT '点赞数',
  `cover_image` varchar(255) DEFAULT NULL COMMENT '封面图片',
  PRIMARY KEY (`nid`),
  KEY `pubUser` (`user_id`),
  CONSTRAINT `pubUser` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
```

3. 新闻类型表

```sql
CREATE TABLE `news_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '新闻类型',
  `name` varchar(255) DEFAULT NULL COMMENT '类型名称', -- 类型名称，最大长度为 255，表示该类型的名字
  `createTime` datetime DEFAULT NULL COMMENT '创建时间', -- 类型创建时间，日期时间格式，记录该类型数据入库的时间
  `updateTime` datetime DEFAULT NULL COMMENT '更新时间', -- 类型更新时间，日期时间格式，记录该类型数据最后一次更新的时间
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
```

4.新闻分类表

```sql
CREATE TABLE `news_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '新闻分类',
  `name` varchar(255) DEFAULT NULL COMMENT '分类名称',
  `newsTypeId` int(11) DEFAULT NULL COMMENT '所属新闻类型id',
  `displayContent` varchar(255) DEFAULT NULL COMMENT '分类展示内容',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `updateTime` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  CONSTRAINT `news_category_ibfk_1` FOREIGN KEY (`newsTypeId`) REFERENCES `news_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
```





## 二、十个功能

✅1. 图片验证码。登录时防止恶意登录。

✅2. MD5加密。注册时密码要进行加密后存进数据库。MD5加密后的字符串一般是32位，如果密码那个字段不够的话在数据库改一下长度。

3. Ajax异步验证。注册时账号输完马上显示账号重复了。

✅4. 分页显示。查询结果分页显示。

✅5. 副文本。注册时的个人简介我们用的多行文本域，勉强可以，但是发布新闻时一篇新闻字数那么多，用多行文本域就显得非常小气了，所以要嵌入类似word的副文本来提高输入新闻内容的功能。

✅6. 文件上传。比如注册时可以上传头像（在数据库user表需要增加一个字段）；比如发布新闻时要带图片（也要增加相应的图片字段）。

✅7. 文件下载。登录成功后主页上可以搞个下载专区的链接，点击后可以下载到本地电脑上。

8. 短信验证码。比如，你密码忘记了，通过短信验证码找回。

9. 邮箱验证码。也是密码忘记了，通过发验证码到邮箱找回。

✅10. 批量操作。比如批量删除用户或新闻，就是在查询结果的画面多一列复选框，可以全选或者多选后进行批量删除操作。

------

## 三、角色

1. 超级管理员：拥有最高权限，可以管理所有模块和功能。

2. 管理员：可以管理新闻发布、审核、编辑、删除等功能。

3. 编辑：可以发布和编辑新闻，并提交审核。

4. 审核员：对新闻进行审核，决定是否通过。

   

5. 普通用户：可以浏览、搜索、评论新闻。

6. 访客：只能浏览新闻，不能进行其他操作。
