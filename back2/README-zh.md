# Spring Boot API Seedling

![stars](https://img.shields.io/github/stars/Zoctan/spring-boot-api-seedling.svg?style=flat-square&label=Stars)
![license](https://img.shields.io/github/license/Zoctan/spring-boot-api-seedling.svg?style=flat-square)

[English](./README.md) | 简体中文

## 简介

本项目修改自：[spring-boot-api-project-seed](https://github.com/lihengming/spring-boot-api-project-seed)

原项目本身很简洁，已经能满足很多基本需求，在此感谢种子作者。

我根据需求继续添加了一些小功能，比如 API 的签名认证、调用文档、一些小工具等，所以就有了该 Seedling 项目。

添加的内容包括：
- Spring Cache：缓存
- Redis：缓存中间件
- Swagger3：API 文档展示
- Spring Security + JWT：对调用方签名认证
- Jasypt：加密配置
- 其他略

代码规范参考阿里巴巴 Java 开发手册，安装 Alibaba Java Coding Guidelines 插件。

风格规范使用 Google，安装 google-java-format 插件。

注解工具：Lombok，安装同名 Idea 插件。

## 版本

| 依赖         | 版本    |
|:-----------:|--------:|
| Java        | 1.8     |
| SpringBoot  | 2.3.5   |

## 快速开始

\# 克隆项目

git clone https://github.com/Zoctan/spring-boot-api-seedling.git

\# 配置代码生成器

对 test/java 包内的代码生成器 CodeGenerator 进行配置
导入 test/resources/sql 目录下的开发环境 dev 的数据库文件 *.sql

\# 根据表名生成代码

输入表名，运行 CodeGenerator.main() 方法，生成基础代码（观看[种子项目的快速演示视频](http://v.youku.com/v_show/id_XMjg1NjYwNDgxNg==.html?spm=a2h3j.8428770.3416059.1)）

\# last

对开发环境配置文件 application-dev.properties 进行配置，启动项目，Have Fun Too：)

## 技术选型&文档

1. Spring Boot（[种子项目作者的学习&使用指南](https://www.jianshu.com/p/1a9fd8936bd8) | [基础教程](http://blog.didispace.com/Spring-Boot%E5%9F%BA%E7%A1%80%E6%95%99%E7%A8%8B/)）
2. MyBatis（[官方中文文档](http://www.mybatis.org/mybatis-3/zh/index.html)）
3. MyBatis通用Mapper插件（[官方中文文档](https://mapperhelper.github.io/docs/)）
4. MyBatis PageHelper分页插件（[官方中文文档](https://pagehelper.github.io/)）
5. Druid Spring Boot Starter（[官方中文文档](https://github.com/alibaba/druid/tree/master/druid-spring-boot-starter/)）
6. FastJson（[官方中文文档](https://github.com/alibaba/fastjson/wiki/Quick-Start-CN) | [W3CSchool使用指南](https://www.w3cschool.cn/fastjson/fastjson-quickstart.html)）

## 相关项目

- [前端 Vue + 后端 Spring Boot 完全分离的用户角色管理模板](https://github.com/Zoctan/spring-boot-vue-admin)

## 更新记录

2020-11-09 更新 Swagger2 至 Swagger3，更新其他依赖版本。

2019-08-13 更换 Tomcat 容器为 Jetty，修复 RSA 密钥文件无法读取问题，添加文件上传控制器，更新其他依赖版本。

2018-11-29 配置改为 yml ，完善单元测试，更新其他依赖版本。

2018-07-21 增加 Jasypt 自定义配置和配置密码加密，Tomcat 打包，修改 RSA 工具和添加相应配置。

2018-07-15 增加 DTO 层，避免 DO 层被污染。

2018-07-11 添加了可自定义缓存过期时间的注解，修改了数据表 user 为 account。
