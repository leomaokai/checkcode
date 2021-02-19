package com.kai.checkcode.controller;


import com.kai.checkcode.service.IWorkContentService;
import com.kai.checkcode.util.RespBean;
import com.kai.checkcode.util.RespBeanEnum;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@RestController
public class CommitController {

    @Resource
    private IWorkContentService workContentService;

    @ApiOperation(value = "提交作业")
    @PostMapping("/commit")
    public RespBean commitWork(MultipartFile file,String str){

        return RespBean.success(RespBeanEnum.COMMIT_SUCCESS);
    }


}
