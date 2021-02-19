package com.kai.checkcode.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kai.checkcode.mapper.WorkMapper;
import com.kai.checkcode.pojo.Work;
import com.kai.checkcode.service.IWorkService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author kai
 * @since 2021-02-19
 */
@Service
public class WorkServiceImpl extends ServiceImpl<WorkMapper, Work> implements IWorkService {

}
