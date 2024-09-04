package com.mycom.springbootjparoleshop.user;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.mycom.springbootjparoleshop.orders.service.OrdersService;
import com.mycom.springbootjparoleshop.product.service.ProductService;
import com.mycom.springbootjparoleshop.user.controller.UserController;
import com.mycom.springbootjparoleshop.user.entity.User;
import com.mycom.springbootjparoleshop.user.repository.UserRepository;
import com.mycom.springbootjparoleshop.user.service.UserService;

import jakarta.persistence.EntityManager;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@WebMvcTest
@Slf4j
public class Test_User_DI {
	
	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	UserController userController;
	
	@MockBean
	UserService userService;
	
	@MockBean
	UserRepository userRepository;
	
	@MockBean
	OrdersService orderService;

	@MockBean
	ProductService productService;
	
	@Test
	void testAssertAll() {
		assertAll("묶음 DI 테스트", 
				() -> assertNotNull(userController), 
				() -> assertNotNull(userService),
				() -> assertNotNull(userRepository)
		);
	}
	
	@MockBean
	HttpSession session;
	
	@Test
	void testSession() {
		log.debug("session test start");
		
		assertNotNull(session);
		
		log.debug("session test end");
	}
	
//	@MockBean
//	EntityManager entityManager;
//	
//	@Test
//	void test_JPA_DI() {
//		log.debug("jpa test start");
//		
//		assertNotNull(entityManager);
//		
//		User user = entityManager.find(User.class, 1);
//		
//		assertNotNull(user);
//		
//		log.debug("jpa test end");
//	}

}
