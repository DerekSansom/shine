package com.sp.user;

import org.springframework.stereotype.Repository;

import com.sp.entity.UserEntity;

@Repository
public interface UserDao {
	
	UserEntity getUserById(int id);

	UserEntity getUser(String credential, String password);

	UserEntity getUserByName(String username);

	UserEntity getUserByEmail(String email);

}
