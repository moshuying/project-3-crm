package com.yly.crm.entity;

import lombok.Data;

import java.util.Date;
import java.util.Map;

@Data
public class LoginResultDO {
    private String token;
    private String expireAt = new Date().toString();
    private Map permissions;
    private Object roles;
    private Object position;
    private Map user;
}
