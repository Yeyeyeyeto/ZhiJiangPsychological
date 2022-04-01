package com.eternal.zjp.core.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Auther Eternal
 * @Date 2022/3/16
 */
@Data
@ApiModel(description="问卷信息")
public class QuestionnaireVO {

    @ApiModelProperty(value = "作者id")
    private Integer authorId;

//    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

//    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "问卷名称")
    private String questionnaireName;

    @ApiModelProperty(value = "问卷简介")
    private String questionnaireIntro;

    @ApiModelProperty(value = "问卷类型")
    private Integer questionnaireType;

    @ApiModelProperty(value = "结果说明")
    private String result;

//    @ApiModelProperty(value = "借款人附件资料")
//    private List<BorrowerAttachVO> borrowerAttachVOList;

}
