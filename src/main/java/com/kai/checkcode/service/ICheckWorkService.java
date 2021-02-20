package com.kai.checkcode.service;

public interface ICheckWorkService {
    String checkResult(String describe, String lang);

    String getResult(String workDescribe);
}
