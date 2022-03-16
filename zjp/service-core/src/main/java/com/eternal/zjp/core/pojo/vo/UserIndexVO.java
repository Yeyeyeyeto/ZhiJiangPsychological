package com.eternal.zjp.core.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Auther Eternal
 * @Date 2022/3/10
 */
@Data
@ApiModel(description = "首页用户信息")
public class UserIndexVO {

    @ApiModelProperty(value = "用户id")
    private Integer userId;

    @ApiModelProperty(value = "用户电话")
    private String mobile;

    @ApiModelProperty(value = "用户昵称")
    private String nickName;

    @ApiModelProperty(value = "用户头像")
    private String headImg;

    @ApiModelProperty(value = "帐户可用余额")
    private BigDecimal amount;

}
