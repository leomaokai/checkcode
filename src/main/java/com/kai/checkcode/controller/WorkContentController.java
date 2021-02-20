package com.kai.checkcode.controller;


import com.kai.checkcode.pojo.WorkContent;
import com.kai.checkcode.pojo.WorkDescribe;
import com.kai.checkcode.service.IWorkContentService;
import com.kai.checkcode.util.RespBean;
import com.kai.checkcode.util.RespPageBean;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author kai
 * @since 2021-02-19
 */
@RestController
@RequestMapping("/workContent")
public class WorkContentController {

    @Resource
    private IWorkContentService workContentService;

    @ApiOperation(value = "获取作业(分页)")
    @GetMapping("/")
    public RespPageBean getListWorkContent(@RequestParam(defaultValue = "1") Integer currentPage,
                                           @RequestParam(defaultValue = "10") Integer size,
                                           WorkContent workContent
                                           ){
        return workContentService.getWorkContents(currentPage,size,workContent,workContent.getWorkDescribe());
    }

    @ApiOperation(value = "删除作业(批量)")
    @DeleteMapping("/")
    public RespBean deleteWorkContent(long [] ids){
        return workContentService.deleteWorkContent(ids);
    }
}
