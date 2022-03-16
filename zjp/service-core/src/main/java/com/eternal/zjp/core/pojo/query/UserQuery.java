package com.eternal.zjp.core.pojo.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Auther Eternal
 * @Date 2022/3/9
 */
@Data
@ApiModel(description = "会员搜索对象")
public class UserQuery {

    @ApiModelProperty("value = 手机号")
    private String mobile;

}
