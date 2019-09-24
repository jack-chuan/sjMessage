package cn.com.sparknet.sjMessage.app.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class LogAspectHandler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    /**
     * 定义一个切面，拦截com.itcodai.course09.controller包下的所有方法
     * @author leolee
     * @date 2018-08-08 11:13
     * @param
    */
    @Pointcut("execution(* cn.com.sparknet.sjMessage.app.controller..*.*(..))")
    public void pointCut(){}
    
    /**
     * 在上面定义的切面方法之前执行该方法
     * @author leolee
     * @date 2018-08-08 11:15
     * @param joinPoint
    */
    @Before("pointCut()")
    public void doBefore(JoinPoint joinPoint){
        logger.info("---- doBefore ----");

        Signature signature = joinPoint.getSignature();

        String declaringTypeName = signature.getDeclaringTypeName();

        String funcName = signature.getName();
        logger.info("-->> It would execute method: {}, belong to package: {}", funcName, declaringTypeName );

        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest httpServletRequest = servletRequestAttributes.getRequest();

        String url = httpServletRequest.getRequestURL().toString();
        String ip = httpServletRequest.getRemoteAddr();
        logger.info("-->> User requested url: {}, ip address: {}", url, ip);
    }
    
    /**
     * 在上面定义的切面方法之后执行该方法
     * @author leolee
     * @date 2018-08-08 11:23
     * @param joinPoint
    */
    @After("pointCut()")
    public void doAfter(JoinPoint joinPoint){
        logger.info("---- doAfter ----");
        Signature signature = joinPoint.getSignature();
        String method = signature.getName();
        logger.info("--|| Method {} has done.", method);
    }
    
    /**
     * 在上面定义的切面方法返回后执行该方法，可以捕获返回对象或者对返回对象进行增强
     * @author leolee
     * @date 2018-08-08 11:27
     * @param joinPoint
     * @param result
    */
    @AfterReturning(pointcut = "pointCut()", returning = "result")
    public void doAfterReturning(JoinPoint joinPoint, Object result){
        Signature signature = joinPoint.getSignature();
        String classMethod = signature.getName();
        logger.info("-->> Method {} has done, return {}", classMethod, result);
        logger.info("-->> Stronger returning: {} ", result + " Strong");
    }
    
    /**
     * 在上面定义的切面方法执行抛异常时，执行该方法
     * @author leolee
     * @date 2018-08-08 11:32
     * @param joinPoint
     * @param ex
    */
    @AfterThrowing(pointcut = "pointCut()", throwing = "ex")
    public void doAfterThrowing(JoinPoint joinPoint, Throwable ex){
        Signature signature = joinPoint.getSignature();
        String method = signature.getName();
        logger.info("--XX Method {} executed with error, ex: {}", method, ex);
    }
}
