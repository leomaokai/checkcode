package com.kai.checkcode.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kai.checkcode.pojo.WorkContent;
import com.kai.checkcode.pojo.WorkDescribe;
import com.kai.checkcode.util.RespBean;
import com.kai.checkcode.util.RespPageBean;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author kai
 * @since 2021-02-19
 */
public interface IWorkContentService extends IService<WorkContent> {


    RespPageBean getWorkContents(Integer currentPage, Integer size, WorkContent workContent, WorkDescribe work);

    RespBean deleteWorkContent(long[] ids);
}
