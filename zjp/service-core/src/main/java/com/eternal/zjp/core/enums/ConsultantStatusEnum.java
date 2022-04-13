package com.eternal.zjp.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Auther Eternal
 * @Date 2022/4/13
 */
@AllArgsConstructor
@Getter
public enum ConsultantStatusEnum {
    NO_AUTH(0, "未认证"),
    AUTH_RUN(1, "认证中"),
    AUTH_OK(2, "认证成功"),
    AUTH_FAIL(-1, "认证失败"),
            ;

    private Integer status;
    private String msg;

    public static String getMsgByStatus(int status) {
        ConsultantStatusEnum arrObj[] = ConsultantStatusEnum.values();
        for (ConsultantStatusEnum obj : arrObj) {
            if (status == obj.getStatus().intValue()) {
                return obj.getMsg();
            }
        }
        return "";
    }
}
