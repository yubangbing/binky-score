package com.binky.score.controller;

import com.binky.score.model.ResultModel;
import com.binky.score.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by bing on 2017/7/30.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    //获取成绩单
    @RequestMapping(value = "/getscore", method = RequestMethod.POST)
    @ResponseBody
    public String userLogin(String username, String password,
                                 HttpServletRequest request, HttpServletResponse response) {
        ResultModel result = userService.getScore(username,password,request,response);

        return result.getMsg();
    }
    @RequestMapping(value = "/redirect")
    public String userRedirect(String soup) {

        return "forward:/WEB-INF/"+soup+".jsp";
    }
    @RequestMapping(value = "/test")
    public String test1(String soup) {
        System.out.println("ok");
        return "forward:/WEB-INF/"+soup+".jsp";
    }
}

