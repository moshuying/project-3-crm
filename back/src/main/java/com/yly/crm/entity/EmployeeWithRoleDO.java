package com.yly.crm.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmployeeWithRoleDO extends Employee{
    private String departmentName;
    String roleNames;
}
