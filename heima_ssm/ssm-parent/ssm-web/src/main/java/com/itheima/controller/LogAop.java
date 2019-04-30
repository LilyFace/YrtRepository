package com.itheima.controller;

import com.itheima.domain.SysLog;
import com.itheima.service.SysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @Author: YangRunTao
 * @Description: 日志aop类
 * @Date: 2019/04/28 20:13
 * @Modified By:
 * private String id;
 * private Date visitTime;  Done
 * private String visitTimeStr; Done
 * private String username;
 * private String ip;
 * private String url;
 * private Long executionTime;
 * private String method;Done
 */
@SuppressWarnings("JavaDoc")
@Component
@Aspect
public class LogAop {

    private HttpServletRequest request;

    @Autowired
    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    private SysLogService sysLogService;

    @Autowired
    public void setSysLogService(SysLogService sysLogService) {
        this.sysLogService = sysLogService;
    }

    private Date visitTime; //开始时间
    private Class<?> clazz; //访问的类
    private Method method;//访问的方法

    /**
     * @param jp
     * @description: 前置通知  主要是获取开始时间，执行的类是哪一个，执行的是哪一个方法
     * @return: void
     * @author: YangRunTao
     * @date: 2019/04/28 20:26
     * @throws:
     **/
    @Before("execution(* com.itheima.controller.*.*(..))")
    public void doBefore(JoinPoint jp) throws NoSuchMethodException {
        //开始时间
        visitTime = new Date();
        //获得执行的类
        clazz = jp.getTarget().getClass();
        //获得获取访问的方法的名称
        String methodName = jp.getSignature().getName();
        //获得方法的返回值
        Object[] args = jp.getArgs();
        if (args == null || args.length == 0) {
            //获得无参方法
            method = clazz.getMethod(methodName);
        } else {
            //装换参数的类型为Class类型的数组
            Class<?>[] classArgs = new Class[args.length];
            for (int i = 0; i < args.length; i++) {
                /*
                 如果获得的参数中是使用了接口的方式，getClass获得的是其实现类的Class对象，
                 在调用clazz.getMethod(methodName, classArgs)时实际传入的是该接口的实现类，
                 所以在获得Method对象时会以实现类作为参数的方式去获取导致出现NoSuchMethod异常或
                 者动态代理异常，所以要让clazz.getMethod(methodName, classArgs)和切点的参数保持一致，也就是
                 clazz.getMethod(methodName, classArgs)中classArgs的类型为接口类型的Class对象类型

                 举例：findAll(Model model)-->需要传入的classArgs为Model.class-->clazz.getMethod(methodName, Model.class);
                 */
                if (args[i] instanceof Model) {
                    classArgs[i] = Model.class;
                } else {
                    classArgs[i] = args[i].getClass();
                }
            }
            //获得有参数的方法
            method = clazz.getMethod(methodName, classArgs);
        }
    }

    /**
     * @param jp
     * @description: 后置通知
     * @return: void
     * @author: YangRunTao
     * @date: 2019/04/28 20:43
     * @throws:
     **/
    @After("execution(* com.itheima.controller.*.*(..))")
    public void doAfter(JoinPoint jp) throws Exception {
        /*
         * 需要封装
         * private String username;
         * private String ip;
         * private String url;
         * private Long executionTime;
         */

        //执行时间
        Long executionTime = new Date().getTime() - visitTime.getTime();
        //获得ip
        String ip;
        //获得username和url
        String username;
        String url = null;
        if (clazz != null && method != null && clazz != LogAop.class) {
            //1.获取类上的@RequestMapping("/orders")
            RequestMapping classAnnotation = (RequestMapping) clazz.getAnnotation(RequestMapping.class);
            if (classAnnotation != null) {
                String[] classValue = classAnnotation.value();
                //2.获取方法上的@RequestMapping(xxx)
                RequestMapping methodAnnotation = method.getAnnotation(RequestMapping.class);
                if (methodAnnotation != null) {
                    String[] methodValue = methodAnnotation.value();
                    url = classValue[0] + methodValue[0];

                    //获取访问的ip
                    ip = request.getRemoteAddr();

                    //获取当前操作的用户
                    SecurityContext context = SecurityContextHolder.getContext();//从上下文中获了当前登录的用户
                    User user = (User) context.getAuthentication().getPrincipal();
                    username = user.getUsername();

                    //将日志相关信息封装到SysLog对象
                    SysLog sysLog = new SysLog();
                    sysLog.setExecutionTime(executionTime); //执行时长
                    sysLog.setIp(ip);
                    sysLog.setMethod("[类名] " + clazz.getName() + "[方法名] " + method.getName());
                    sysLog.setUrl(url);
                    sysLog.setUsername(username);
                    sysLog.setVisitTime(visitTime);
                    //System.out.println(sysLog);
                    //调用Service完成操作
                    sysLogService.save(sysLog);
                }
            }
        }
    }
}
