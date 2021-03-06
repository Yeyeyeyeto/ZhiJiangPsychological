package com.eternal.zjp.core.pojo.vo;

import com.eternal.zjp.core.pojo.entity.ConsultantAttach;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Auther Eternal
 * @Date 2022/4/13
 */
@Data
@ApiModel(description="咨询师认证信息")
public class ConsultantVO {
    @ApiModelProperty(value = "姓名")
    private String nickName;

    @ApiModelProperty(value = "年龄")
    private Integer age;

    @ApiModelProperty(value = "性别（1：男 0：女）")
    private Integer sex;

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

    @ApiModelProperty(value = "咨询师附件资料")
    private List<ConsultantAttach> consultantAttachList;
}
