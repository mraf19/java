package programmerzamannow.aop.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class LogAspect {
    @Pointcut("target(programmerzamannow.aop.service.HelloService)")
    public void helloServiceMethod(){}

    @Pointcut("execution(* programmerzamannow.aop.service.HelloService.*(java.lang.String))")
    public void helloServiceMethodStringParam(){}

//    @Before("helloServiceMethodStringParam()")
//    public void logStringParameter(JoinPoint joinPoint){
//        String arg = (String) joinPoint.getArgs()[0];
//        log.info("Execution method with parameter: " + arg);
//    }

    @Before("helloServiceMethodStringParam() && args(name)")
    public void logStringParameter(String name){
        log.info("Execution method with parameter: " + name);
    }

    @Before("helloServiceMethod()")
    public void beforeHelloServiceMethod(JoinPoint joinPoint){
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        log.info("Before " + className + "." + methodName + "()");
    }

    @Around("helloServiceMethod()")
    public Object aroundHelloServiceMethod(ProceedingJoinPoint joinPoint) throws Throwable{
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        try {
            log.info("Around Before " + className + "." + methodName + "()");
            return joinPoint.proceed(joinPoint.getArgs());
        } catch (Throwable throwable){
            log.info("Around Error " + className + "." + methodName + "()");
            throw throwable;
        }finally {
            log.info("Around Finally " + className + "." + methodName + "()");
        }
    }

    @Pointcut("execution(* programmerzamannow.aop.service.*.*(..))")
    public void pointcutServicePackage(){}

    @Pointcut("bean(*Service)")
    public void pointcutBeanService(){}

    @Pointcut("execution(public * *(..))")
    public void pointcutPublicMethod(){}

    @Pointcut("pointcutServicePackage() && pointcutBeanService() && pointcutPublicMethod()")
    public void publicMethodForService(){}

    @Before("publicMethodForService()")
    public void logAllPublicServiceMethod(){
        log.info("Log all public service method");
    }
}
