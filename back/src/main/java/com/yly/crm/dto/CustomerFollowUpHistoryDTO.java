package com.yly.crm.dto;

import com.yly.crm.entity.CustomerFollowUpHistory;
import lombok.Getter;
import lombok.Setter;

/**
 * 客户跟进历史分页查询模型。
 */
@Getter
@Setter
public class CustomerFollowUpHistoryDTO extends CustomerFollowUpHistory {
    private String inputName; // 跟進人姓名
    private String entName; // 企业名称 【可以不显示】
    private String customerName; // 联系人姓名
}
