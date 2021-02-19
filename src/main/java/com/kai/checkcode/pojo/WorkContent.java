package com.kai.checkcode.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("work_content")
@ApiModel(value="WorkContent对象", description="")
public class WorkContent implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "作业id")
    private Integer work_id;

    @ApiModelProperty(value = "作业位置")
    private String url;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime create_time;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime update_time;


}
