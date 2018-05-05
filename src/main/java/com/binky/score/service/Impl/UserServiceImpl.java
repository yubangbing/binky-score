package com.binky.score.service.Impl;

import com.binky.score.model.ResultModel;
import com.binky.score.service.UserService;
import com.binky.score.utils.OSUtil;
import com.binky.score.utils.Md5Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * Created by bing on 2017/7/30.
 */
@Service
public class UserServiceImpl implements UserService {
    private static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    public ResultModel getScore(String username, String password, HttpServletRequest request, HttpServletResponse response) {
        log.info("===========service start============");
        ResultModel result = new ResultModel();
        String pythonPath = this.getClass().getResource("/").getPath() + "getscore.py";
        log.info("==========pytjonpath1:" + pythonPath);
//        System.out.println(pythonPath);
        //不同操作系统处理字符串
        if (OSUtil.isWindows()) {
            pythonPath = pythonPath.substring(1, pythonPath.length());
        }
        log.info("==========pytjonpath2:" + pythonPath);
//        System.out.println(pythonPath);
        String[] arguments = new String[]{"python", pythonPath, username, password};
        try {
            String filename = Md5Util.toMD5(username).substring(0, 9) + ".jsp";
            String filepath = request.getSession().getServletContext().getRealPath("/WEB-INF") + "/" + filename;
            log.info("==========pythonfilepath:" + filepath);
            Process process = Runtime.getRuntime().exec(arguments);
            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(filepath), "utf-8"));
            log.info("==============out is go=============");
            String line = null;
            String jspheader = "<%@ page language=\"java\" contentType=\"text/html; charset=UTF-8\" pageEncoding=\"UTF-8\"%>";
            out.write(jspheader);
            String flag = in.readLine();
            if ("2".equals(flag)||flag==null) {
                //用户名或密码错误
                log.info("用户名或密码错误");
                return new ResultModel(0, "2");
            }

            while ((line = in.readLine()) != null) {
                    out.write(line);
            }
            in.close();
            out.close();
            int re = process.waitFor();
            result.setRe(re);
            result.setMsg(Md5Util.toMD5(username).substring(0, 9));
            log.info("=========success:" + result.getMsg() + "RE IS:" + String.valueOf(re));
            return result;
        } catch (Exception e) {
            log.info("=========into exception:" + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}

