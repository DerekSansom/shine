package com.sp.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sp.entity.auth.AuthToken;
import com.sp.security.AuthDao;

@Component
public class AuthService {

	@Autowired
	private AuthDao authDao;

	@Autowired
	private TokenGenerator tokenGenerator;

	@Transactional
	public String authenticate(long userId) {

		AuthToken authToken = authDao.createToken(userId, generateToken(userId));

		return authToken.getToken();
	}

	public String createTokenForUser(long userId) {

		AuthToken authToken = authDao.createToken(userId, generateToken(userId));

		return authToken.getToken();

	}

	private String generateToken(long userId) {

		String token = tokenGenerator.generateToken();
		return token + userId;
	}

	public Long getUserIdFromToken(String token) {

		Long userId = authDao.getIdByToken(token);
		return userId;
	}
}
