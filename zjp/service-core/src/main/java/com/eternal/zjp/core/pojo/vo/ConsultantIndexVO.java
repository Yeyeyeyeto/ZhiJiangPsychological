package com.eternal.zjp.core.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

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

    @ApiModelProperty(value = "认证状态")
    private Integer status;

    @ApiModelProperty(value = "咨询师等级头衔")
    private String consultantGrade;

    @ApiModelProperty(value = "咨询师个人签名")
    private String consultantMotto;

    @ApiModelProperty(value = "线下咨询地点")
    private String consultantLocation;

    @ApiModelProperty(value = "个人签名")
    private String consultantDirection;

    @ApiModelProperty(value = "咨询费用")
    private Integer consultantCost;
}
