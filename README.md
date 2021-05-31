
### 简介
这个项目是做个一个DDD的应用的一个demo，核心思想是CQRS，对命令进行严格的建模设计；对于读请求，按事务脚本方式编程。目的在于，延后对读请求的设计，能快速的开发需求，在这前提下并且保持着清晰的业务边界和业务模型迭代。


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
