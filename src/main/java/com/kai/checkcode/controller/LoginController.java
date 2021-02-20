package com.kai.checkcode.controller;


import com.kai.checkcode.pojo.Admin;
import com.kai.checkcode.pojo.AdminLoginParam;
import com.kai.checkcode.service.IAdminService;
import com.kai.checkcode.util.RespBean;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@RestController
public class LoginController {

    @Resource
    private IAdminService adminService;

    @ApiOperation(value = "登录之后返回token")
    @PostMapping("/login")
    public RespBean login(@RequestBody AdminLoginParam adminLoginParam, HttpServletRequest request){
        return adminService.login(adminLoginParam.getUsername(),adminLoginParam.getPassword(),request);
    }

    @ApiOperation(value = "获取当前登录的用户信息")
    @GetMapping("/adminInfo")
    public Admin getAdminInfo(Principal principal){
        if(null==principal){
            return null;
        }
        String username = principal.getName();
        Admin admin=adminService.getAdminByUsername(username);
        admin.setPassword(null);
        return admin;
    }

    @ApiOperation(value = "退出登录")
    @PostMapping("/logout")
    public RespBean logout(){
        // 前端直接将token删除即可,之后访问会被拦截器拦截
        return RespBean.success();
    }
}
