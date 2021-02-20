package com.kai.checkcode.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kai.checkcode.pojo.WorkDescribe;
import com.kai.checkcode.service.IWorkDescribeService;
import com.kai.checkcode.util.RespBean;
import com.kai.checkcode.util.RespBeanEnum;
import com.kai.checkcode.util.RespPageBean;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author kai
 * @since 2021-02-19
 */
@RestController
@RequestMapping("/workDescribe")
public class WorkDescribeController {


    @Resource
    private IWorkDescribeService workDescribeService;

    @ApiOperation(value = "添加作业描述")
    @PostMapping("/")
    public RespBean insertWorkDescribe(WorkDescribe workDescribe){
        if(workDescribeService.save(workDescribe)){
            return RespBean.success();
        }
        return RespBean.error(RespBeanEnum.FAIL);
    }

    @ApiOperation(value = "删除作业描述(0提交才能删除)")
    @DeleteMapping("/{id}")
    public RespBean deleteWorkDescribe(@PathVariable("id") int id){
        if(workDescribeService.remove(new QueryWrapper<WorkDescribe>().eq("id",id).eq("commit_number",0))){
            return RespBean.success();
        }
        return RespBean.error(RespBeanEnum.FAIL);
    }
}
