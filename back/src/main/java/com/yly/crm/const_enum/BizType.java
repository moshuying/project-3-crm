package com.yly.crm.const_enum;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum BizType {

   // niche 商机， index 线索 默认为niche
   NICHE(1,"商机"),
   INDEX(2,"线索");

   @EnumValue
   private final int code;

   @JsonValue
   private final String text;

   BizType(int code , String text){
      this.code = code;
      this.text = text;
   }
}
