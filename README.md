
### 简介
这个项目是做一个DDD的应用的一个demo，核心思想是CQRS，对命令进行严格的建模设计；对于读请求，按事务脚本方式编程。目的在于，延后对读请求的设计，能快速的开发需求，在这前提下并且保持着清晰的业务边界和业务模型迭代。

### 使用DDD能带来什么好处？
- 获得一个非常有用的领域模型
- 业务得到了更准确的定义和理解
- “领域专家们”可以为软件设计做出贡献
- 业务被集中在业务模型上，不会随着时间的流逝和人员的迭代而被遗忘
- 隔离业务逻辑和具体技术实现（业务会趋于稳定，技术会越来越不稳定）
- 更注重核心业务


### 工程解读
```
└── demo-adapter    对接前端的适配模块
└── demo-application-api    应用实例模块
└── demo-application-command    应用实例命令实现模块
└── demo-application-query  应用实例查询实现模块
└── demo-domain 业务领域模块
└── demo-infrastructure 基础模块模块
└── start   启动和配置模块
└── db   demo的sql脚本
```
![项目架构图](https://github.com/qzsang/ddd-demo-quick-version/blob/master/doc/p2.png)

### 架构和微服务的演进方式
![演进关系](https://github.com/qzsang/ddd-demo-quick-version/blob/master/doc/p3.png)

### PPT
https://github.com/qzsang/ddd-demo-quick-version/blob/master/doc/DDD-%E5%81%9A%E4%B8%9A%E5%8A%A1%E7%9A%84%E6%B2%89%E6%B7%80%E8%80%85.pptx
