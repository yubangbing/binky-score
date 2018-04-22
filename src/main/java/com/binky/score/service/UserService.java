package com.binky.score.service;


import com.binky.score.model.ResultModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by bing on 2017/7/30.
 */
public interface UserService {
    ResultModel getScore(String username, String password, HttpServletRequest request, HttpServletResponse response);
}
