package com.example.swaggerdemo.component;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

@Aspect
@Component
public class LogAspect {
    private static final String TIMESTAMP_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";
    private static final Logger log = LoggerFactory.getLogger("TraceLogger");
    private ObjectMapper mapper = new ObjectMapper();

    private String host = "";
    private String ip = "";
    private String clientIp = "";
    private String clientUrl = "";

    @PostConstruct
    public void init() throws Exception {
        InetAddress addr = InetAddress.getLocalHost();
        this.host = addr.getHostName();
        this.ip = addr.getHostAddress();
    }
/*

    @Around("bean(*Controller)")
    public Object controllerAroundLogging(ProceedingJoinPoint pjp) throws Throwable {

        String timeStamp = new SimpleDateFormat(TIMESTAMP_FORMAT).format(new Timestamp(System.currentTimeMillis()));
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        this.clientIp = request.getRemoteAddr();
        this.clientUrl = request.getRequestURL().toString();
        String callFunction = pjp.getSignature().getDeclaringTypeName() + "." + pjp.getSignature().getName();

        TraceLogger traceLogger = new TraceLogger();
        traceLogger.setTimestamp(timeStamp);
        traceLogger.setHostname(host);
        traceLogger.setHostIp(ip);
        traceLogger.setClientIp(clientIp);
        traceLogger.setClientUrl(clientUrl);
        traceLogger.setCallFunction(callFunction);
        traceLogger.setType("CONTROLLER_REQ");
        traceLogger.setParameter(mapper.writeValueAsString(request.getParameterMap()));
        log.info( mapper.writeValueAsString(traceLogger));

        Object result = pjp.proceed();

        timeStamp = new SimpleDateFormat(TIMESTAMP_FORMAT).format(new Timestamp(System.currentTimeMillis()));

        traceLogger.setTimestamp(timeStamp);
        traceLogger.setType("CONTROLLER_RES");
        traceLogger.setParameter(mapper.writeValueAsString(result));
        log.info(mapper.writeValueAsString(traceLogger));

        return result;
    }
*/



    @Before("bean(*ServiceImpl)")
    public void serviceBeforeLogging(JoinPoint pjp) throws Throwable {
        String timeStamp = new SimpleDateFormat(TIMESTAMP_FORMAT).format(new Timestamp(System.currentTimeMillis()));
        String callFunction = pjp.getSignature().getDeclaringTypeName() + "." + pjp.getSignature().getName();

        Object[] argNames = pjp.getArgs();

        TraceLogger traceLogger = new TraceLogger();
        traceLogger.setTimestamp(timeStamp);
        traceLogger.setHostname(host);
        traceLogger.setHostIp(ip);
        traceLogger.setClientIp(clientIp);
        traceLogger.setClientUrl(clientUrl);
        traceLogger.setCallFunction(callFunction);
        traceLogger.setType("SERVICE_REQ");
        traceLogger.setParameter(mapper.writeValueAsString(argNames));
        log.info(mapper.writeValueAsString(traceLogger));
    }
/*

    @AfterReturning(pointcut="bean(*ServiceImpl)", returning="retVal")
    public void serviceAfterReturningLogging(JoinPoint pjp, Object retVal) throws Throwable {
        String timeStamp = new SimpleDateFormat(TIMESTAMP_FORMAT).format(new Timestamp(System.currentTimeMillis()));
        String callFunction = pjp.getSignature().getDeclaringTypeName() + "." + pjp.getSignature().getName();

        TraceLogger traceLogger = new TraceLogger();
        traceLogger.setTimestamp(timeStamp);
        traceLogger.setHostname(host);
        traceLogger.setHostIp(ip);
        traceLogger.setClientIp(clientIp);
        traceLogger.setClientUrl(clientUrl);
        traceLogger.setCallFunction(callFunction);
        traceLogger.setType("SERVICE_RES");
        traceLogger.setParameter(mapper.writeValueAsString(retVal));
        log.info(mapper.writeValueAsString(traceLogger));

    }

 */
}
