package com.syq.demo.aoplog.aspect;

import com.google.gson.Gson;
import com.syq.demo.aoplog.annotation.SysLog;
import com.syq.demo.aoplog.util.HttpContextUtils;
import com.syq.demo.aoplog.util.IPUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

//我们在这和切面当中声明服务层的接口，来实现将我们获取到的日志存入到数据库当中
@Aspect
@Component
public class SysLogAspect {

   // 然后配置切点，切点实在空方法上去配置的，当然这是简便写法，因为这样的话，后面方法上面的切面就直接可以用户这个空的方法名去代替这样就避免了重复去写契入点
    @Pointcut("@annotation(com.syq.demo.aoplog.annotation.SysLog)")
    public void controllerAspect()
    {
        System.out.println("我是一个切入点");
    }

    @Around("controllerAspect()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();
        //执行方法
        Object result = point.proceed();
        //执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;


        System.out.println(time + "-----------------------------");

        //保存日志
       saveSysLog(point, time);

        return result;
    }


    private void saveSysLog(ProceedingJoinPoint joinPoint, long time) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

      //  SysLogEntity sysLog = new SysLogEntity();
        SysLog syslog = method.getAnnotation(SysLog.class);
        if(syslog != null){
            //注解上的描述
            System.out.println("syslog.value()="+syslog.value());
        }

        //请求的方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
       // sysLog.setMethod(className + "." + methodName + "()");

        System.out.println("sysLog.setMethod="+className + "." + methodName + "()");

        //请求的参数
        Object[] args = joinPoint.getArgs();
        try{
            String params = new Gson().toJson(args);
            //sysLog.setParams(params);
            System.out.println("sysLog.setParams="+params);
        }catch (Exception e){

        }

        //获取request
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        //设置IP地址
       // sysLog.setIp(IPUtils.getIpAddr(request));

        System.out.println("sysLog.setIp="+ IPUtils.getIpAddr(request));

        //用户名
      //  String username = ((SysUserEntity) SecurityUtils.getSubject().getPrincipal()).getUsername();
       // sysLog.setUsername(username);

        System.out.println("time="+time);
       // sysLog.setCreateDate(new Date());
        //保存系统日志
       // sysLogService.save(sysLog);

        SimpleDateFormat simpleFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        System.out.println(simpleFormatter.format(new Date()));
    }

}
