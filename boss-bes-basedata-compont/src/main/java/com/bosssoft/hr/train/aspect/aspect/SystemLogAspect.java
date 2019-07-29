package com.bosssoft.hr.train.aspect.aspect;

import com.bosssoft.hr.train.aspect.anno.SysLog;
import com.bosssoft.hr.train.aspect.service.SystemLogService;
import com.bosssoft.hr.train.pojo.entity.SystemLog;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 系统日志切面,使用@Aspect注解声明一个切面
 * @author lujinshan
 * @date 2019/7/25 18:35
 */
@Aspect
@Component
public class SystemLogAspect {
    /**
     * 生成log4j对象
     */
    private Logger logger = Logger.getLogger(SystemLogAspect.class);
    /**
     * 声明SystemLogService对象
     */
    @Resource
    SystemLogService systemLogService;

    /**
     * 采用注解的方式
     */
    @Pointcut("@annotation(com.bosssoft.hr.train.aspect.anno.SysLog)")
    public void logPointCut(){

    }

    /**
     * 环绕通知
     * @param point 通过ProceedingJoinPoint获取当前执行的方法
     * @return
     * @throws Throwable
     */
    @Around("logPointCut()")
    public  Object around(ProceedingJoinPoint point) throws Throwable{
        //获取系统开始时间
        long beginTime = System.currentTimeMillis();
        //使用proceed()方法来执行目标方法：
        Object result = point.proceed();
        //计算运行时间
        long time = System.currentTimeMillis() - beginTime;
        //尝试运行保存日志
        try {
            saveLog(point , time);
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return  result;
    }

    /**
     * 保存日志信息
     * @param joinPoint
     * @param time
     */
    private  void saveLog(ProceedingJoinPoint joinPoint , long time){
        //获取连接点签名，用来获取方法
        MethodSignature methodSignature = (MethodSignature)joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        SystemLog systemLog = new SystemLog();
        //设置运行的时间
        systemLog.setExeuTime(time);
        //设置创建的时间
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        systemLog.setCreateDate(dateFormat.format(new Date()));
        SysLog sysLog = method.getAnnotation(SysLog.class);
        //注解上的描述
        if (sysLog != null){
            systemLog.setRemark(sysLog.value());
        }
        //请求的类名和方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = methodSignature.getName();
        //设置对应的类名和方法名
        systemLog.setClassName(className);
        systemLog.setMethodName(methodName);
        //获取请求参数
        Object[] params = joinPoint.getArgs();
        try {
            //解析请求参数
            List<String> list = new ArrayList<String>();
            for (Object o: params){
                list.add(o.toString());
            }
            //设置请求参数
            systemLog.setParams(list.toString());
        }catch (Exception e){
        }
        //保存日志
        systemLogService.save(systemLog);

    }
}
