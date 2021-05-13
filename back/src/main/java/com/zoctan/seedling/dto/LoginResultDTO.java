package com.zoctan.seedling.dto;

import com.zoctan.seedling.core.dto.AbstractConverter;
import com.zoctan.seedling.entity.LoginResultDO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.*;

@Getter
@Setter
@Data
@Schema(name = "登录传输实体")
@EqualsAndHashCode(callSuper = true)
public class LoginResultDTO extends AbstractConverter<LoginResultDTO, LoginResultDO> implements Serializable {
    private static final long serialVersionUID = -12322384324L;

    @Schema(name = "账户令牌")
    private String token;

    @Schema(name = "过期时间")
    private String expireAt = new Date().toString();

    private List<Object> permissions = new ArrayList<>();
    private List<Object> roles = new ArrayList<>();

    @Schema(name = "用户信息")
    private Map<String, Object> user = new HashMap<>();

    public void setUserName(String name) {
        this.getUser().put("name", name);
    }

    public LoginResultDTO() {
//        注入虚拟数据
        Map<String, Object> permissions = new HashMap<>();
        permissions.put("id", "queryForm");
        permissions.put("operation", new String[]{"add", "edit"});
        this.permissions.add(permissions);

        this.user.put("address", "贺州市");
        this.user.put("avatar", "https://gw.alipayobjects.com/zos/rmsportal/WhxKECPNujWoWEFNdnJE.png");
        Map<String, Object> position = new HashMap<>();
        position.put("CN", "前端工程师 | 蚂蚁金服-计算服务事业群-REACT平台");
        position.put("HK", "前端工程師 | 螞蟻金服-計算服務事業群-REACT平台");
        position.put("US", "Front-end engineer | Ant Financial - Computing services business group - REACT platform");
        this.user.put("position", position);
    }

    @Override
    protected LoginResultDTO setDTO() {
        return this;
    }
}
