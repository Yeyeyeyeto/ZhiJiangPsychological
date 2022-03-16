package com.eternal.zjp.core.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author Eternal
 * @since 2022-03-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="QuestionnaireQa对象", description="")
public class QuestionnaireQa implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "编号")
      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "问卷id")
    private Integer questionnaireId;

    @ApiModelProperty(value = "问卷详情id")
    private Integer questionnaireItemId;

    @ApiModelProperty(value = "用户id")
    private Integer userId;

    @ApiModelProperty(value = "题目题号")
    private Integer questionNum;

    @ApiModelProperty(value = "题目题干")
    private String questionTitle;

    @ApiModelProperty(value = "回答")
    private String answer;


}
