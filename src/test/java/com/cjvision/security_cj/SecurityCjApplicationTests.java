package com.cjvision.security_cj;

import com.cjvision.security_cj.controller.ProductApi;
import com.cjvision.security_cj.mockedUsers.MockedSamuelUser;
import com.cjvision.security_cj.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ContextConfiguration
class SecurityCjApplicationTests {

	@Autowired
	ProductApi productApi;

	@Autowired
	UserService userService;

	@Test
	void contextLoads() {
	}

	@Test
	//@WithMockUser(username = "samuel", roles = {"USER"})
	@MockedSamuelUser
	void getAllProductsTest(){
		var products = productApi.getProducts();
		System.out.println(products);
		assertEquals(0, products.size());
	}

	@Test
	@WithMockUser(username = "john", roles = { "USER'" })
	public void givenRoleViewer_whenCallGetUsername_thenReturnUsername() {
		String userName = userService.getUsername();

		assertEquals("john", userName);
	}


//	With anonymous user
	@Test
	@WithAnonymousUser
	void givenAnomynousUser_whenCallGetUsername_thenAccessDenied() {
		userService.getUsername();
	}
}
