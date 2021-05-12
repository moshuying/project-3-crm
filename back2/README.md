# Spring Boot API Seedling

![stars](https://img.shields.io/github/stars/Zoctan/spring-boot-api-seedling.svg?style=flat-square&label=Stars)
![license](https://img.shields.io/github/license/Zoctan/spring-boot-api-seedling.svg?style=flat-square)

English | [简体中文](./README-zh.md)

## Introduction

Modified from: [spring-boot-api-project-seed](https://github.com/lihengming/spring-boot-api-project-seed)

The original project is very well and has been able to meet many basic needs. Thanks the seed author!

Seedling project:
I continued to add some small functions according to my needs, such as API signature authentication, API documents, some tools, etc.

The added content includes:
- Spring Cache: To cache
- Redis: Cache middleware
- Swagger3：API Doc
- Spring Security + JWT：Sign the caller authentication
- Jasypt：Encryption configuration
- etc.

The code specification refers to the《Alibaba Java Development》 and install the Alibaba Java Coding Guidelines plugin.

The style specification refers to Google and install google-java-format plugin.

Annotation tool: Lombok, install the Idea plugin of the same name.

## Version

| Dependencies | Version |
|:------------:|--------:|
| Java         | 1.8     |
| SpringBoot   | 2.3.5   |

## Start

\# Clone project

git clone https://github.com/Zoctan/spring-boot-api-seedling.git

\# Configure code generator

configure package test/java/.../CodeGenerator, import directory test/resources/sql/dev/*.sql file

\# Generate code from database schema

input table name, run CodeGenerator.main() method to generate basic code (watch [demo video](http://v.youku.com/v_show/id_XMjg1NjYwNDgxNg==.html?spm=a2h3j.8428770.3416059.1))

\# Last

configure the development environment configuration file application-dev.properties and start the project.

Have Fun Too：)

## Related project

- [前端 Vue + 后端 Spring Boot 完全分离的用户角色管理模板](https://github.com/Zoctan/spring-boot-vue-admin)

## Update log

2020-11-09 Update Swagger2 to Swagger3, update other dependencies version.

2019-08-13 Modify Tomcat to Jetty, read RSA file error have been fixed, add file upload controller, update dependencies version.

2018-11-29 Modify setting file format to yml, improve unit testing, update dependencies version.

2018-07-21 Add Jasypt custom setting and password encryption, add Tomcat pack, modify RSA tool.

2018-07-15 Add DTO to prevent DO pollution.

2018-07-11 Add annotation for customizable cache expiration time, modify the data table user to account.
