package com.yly.crm.const_enum;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

public enum BizResources {
    baobei(1,"报备"),
    website(2,"网站"),
    activity(3,"活动"),
    other(4,"其他");


    @EnumValue
    private final int code;

    @JsonValue
    private final String text;

    BizResources(int code , String text){
        this.code = code;
        this.text = text;
    }
}
