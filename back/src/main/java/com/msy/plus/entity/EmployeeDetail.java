package com.msy.plus.entity;

import lombok.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmployeeDetail extends Employee{
    List<Long> roleIds;
}
