package com.autossh.quartz.job;

import org.python.core.PyFunction;
import org.python.core.PyInteger;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;

import java.io.*;

public class java_Python_test {
    public static void main1(String[] args) throws IOException, InterruptedException {
//        // TODO Auto-generated method stub
//        PythonInterpreter interpreter = new PythonInterpreter();
//        interpreter.execfile("F:\\untitled\\demo.py");
//
//        // 第一个参数为期望获得的函数（变量）的名字，第二个参数为期望返回的对象类型
//        PyFunction pyFunction = interpreter.get("my_test", PyFunction.class);
//        int a = 5, b = 10;
//        //调用函数，如果函数需要参数，在Java中必须先将参数转化为对应的“Python类型”
//        PyObject pyobj = pyFunction.__call__(new PyInteger(a), new PyInteger(b));
//        System.out.println("the anwser is: " + pyobj);
        String[] arguments = new String[] {"python", "F:\\untitled\\demp.py"};
        String line = null;
        try {
            Process process = Runtime.getRuntime().exec("python F:\\untitled\\demp.py 1 2");
            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            in.close();
            int re = process.waitFor();
            System.out.println(re);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
