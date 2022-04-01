package com.eternal.zjp.core.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 问卷表
 * </p>
 *
 * @author Eternal
 * @since 2022-03-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Questionnaire对象", description="问卷表")
public class Questionnaire implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "编号")
      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "作者id")
    private Integer authorId;

    @ApiModelProperty(value = "问卷名字")
    private String questionnaireName;

    @ApiModelProperty(value = "问卷类型（1 是否；2 单选；3 问答；4 多选；5 混合）")
    private Integer questionnaireType;

    @ApiModelProperty(value = "问卷简介")
    private String questionnaireIntro;

    @ApiModelProperty(value = "结果说明")
    private String result;

//    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

//    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "逻辑删除(1:已删除，0:未删除)")
    @TableField("is_deleted")
    @TableLogic
    private Boolean deleted;

    @ApiModelProperty(value = "状态（0：正常 1：未完善）")
    private Integer status;

}
