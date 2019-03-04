package com.example.demo.txFirst;

import com.example.demo.txFirst.domain.Foo;
import com.example.demo.txFirst.service.FooService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.AbstractPlatformTransactionManager;

/**
 * Created by zhong on 2018/9/1.
 */
public final class Boot {

    public static void main(final String[] args) throws Exception{
        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:/spring/context.xml");
        FooService fooService = (FooService) ctx.getBean("fooService");
        fooService.insertFoo(new Foo());
    }

}
