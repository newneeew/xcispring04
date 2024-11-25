package net.developia.security;

import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.extern.log4j.Log4j;

@Log4j
public class CustomNoOpPasswordEncoder implements PasswordEncoder {
	
	@Override
	public String encode(CharSequence rawPassword) {
		log.warn("before encode :" + rawPassword);
		return rawPassword.toString();
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) { // 인코딩된 값, DB에서 받아온 값
		log.warn("matches: " + rawPassword + ":" + encodedPassword);
		return rawPassword.toString().equals(encodedPassword); // 둘이 일치하면 True 반환하여 login 실행
	}

}
