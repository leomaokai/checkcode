package com.kai.checkcode.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kai.checkcode.pojo.Admin;
import com.kai.checkcode.util.RespBean;

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
}
