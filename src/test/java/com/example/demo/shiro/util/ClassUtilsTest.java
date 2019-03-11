package com.example.demo.shiro.util;

import com.example.demo.activity.domain.Activity;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

public class ClassUtilsTest {

    ClassUtils classUtils;

    @Before
    public void init(){
        classUtils = new ClassUtils();
    }

    @Test
    public void getResourceAsStream() throws IOException {
        InputStream is = ClassUtils.getResourceAsStream("spring/context.xml");
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        String line;
        while ((line = reader.readLine()) != null){
            System.out.println(line);
        }
    }

    @Test
    public void forName() throws IllegalAccessException, InstantiationException {
        Class clazz = ClassUtils.forName("com.example.demo.activity.domain.Activity");
        Activity activity = (Activity) clazz.newInstance();
        System.out.println(activity);
    }

    @Test
    public void isAvailable() {
        System.out.println( ClassUtils.isAvailable("com.example.demo1.activity.domain.Activity"));
    }

    @Test
    public void newInstance() {
    }

    @Test
    public void newInstance1() {
    }

    @Test
    public void newInstance2() {
    }

    @Test
    public void newInstance3() {
    }

    @Test
    public void getConstructor() {
    }

    @Test
    public void instantiate() {
    }
}