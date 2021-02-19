package com.kai.checkcode.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author kai
 * @since 2021-02-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Admin对象", description="")
public class Admin implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "姓名")
    private String username;

    @ApiModelProperty(value = "电话")
    private String phone;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "邮箱")
    private String mail;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime create_time;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime update_time;

    @ApiModelProperty(value = "角色")
    private String role;


}
