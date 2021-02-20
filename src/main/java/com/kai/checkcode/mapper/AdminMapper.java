package com.kai.checkcode.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kai.checkcode.pojo.Admin;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author kai
 * @since 2021-02-19
 */
public interface AdminMapper extends BaseMapper<Admin> {

    IPage<Admin> getListAdmin(Page<Admin> adminPage, @Param("users") Admin admin);
}
