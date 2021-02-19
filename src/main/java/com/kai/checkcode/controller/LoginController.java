package com.kai.checkcode.controller;


import com.kai.checkcode.pojo.AdminLoginParam;
import com.kai.checkcode.service.IAdminService;
import com.kai.checkcode.util.RespBean;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
public class LoginController {

    @Resource
    private IAdminService adminService;

    @ApiOperation(value = "登录之后返回token")
    @PostMapping("/login")
    public RespBean login(@RequestBody AdminLoginParam adminLoginParam, HttpServletRequest request){
        System.out.println("login");
        return adminService.login(adminLoginParam.getUsername(),adminLoginParam.getPassword(),request);
    }
}
