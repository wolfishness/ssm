package com.bosssoft.hr.train.aspect.anno;

import java.lang.annotation.*;

/**
 * 定义系统日志注解
 *
 * @author lujinshan
 * @date 2019/7/25 18:34
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLog {
    //设置默认值
    String value() default "";
}
