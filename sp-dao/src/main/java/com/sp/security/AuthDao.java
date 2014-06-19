package com.sp.security;

import com.sp.entity.auth.AuthToken;

public interface AuthDao {

	Long getIdByToken(String token);

	AuthToken createToken(long userId, String token);

}
