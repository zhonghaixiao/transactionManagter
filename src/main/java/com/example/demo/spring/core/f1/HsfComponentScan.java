package com.example.demo.spring.core.f1;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface HsfComponentScan {
    /**
     * @return
     */
    String[] value() default {};

    /**
     * 扫描包
     *
     * @return
     */
    String[] basePackages() default {};

    /**
     * 扫描的基类
     *
     * @return
     */
    Class<?>[] basePackageClasses() default {};

    /**
     * 包含过滤器
     *
     * @return
     */
    ComponentScan.Filter[] includeFilters() default {};

    /**
     * 排斥过滤器
     *
     * @return
     */
    ComponentScan.Filter[] excludeFilters() default {};

}
