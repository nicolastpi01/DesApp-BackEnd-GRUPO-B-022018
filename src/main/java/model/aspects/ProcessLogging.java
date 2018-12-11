package model.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(1)
public class ProcessLogging {

	static Logger logger = LoggerFactory.getLogger(ProcessLogging.class);

	/// ANNOTATION POINTCUT////
	@Around("@annotation(LogExecutionAction)")
	public Object logExecutionTimeAnnotation(ProceedingJoinPoint joinPoint) throws Throwable {
		Object proceed = null;
		try{
			logger.info("/////// AROUND START  logExecutionTime annotation //////");
			long start = System.currentTimeMillis();
			proceed = joinPoint.proceed();
			long executionTime = System.currentTimeMillis() - start;
			logger.info("/////// " + joinPoint.getSignature() + " executed in " + executionTime + "ms");
			logger.info("/////// AROUND FINISH  logExecutionTime annotation ///////");
			logger.info("/////// NO ERRORS FOUND //////");
			return proceed;
		}catch(Exception e){
			logger.error("/////// ERRORS: " + e.getMessage() + " ////// ");
			return null;
		}
	}
}
