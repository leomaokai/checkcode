package com.kai.checkcode.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kai.checkcode.pojo.WorkContent;
import com.kai.checkcode.pojo.WorkDescribe;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author kai
 * @since 2021-02-19
 */
public interface WorkContentMapper extends BaseMapper<WorkContent> {

    IPage<WorkContent> getWorkContents(Page<WorkContent> page, @Param("workContent") WorkContent workContent,@Param("work") WorkDescribe work);

}
