package com.eternal.zjp.core.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Auther Eternal
 * @Date 2022/4/15
 */
@Data
@ApiModel(value="咨询师附件资料")
public class ConsultantAttachVO {
    @ApiModelProperty(value = "图片类型（idCard1：身份证正面，idCard2：身份证反面，credential：相关证书）")
    private String imageType;

    @ApiModelProperty(value = "图片路径")
    private String imageUrl;
}
