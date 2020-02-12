 # mall-swarm
 ##项目介绍
 `mall-swarm` 是一套微服务商城，采用Spring cloud、SpringBoot、Mybaits、Elasticsearch等核心技术搭建
 ##组织结构
``` lua
mall
├── mall-common -- 工具类及通用代码模块
├── mall-mbg -- MyBatisGenerator生成的数据库操作代码模块
├── mall-security -- 封装SpringSecurity+JWT的安全认证的模块
├── mall-registry -- 基于Eureka的微服务注册中心
├── mall-config -- 基于Spring Cloud Config的微服务配置中心
├── mall-gateway -- 基于Spring Cloud Gateway的微服务API网关服务
├── mall-admin -- 后台管理系统服务
├── mall-search -- 基于Elasticsearch的商品搜索系统服务
├── mall-front -- Pc端商城系统服务
└── mall-demo -- 微服务远程调用测试服务
```


### 后端技术

| 技术             | 说明                 | 官网                                                 |
| ---------------- | -------------------- | ---------------------------------------------------- |
| Spring Cloud     | 微服务框架           | https://spring.io/projects/spring-cloud              |
| Spring Boot      | 容器+MVC框架         | https://spring.io/projects/spring-boot               |
| Spring Security  | 认证和授权框架       | https://spring.io/projects/spring-security           |
| MyBatis          | ORM框架              | http://www.mybatis.org/mybatis-3/zh/index.html       |
| MyBatisGenerator | 数据层代码生成       | http://www.mybatis.org/generator/index.html          |
| PageHelper       | MyBatis物理分页插件  | http://git.oschina.net/free/Mybatis_PageHelper       |
| Swagger-UI       | 文档生产工具         | https://github.com/swagger-api/swagger-ui            |
| Elasticsearch    | 搜索引擎             | https://github.com/elastic/elasticsearch             |
| RabbitMq         | 消息队列             | https://www.rabbitmq.com/                            |
| Redis            | 分布式缓存           | https://redis.io/                                    |
| MongoDb          | NoSql数据库          | https://www.mongodb.com/                             |
| Docker           | 应用容器引擎         | https://www.docker.com/                              |
| Druid            | 数据库连接池         | https://github.com/alibaba/druid                     |
| OSS              | 对象存储             | https://github.com/aliyun/aliyun-oss-java-sdk        |
| MinIO            | 对象存储             | https://github.com/minio/minio                       |
| JWT              | JWT登录支持          | https://github.com/jwtk/jjwt                         |
| LogStash         | 日志收集             | https://github.com/logstash/logstash-logback-encoder |
| Lombok           | 简化对象封装工具     | https://github.com/rzwitserloot/lombok               |
| Seata            | 全局事务管理框架     | https://github.com/seata/seata                       |
| Portainer        | 可视化Docker容器管理 | https://github.com/portainer/portainer               |
| Jenkins          | 自动化部署工具       | https://github.com/jenkinsci/jenkins                 |



### 前端技术

| 技术       | 说明                  | 官网                           |
| ---------- | --------------------- | ------------------------------ |
| Vue        | 前端框架              | https://vuejs.org/             |
| Vue-router | 路由框架              | https://router.vuejs.org/      |
| Vuex       | 全局状态管理框架      | https://vuex.vuejs.org/        |
| Element    | 前端UI框架            | https://element.eleme.io/      |
| Axios      | 前端HTTP框架          | https://github.com/axios/axios |
| v-charts   | 基于Echarts的图表框架 | https://v-charts.js.org/       |

