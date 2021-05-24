package com.msy.plus.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CustomerHandoverList {
    private Integer id;
    private String customerName;
    private Date transTime;
    private String transUser;
    private String oldSeller;
    private String newSeller;
    private String transReason;
}
