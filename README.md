<center>

# project-vue2-CRM 客户资源管理系统(www.yliyun.com)

  
<img align="right" src='/front/src/assets/img/logo.png' />
  
[![Build Status](https://travis-ci.org/moshuying/project-3-crm.svg?branch=main)](https://travis-ci.org/moshuying/project-3-crm)
[![Commitizen friendly](https://img.shields.io/badge/commitizen-friendly-brightgreen.svg)](http://commitizen.github.io/cz-cli/) [![Join the chat at https://gitter.im/墨抒颖/project-3-crm](https://badges.gitter.im/墨抒颖/project-3-crm.svg)](https://gitter.im/墨抒颖/project-3-crm?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)
 
 ![GitHub language count](https://img.shields.io/github/languages/count/moshuying/project-3-crm) ![GitHub search hit counter](https://img.shields.io/github/search/moshuying/project-3-crm/1) ![GitHub code size in bytes](https://img.shields.io/github/languages/code-size/moshuying/project-3-crm) ![GitHub repo size](https://img.shields.io/github/repo-size/moshuying/project-3-crm) 
  
  ![GitHub closed issues](https://img.shields.io/github/issues-closed/moshuying/project-3-crm) ![GitHub closed pull requests](https://img.shields.io/github/issues-pr-closed/moshuying/project-3-crm) ![GitHub](https://img.shields.io/github/license/moshuying/project-3-crm)


# 简述

[sql文件包含在/mysql文件夹内](https://github.com/moshuying/project-3-crm/blob/main/mysql)

<a href="https://www.msy.plus/discover/" target="_blank">
在线演示(向下翻页就有)
</a>
</center>


- 前端使用 [vue-antd-admin](https://github.com/iczer/vue-antd-admin) 
- [项目文档地址](https://iczer.gitee.io/vue-antd-admin-docs/advance/authority.html#%E9%A1%B5%E9%9D%A2%E6%9D%83%E9%99%90) 
- 后台使用 [spring-boot-api-seedling](https://github.com/Zoctan/spring-boot-api-seedling) 

<h1>把项目拷贝下来后，导入`mysql/dev.sql`到`crm`数据库</h1>

<h1>数据库配置的在`back/src/main/resources/application-dev.yml`下，默认账户密码都是`root`。使用`crm`数据库</h1>


# 需求说明书

### 商机（有明确的产品购买意向知道购买什么产品）

> （线索：客户有意向推荐或者购买系列的产品，但是还需要长时间的沟通，并且需要机会推荐，需要更多的确定的东西）

1、  分类：线索或者商机  （可以修改）【是否立项】
2、  来源：渠道报备、官网互联网、 自有商机 【（如果是渠道，需要选择渠道信息才能完成报备）【分享录入】】继续完成报备
3、  预购产品：（选择分类）
4、  商机描述（desc）
5、  预计成交时间 （必填）
6、  商机预算金额 （选填）
7、  客户信息+联系人信息 【如果是报备：（企业信息+联系人信息）】【要填写】
8、  商机阶段（）- 不填（系统自动管理）
9、  商机动作：作废（延期）（输单）【录入超过3个月后提醒进行商机动作】【不填跟进时候才有】

绑定员工： 商机负责销售， 来源录入，分配；


### 关联报备信息（报备管理策略，系统自动执行）

1、商机关联
2、报备方信息
3、保护时间（自动根据阶段计算）
5、阶段推荐（初次录入、讲解、测试、POC、商务期）

###  项目，确认意向或者意向合同后确定为项目

1、立项
2、开始与结束时间
3、交付负责人
4、售后服务负责人
6、关联客户信息
7、保存渠道信息
8、项目阶段： 交付阶段，售后阶段，脱保阶段

### 商机漏斗
目标：（周期、商机个数、阶段转换对比，最终成交），根据历史计算出转化率；
针对单个用户、所有用户、部门用户形成对比；

### 商机增长对比
销售与销售之间的对比（部门与部门之间的对比）
周期与周期之间的柱状图（添加市场活动到周期中）


### 跟进周期（）
