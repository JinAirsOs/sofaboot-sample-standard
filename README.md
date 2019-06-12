###
sofa-boot官方项目sofaboot-samples/sofaboot-sample-standard文件夹下标准示例项目，不同的是，可以脱离sofa-boot
独立运行。更改了pom依赖，使其脱离sofaboot文件夹，更改了依赖数据库为mysql数据库。
## 快速入门
本文档旨在演示如何在 SOFABoot 多模块中使用数据源，使用 mysql 内存数据库，执行了简单插入、查询、删除的数据库操作。项目的目录结构划分如下：
```text
sofaboot-sample-standard
│
├── sofa-app-server
│   ├── app (sofaboot后端模块）
│
├── sofa-app-client 
│   ├── src(react前端模块)
    
```
在这里不详述如何发布引用 JVM 服务，可以参见其他演示工程。这里演示如何运行该 Demo. 在该工程中暴露了4个 Rest 服务：
- `localhost:8080/getOne`：获取一个student
- `localhost:8080/update`: 更新student
- `localhost:8080/add`: 新增student
- `localhost:8080/queryAll`: 查询所有student
##安装步骤
- java8
- docker-compose up
- docker ps找到mysql容器
- 登入mysql容器新建test database `docker exec -it some-mysql /bin/bash`
- `mysql -u root -p`
输入123456
- `>create database test;`
- 打开浏览器访问localhost:9090
##新增功能
- 服务注册zookeeper rpc注册中心,可以跨项目互相调用。
- mysql数据库支持替换h2
- 利用spring-security登陆验证sofa后端和react交互过程。
- 单点的cronjob例子。
- 健康检查模块 详见`http://localhost:8080/actuator/health`
- sofa，rpc，rest发布模式例子，xml和annotation的发布模式都有
- sofaboot的业务分层划分，common-dal为数据库相关，common-util为公共地盘工具包，common-service-facade为facade
定义层，biz为facade实现层，用于实现对内微服务，web为web后端，用于实现对外带有验证的web服务。
