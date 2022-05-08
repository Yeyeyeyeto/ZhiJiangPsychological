package com.eternal.zjp.core.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Auther Eternal
 * @Date 2022/3/17
 */
@Data
@ApiModel(description="单选型问卷详细信息")
public class QuestionnaireRadioVO {

    @ApiModelProperty(value = "问卷id")
    private Integer questionnaireId;

    @ApiModelProperty(value = "问卷题号")
    private Integer questionNum;

    @ApiModelProperty(value = "问卷题干")
    private String questionTitle;

    @ApiModelProperty(value = "选项内容及对应分值")
    private String radioOptions;

}
