package com.kai.checkcode.controller;


import com.kai.checkcode.service.ICheckWorkService;
import com.kai.checkcode.util.RespBean;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/check")
public class CheckWorkController {

    @Resource
    private ICheckWorkService checkWorkService;

    @ApiOperation(value = "代码查重")
    @GetMapping("/do")
    public String checkResult(String workDescribe,String lang){
        return checkWorkService.checkResult(workDescribe,lang);
    }

    @ApiOperation(value = "获得查重结果")
    @GetMapping("/get")
    public String getResult(String workDescribe){
        return checkWorkService.getResult(workDescribe);
    }

}
