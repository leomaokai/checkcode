package com.kai.checkcode.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kai.checkcode.mapper.WorkContentMapper;
import com.kai.checkcode.pojo.WorkContent;
import com.kai.checkcode.pojo.WorkDescribe;
import com.kai.checkcode.service.IWorkContentService;
import com.kai.checkcode.service.IWorkDescribeService;
import com.kai.checkcode.util.RespBean;
import com.kai.checkcode.util.RespBeanEnum;
import com.kai.checkcode.util.RespPageBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author kai
 * @since 2021-02-19
 */
@Service
public class WorkContentServiceImpl extends ServiceImpl<WorkContentMapper, WorkContent> implements IWorkContentService {

    @Resource
    private WorkContentMapper workContentMapper;
    @Resource
    private IWorkDescribeService workDescribeService;

    @Override
    public RespPageBean getWorkContents(Integer currentPage, Integer size, WorkContent workContent, WorkDescribe work) {
        Page<WorkContent> workContentPage = new Page<>(currentPage, size);
        IPage<WorkContent> workContentIPage = workContentMapper.getWorkContents(workContentPage, workContent, work);
        return new RespPageBean(workContentIPage.getTotal(), workContentIPage.getRecords());
    }

    @Override
    @Transactional
    public RespBean deleteWorkContent(long[] ids) {
        for (long id : ids) {
            WorkContent one = workContentMapper.selectOne(new QueryWrapper<WorkContent>().eq("id", id));
            Long id1 = one.getId();
            Integer workId = one.getWorkId();
            workContentMapper.deleteById(id1);
            boolean res = workDescribeService.update(new UpdateWrapper<WorkDescribe>().setSql("commit_number = commit_number-1").eq("id", workId));
            if(!res){
                return RespBean.error(RespBeanEnum.FAIL);
            }
        }
        return RespBean.success();
    }
}
