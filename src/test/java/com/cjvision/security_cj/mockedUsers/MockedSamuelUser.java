package com.cjvision.security_cj.mockedUsers;

import org.springframework.security.test.context.support.WithMockUser;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@WithMockUser(value = "samuel", roles = "USER")
public @interface MockedSamuelUser {
}

