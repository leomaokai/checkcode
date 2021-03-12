package com.kai.checkcode.service.impl;

import com.kai.checkcode.mapper.WorkContentMapper;
import com.kai.checkcode.mapper.WorkDescribeMapper;
import com.kai.checkcode.mapper.WorkResultMapper;
import com.kai.checkcode.service.ICheckWorkService;
import jplag.ExitException;
import jplag.Program;
import jplag.options.CommandLineOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CheckWorkServiceImpl implements ICheckWorkService {

    @Value("${kai.result}")
    private String result;
    @Value("${kai.sim}")
    private int sim;
    @Value("${kai.resource}")
    private String resource;


    @Override
    public String checkResult(String describe, String lang) {
        File retFile = new File(result, describe);
        cleanResult(new File(result, describe));
//        if (retFile.listFiles() != null) {
//            // return convertMatchesFileToString(new File(retFile, "matches_max.csv"));
//        }
        String ret;
        try {
            List<String> args = new ArrayList<>();
            // 指定语言
            args.add("-l");
            // java只能检测java9以后版本
            if ("Java".equals(lang)) {
                args.add("java19");
            } else if ("C".equals(lang) || "C++".equals(lang)) {
                args.add("c/c++");
            } else if ("Python".equals(lang)) {
                args.add("python3");
            } else {
                args.add(lang);
            }
            // 指定结果存放的路径
            args.add("-r");
            args.add(new File(result, describe).getPath());
            // 设置相似度检查门限参数值
            args.add("-m");
            args.add("1" + "%");
            // 指定源文件存放路径
            args.add("-s");
            String path = new File(resource, describe).getPath();
            args.add(path);
            args.add("-c");
            args.add("a.cpp");
            args.add("b.cpp");
            //System.out.println("CheckWorkServiceImpl==>resourcePath:" + path);
            String[] toPass = new String[args.size()];
            toPass = args.toArray(toPass);
            //System.out.println("CheckWorkServiceImpl==>toPass" + toPass.toString());

            new Program(new CommandLineOptions(toPass)).run();
        } catch (ExitException e) {
            e.printStackTrace();
        }
        File file = new File(result, describe);
        //ret =convertMatchesFileToString(new File(file,"matches_avg.csv"));
        ret = convertMatchesFileToString(new File(file, "matches_max.csv"));
        return ret;
    }

    @Override
    public String getResult(String workDescribe) {
        File retFile = new File(result, workDescribe);
        if (retFile.listFiles() != null) {
            return convertMatchesFileToString(new File(retFile, "matches_max.csv"));
        }
        return "请查重后再来查询";
    }

    private void cleanResult(File dir) {
        File[] files = dir.listFiles();
        if (files != null) for (File file : files) {
            file.delete();
        }
    }

    private String convertMatchesFileToString(File file) {
        byte[] fileContent = new byte[(int) file.length()];
        FileInputStream in = null;
        try {
            in = new FileInputStream(file);
            in.read(fileContent);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new String(fileContent);
    }
}
