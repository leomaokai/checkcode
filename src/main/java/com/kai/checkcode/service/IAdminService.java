package com.kai.checkcode.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kai.checkcode.pojo.Admin;
import com.kai.checkcode.util.RespBean;
import com.kai.checkcode.util.RespPageBean;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author kai
 * @since 2021-02-19
 */
public interface IAdminService extends IService<Admin> {

    RespBean login(String username, String password, HttpServletRequest request);

    Admin getAdminByUsername(String username);

    RespPageBean getListAdmin(Integer currentPage, Integer size, Admin admin);

    RespBean updateAdmin(Admin admin);

    RespBean insertAdmin(Admin admin);
}
