package com.binky.score.python;

import com.binky.score.utils.OSUtil;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by bing on 2018/4/18.
 */
public class testpython {
    @Test
    public void test3(){

 /*       String  pythonPath = this.getClass().getResource("/").getPath()+"getscore.py";
        System.out.println(pythonPath);
        //不同操作系统处理字符串
        if(OSUtil.isWindows()){
            pythonPath = pythonPath.substring(1,pythonPath.length());
        }
        System.out.println(pythonPath);
        String[] arguments = new String[] {"python", pythonPath, "Y30150649", "930408"};
        try {
            Process process = Runtime.getRuntime().exec(arguments);
            InputStream in = process.getInputStream();
            FileOutputStream out = new FileOutputStream("D:\\score.html");
            int len = 0;
            byte buffer[] = new byte[1024];
            while((len=in.read(buffer))>0){
                out.write(buffer,0,len);
            }
          *//*  BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = null;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }*//*
            in.close();
            out.close();
            int re = process.waitFor();
            System.out.println(re);
        } catch (Exception e) {
            System.out.println("except");
            e.printStackTrace();
        }*/
    }
    @Test
    public void test4(){
        System.out.println(OSUtil.isWindows());
        System.out.println(OSUtil.isLinux());
    }
}
