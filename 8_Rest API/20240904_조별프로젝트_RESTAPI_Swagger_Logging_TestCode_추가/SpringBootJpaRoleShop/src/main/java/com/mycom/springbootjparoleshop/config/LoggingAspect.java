package com.mycom.springbootjparoleshop.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.mycom.springbootjparoleshop.user.dto.UserDto;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Aspect
@Component
@RequiredArgsConstructor
public class LoggingAspect {
	
	// logger
		private final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);
		
		// session (호출 사용자 정보)
		private final HttpSession session;
		
		// pointcut
//		@Pointcut(value = "execution( * com.mycom.springbootjparoleshop.user..*(..) )") 
//		private void logPointcut() {}
		
		@Pointcut(value = "execution( * com.mycom.springbootjparoleshop.user..*(..) )") 
		private void userPointcut() {}
		
		@Pointcut(value = "execution( * com.mycom.springbootjparoleshop.orders..*(..) )") 
		private void ordersPointcut() {}
		
		@Pointcut(value = "execution( * com.mycom.springbootjparoleshop.product..*(..) )") 
		private void productPointcut() {}
		
		// advise
//		@Before("logPointcut()")
		@Before("userPointcut() || ordersPointcut() || productPointcut()")
		public void logMethodCall(JoinPoint joinPoint) {
			// 누가 언제 무슨 메소드를 호출하는지
			UserDto userDto = (UserDto) session.getAttribute("userDto");
			if( userDto == null ) return;
			
			String methodName = joinPoint.getSignature().getName();
			
			logger.info("{}가 {}를 호출했습니다.", userDto.getEmail(), methodName);
			
		}

}
