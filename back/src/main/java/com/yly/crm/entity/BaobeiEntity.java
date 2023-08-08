package com.yly.crm.entity;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import com.mybatisflex.core.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author : jiuyuehe in Administrator
 * 2023/8/8 14:48
 * @description :
 **/

@Table("baobei")
@Accessors(chain=true)
@Data(staticConstructor = "create")
@EqualsAndHashCode(callSuper=false)
public class BaobeiEntity extends Model<BaobeiEntity> {

    @Id(keyType = KeyType.Auto)
    private Long id;
    private Long productId;
    private String productName;
    private String customerName;
    private String projectDesc;
    private Long userId;
    private String userName;
    private Date createAt;
}
