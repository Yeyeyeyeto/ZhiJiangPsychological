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
 * 咨询师表
 * </p>
 *
 * @author Eternal
 * @since 2022-03-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Consultant对象", description="咨询师表")
public class Consultant implements Serializable {

    public static final Integer STATUS_NORMAL = 1;
    public static final Integer STATUS_LOCKED = 0;
    public static final String USER_AVATAR = "https://zjjr-file.oss-cn-beijing.aliyuncs.com/avatar/01.jpg";

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "编号")
      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "手机号")
    private String mobile;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "性别(1: 男，0: 女)")
    private Integer sex;

    @ApiModelProperty(value = "年龄")
    private Integer age;

    @ApiModelProperty(value = "头像")
    private String headImg;

    @ApiModelProperty(value = "类型（咨询师）")
    private Integer type;

    @ApiModelProperty(value = "逻辑删除(1:已删除，0:未删除)")
    @TableField("is_deleted")
    @TableLogic
    private Boolean deleted;

    @ApiModelProperty(value = "状态（0：锁定 1：正常）")
    private Integer status;

    @ApiModelProperty(value = "咨询师真实姓名")
    private String nickName;

    @ApiModelProperty(value = "咨询师等级")
    private String consultantGrade;

    @ApiModelProperty(value = "咨询师咨询地点")
    private String consultantLocation;

    @ApiModelProperty(value = "咨询师擅长方向")
    private String consultantDirection;

    @ApiModelProperty(value = "咨询师擅长方向")
    private String consultantMotto;

    @ApiModelProperty(value = "咨询师认证状态（0：未认证 1：认证中 2：认证通过 -1：认证失败）")
    private Integer consultantAuthStatus;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;


}
