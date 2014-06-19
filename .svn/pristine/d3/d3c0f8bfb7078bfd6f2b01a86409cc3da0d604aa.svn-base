package com.sp.security;

import com.sp.entity.auth.PasswordResetToken;

public interface PasswordResetDao {

	Long getIdByToken(String token);

	void deleteToken(String token);

	PasswordResetToken createToken(long userId, String token);

	PasswordResetToken getResetToken(String token);

}
