package com.kai.checkcode.controller;


import com.kai.checkcode.pojo.Admin;
import com.kai.checkcode.service.IAdminService;
import com.kai.checkcode.util.RespBean;
import com.kai.checkcode.util.RespBeanEnum;
import com.kai.checkcode.util.RespPageBean;
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
@RequestMapping("/admin")
public class AdminController {

    @Resource
    private IAdminService adminService;

//    @ApiOperation(value = "根据姓名获取管理员信息(分页,默认查全部)")
//    @GetMapping("/")
//    public RespPageBean getListAdmin(@RequestParam(defaultValue = "1") Integer currentPage,
//                                     @RequestParam(defaultValue = "10") Integer size,
//                                     Admin admin){
//        return adminService.getListAdmin(currentPage,size,admin);
//    }

    @ApiOperation(value = "添加管理员")
    @PostMapping("/")
    public RespBean insertAdmin(@RequestBody Admin admin){
        return adminService.insertAdmin(admin);
    }

    @ApiOperation(value = "根据id删除管理员")
    @DeleteMapping("/{id}")
    public RespBean deleteAdmin(@PathVariable("id") int id){
        if(adminService.removeById(id)){
            return RespBean.success();
        }
        return RespBean.error(RespBeanEnum.FAIL);
    }

    @ApiOperation(value = "更新管理员")
    @PutMapping("/")
    public RespBean updateAdmin(Admin admin){
        return adminService.updateAdmin(admin);
    }
}
