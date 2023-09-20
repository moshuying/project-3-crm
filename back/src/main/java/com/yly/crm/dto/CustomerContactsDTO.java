package com.yly.crm.dto;

import com.yly.crm.entity.CustomerContacts;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerContactsDTO extends CustomerContacts {
    /**
     * 所属企业id;
     */
    private Integer entId;

    /**
     * 所属企业名字；
     */
    private String entName;

    public CustomerContactsDTO(CustomerContacts customerContacts) {
    }
}
