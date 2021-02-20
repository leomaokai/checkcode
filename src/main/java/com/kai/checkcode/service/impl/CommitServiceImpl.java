package com.kai.checkcode.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.kai.checkcode.mapper.WorkContentMapper;
import com.kai.checkcode.mapper.WorkDescribeMapper;
import com.kai.checkcode.pojo.WorkContent;
import com.kai.checkcode.pojo.WorkDescribe;
import com.kai.checkcode.service.ICommitService;
import com.kai.checkcode.service.IWorkDescribeService;
import com.kai.checkcode.util.RespBean;
import com.kai.checkcode.util.RespBeanEnum;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class CommitServiceImpl implements ICommitService {

    @Resource
    private WorkContentMapper workContentMapper;
    @Resource
    private WorkDescribeMapper workDescribeMapper;
    @Resource
    private IWorkDescribeService workDescribeService;
    @Value("${kai.resource}")
    private String resource;

    @Override
    @Transactional
    public RespBean commitWork(MultipartFile workFile, String workDescribe) {
        WorkDescribe one = workDescribeService.getOne(new QueryWrapper<WorkDescribe>().eq("work_describe", workDescribe));
        int did = one.getId();
        String workFileName = workFile.getOriginalFilename();
        String contentType = workFileName.substring(workFileName.lastIndexOf('.') + 1);
        System.out.println("CommitServiceImpl:workFileName===>" + workFileName);
        //String url="/"+workDescribe+"/"+workFileName;
        String directoryUrl = new File(resource,workDescribe).getPath();
        System.out.println("CommitServiceImpl:directoryUrl===>" + directoryUrl);
        Path path = null;
        try {
            byte[] bytes = workFile.getBytes();
            if (!Files.isWritable(Paths.get(directoryUrl))) {
                Files.createDirectories(Paths.get(directoryUrl));
            }
            path = Paths.get(directoryUrl, workFileName);
            Files.write(path, bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("CommitServiceImpl:path===>" + path);
        WorkContent workContent = new WorkContent();
        workContent.setWorkId(did);
        workContent.setWorkName(workFileName);
        workContent.setUrl(String.valueOf(path));
        workContent.setWorkType(contentType);
        if (workContentMapper.insert(workContent) == 1) {
            one.setCommitNumber(one.getCommitNumber() + 1);
            workDescribeService.updateById(one);
            return RespBean.success(RespBeanEnum.COMMIT_SUCCESS);
        }
        return RespBean.error(RespBeanEnum.COMMIT_ERROR);
    }
}
