# classroom_management_system
## 项目概述
项目通过一个开放的二维码作为系统的入口，通过手机扫描二维码进入系统。本系统使用shiro作为权限控制，将用户和超级用户分来。项目实现了普通用户的教室查询、教室预约、自我预约浏览等功能，超级用户拥有预约的审批权限，可以对普通用户的预约进行审批。

this is zhuo brance
### 接口：

返回值信息：

| code | data | massage | express |
| ------ | ------ | ------ | -----|
|  -1  |  ""  | 错误信息 |  失败   |
|  0   |  ""  | 错误信息 | 未登录  |
|  1   |  返回结果  | 错误信息 |  成功   |
|  2   |  ""  | 错误信息 |用户名不存在|
|  3   |  ""  | 错误信息 |密码错误 |
|  4   |  ""  | 错误信息 |权限不够 |


#### 注册
Method: POST

URL: /login/register   

```
body:
{
	"userId":100000,
	"username":"wangwu",
	"password":"123456",
	"sex":true,
	"roles":"student",
	"perms":" user:add"
}

response:
{
    "code": 1,
    "message": "success",
    "data": null
}
```

#### 登录
Method: POST

URL: /login/subLogin

```
body:
{
	"userId": 10000111,
	"password": "123456",
	"rememberMe": false
}

response:
{
    "code": 1,
    "message": "success",
    "data": null
}
```

#### 退出
Method: GET

URL: /login/logout

```
response:
{
    "code": 0,
    "message": "未登录",
    "data": null
}
```

#### 教室查询页面加载
Method: GET

URL: /cls/getRoomList

```
response:
{
    "code": 1,
    "message": "success",
    "data": "[{\"id\":1,\"roomLocal\":\"东南角\",\"roomNumber\":1,\"seatsNumber\":120,\"teachingBuilding\":\"教1\"},
                {\"id\":2,\"roomLocal\":\"东南角\",\"roomNumber\":2,\"seatsNumber\":120,\"teachingBuilding\":\"教1\"},
                {\"id\":3,\"roomLocal\":\"东南角\",\"roomNumber\":3,\"seatsNumber\":90,\"teachingBuilding\":\"教1\"},
                {\"id\":4,\"roomLocal\":\"东北角\",\"roomNumber\":1,\"seatsNumber\":90,\"teachingBuilding\":\"教2\"},
                {\"id\":5,\"roomLocal\":\"东北角\",\"roomNumber\":2,\"seatsNumber\":120,\"teachingBuilding\":\"教2\"}]"
}
```

#### 获得教学楼信息
Method: GET

URL: /cls/getTeachingBuilding

```
response:
{
    "code": 1,
    "message": "success",
    "data": "[\"教1\",\"教2\"]"
}
```

#### 通过条件获得教室信息
Method: GET

URL: /cls/getRoomByTIdAndSize

```
request:
teachingBuilding=0 (获得全部给0，具体的给具体教学楼id)

size=2 (获得全部给0,大于120座给1，小于120座给2)

response:
{
    "code": 1,
    "message": "success",
    "data": "[{\"id\":3,\"roomLocal\":\"东南角\",\"roomNumber\":3,\"seatsNumber\":90,\"teachingBuilding\":\"教1\"},
                {\"id\":4,\"roomLocal\":\"东北角\",\"roomNumber\":1,\"seatsNumber\":90,\"teachingBuilding\":\"教2\"}]"
}
```

#### 获得具体教室信息
Method: GET

URL: /cls/getRoomDetailById

```
request:
id=1 (数据库教室表中的主键)

response:
{
    "code": 1,
    "message": "success",
    "data": {
        "classroom": {
            "seatsNumber": 120,
            "roomLocal": null,
            "roomNumber": 1,
            "teachingBuilding": "教1",
            "id": 1,
            "multimediaEquipment": "电脑，投影仪"
        },
        "status": [
            {
                "time": 1,
                "status": "上课"
            },
            {
                "time": 3,
                "status": "上课"
            },
            {
                "time": 5,
                "status": "上课"
            },
            {
                "time": 4,
                "status": "排队"
            }
        ]
    }
}
```

#### 通过时间获得具体教室信息
Method: GET

URL: /cls/getRoomDetailByIdAndDate

```
request:
id=1 (数据库教室表中的主键)
date=20190306

response:
{
    "code": 1,
    "message": "success",
    "data": {
        "classroom": {
            "seatsNumber": 120,
            "roomLocal": null,
            "roomNumber": 1,
            "teachingBuilding": "教1",
            "id": 1,
            "multimediaEquipment": "电脑，投影仪"
        },
        "status": [
            {
                "time": 1,
                "status": "上课"
            },
            {
                "time": 3,
                "status": "上课"
            },
            {
                "time": 5,
                "status": "上课"
            },
            {
                "time": 4,
                "status": "排队"
            }
        ]
    }
}
```

