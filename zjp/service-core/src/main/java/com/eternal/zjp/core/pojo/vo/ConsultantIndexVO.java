package com.eternal.zjp.core.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Auther Eternal
 * @Date 2022/3/14
 */
@Data
@ApiModel(description = "首页咨询师信息")
public class ConsultantIndexVO {
    @ApiModelProperty(value = "咨询师id")
    private Integer userId;

    @ApiModelProperty(value = "咨询师电话")
    private String mobile;

    @ApiModelProperty(value = "咨询师昵称")
    private String nickName;

    @ApiModelProperty(value = "咨询师头像")
    private String headImg;

    @ApiModelProperty(value = "帐户可用余额")
    private BigDecimal amount;

}
