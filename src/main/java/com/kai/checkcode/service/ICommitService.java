package com.kai.checkcode.service;

import com.kai.checkcode.util.RespBean;
import org.springframework.web.multipart.MultipartFile;

public interface ICommitService {
    RespBean commitWork(MultipartFile workFile, String workDescribe);
}