#### 预约页面申请预约
Method: POST

URL: /rsv/insertInfo

```
request:
body:
{
	"roomId": 1,
	"date": 20190306,
	"time": 3,
	"userId": 10000111,
	"userName": "zhangsan",
	"reservationDesc": "计算机协会又来了"
}

response:
{
    "code": 1,
    "message": "success",
    "data": null
}
```

#### 学生预约界面
Method: GET

URL: /rsv/getStudentRSVById

```
request:

uid=10000111

response:
{
    "code": 1,
    "message": "success",
    "data": "[{\"date\":20190306,\"id\":1,\"isPass\":0,\"roomId\":0,\"roomNumber\":1,\"teachingBuilding\":\"教1\",\"time\":4,\"userId\":0},{\"date\":20190306,\"id\":3,\"isPass\":0,\"roomId\":0,\"roomNumber\":2,\"teachingBuilding\":\"教1\",\"time\":4,\"userId\":0},{\"date\":20190306,\"id\":5,\"isPass\":0,\"roomId\":0,\"roomNumber\":1,\"teachingBuilding\":\"教1\",\"time\":3,\"userId\":0}]"
}
```

#### 学生预约界面取消申请操作
Method: GET

URL: /rsv/cancelApplication

```
request:
id=1

response:
{
    "code": 1,
    "message": "success",
    "data": null
}
```

#### 审批界面
Method: GET

URL: /rsv/getApprovalDetail

```
request:

date=20190306

response:
{
    "code": 1,
    "message": "success",
    "data": "[{\"date\":20190306,\"id\":1,\"isPass\":0,\"reservationDesc\":\"计算机协会活动\",\"roomId\":0,\"roomNumber\":1,\"teachingBuilding\":\"教1\",\"time\":4,\"userId\":0,\"userName\":\"zhangsan\"},{\"date\":20190306,\"id\":2,\"isPass\":0,\"reservationDesc\":\"欢乐时光\",\"roomId\":0,\"roomNumber\":1,\"teachingBuilding\":\"教1\",\"time\":4,\"userId\":0,\"userName\":\"lisi\"},{\"date\":20190306,\"id\":3,\"isPass\":0,\"reservationDesc\":\"计算机协会活动\",\"roomId\":0,\"roomNumber\":2,\"teachingBuilding\":\"教1\",\"time\":4,\"userId\":0,\"userName\":\"zhangsan\"},{\"date\":20190306,\"id\":5,\"isPass\":0,\"reservationDesc\":\"计算机协会又来了\",\"roomId\":0,\"roomNumber\":1,\"teachingBuilding\":\"教1\",\"time\":3,\"userId\":0,\"userName\":\"zhangsan\"}]"
}
```

#### 审批界面操作
Method: GET

URL: /rsv/approvalOperation

```
request:
id=4&ope=2&desc=驳回
id=4&ope=1&desc=同意


response:
{
    "code": 1,
    "message": "success",
    "data": null
}
```

## 项目重点
1. 根据项目的地址生成二维码的入口。
2. 开发项目的接口供外网访问。
3. 教室的上课，预约逻辑是需要梳理清楚的，如果在程序中进行该逻辑的实现。
4. 数据库的设计，如何在满足数据库设计原则的基础上使用更简洁的库来支持项目功能。

## 项目难点
1. 需要使用ngrok将内外网进行一个穿透，使项目可以通过外网访问。
2. 数据库设计，需要尽量的满足数据的设计原则，并且数据库之间减少耦合。
3. 预约的逻辑实现是有多种多样的，本项目增加了难度，加入了课表系统，这使得本系统更基于现实情况。在此种情况下来实现预约是需要做大量的逻辑处理的。

## 前端设计
![image](https://note.youdao.com/yws/public/resource/b7f20240ec3000076c6964d090b97c75/xmlnote/7BF86AFED4C74468A13771AAFFEE938B/20560)

![image](https://note.youdao.com/yws/public/resource/b7f20240ec3000076c6964d090b97c75/xmlnote/0402DCDBFFDC41B693FCC3695FD6E73F/20562)

![image](https://note.youdao.com/yws/public/resource/b7f20240ec3000076c6964d090b97c75/xmlnote/98659FA19CB747B1BB9341401DC6E1F6/20561)

![image](https://note.youdao.com/yws/public/resource/b7f20240ec3000076c6964d090b97c75/xmlnote/78BC5253723846338AF633A5295A6864/20564)

![image](https://note.youdao.com/yws/public/resource/b7f20240ec3000076c6964d090b97c75/xmlnote/FE28B1A63D494849A9CC815B8FA9A16C/20568)

![image](https://note.youdao.com/yws/public/resource/b7f20240ec3000076c6964d090b97c75/xmlnote/2D2E64CBC6A940AB8F1BAFC6F945EF27/20566)
