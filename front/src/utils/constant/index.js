/**
 * @name: index.js crm 系统全局常量管理
 * @author: Administrator
 * @date: 2023/8/30 10:21
 * @description：index.js
 * @update: 2023/8/30 10:21
 */

export  default {

    install(Vue,options){
        console.log("install:",options)

      Vue.prototype.$crmConst = {
          "monthMap":{
              title: '月',
              name: 'month',
          },
          "quarterMap":{
              title: '季度',
              name: 'quarter',
          },
          "yearMap":{
              title: '年',
              name: 'year',
          }

      }

    }

}