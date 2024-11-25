package net.developia.aop;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j;

@Aspect
@Log4j
@Component
// 얘는 로그 ㅊ처리 한방에다 한다?얘가 다 담당한다.
public class SystemLogAdvice {
	@AfterThrowing(pointcut="execution(* net.developia.service.BoardService*.*(..))", 
			throwing="exception")
	public void logException(Exception exception) {
		log.info("Exception....!!");
		log.info("exception: " + exception);
		log.info("♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥");
		log.info("♥ @AfterThrowing ADVICE ♥");
		log.info("♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥♥");
		log.info("exception: "+exception.getMessage());
	}
}
