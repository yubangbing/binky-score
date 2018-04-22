package com.binky.score.model;

/**
 * Created by bing on 2018/4/19.
 */
public class ResultModel {
    private Integer re;
    private String msg;
    public  ResultModel(){

    }
    public ResultModel(Integer re, String msg) {
        this.re = re;
        this.msg = msg;
    }

    public Integer getRe() {
        return re;
    }

    public void setRe(Integer re) {
        this.re = re;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
