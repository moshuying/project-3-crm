package com.msy.plus.dto;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AnalysisQuery {
    private Integer page=1;
    private Integer size=10;
    private String name="";
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date startTime;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date endTime;
    /**
     * groupType
     * 1 员工
     * 2 年
     * 3 月
     * 4 日
     */
    private int groupType;
}
