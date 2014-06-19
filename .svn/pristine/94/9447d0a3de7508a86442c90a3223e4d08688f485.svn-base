package com.sp.player;

import java.util.List;

import com.shine.objects.ShineLocation;
import com.sp.entity.PlayerEntity;

public interface PlayerDao {

	void save(PlayerEntity user);

	PlayerEntity getUserByEmail(String email);

	PlayerEntity getPlayerByName(String username);

	PlayerEntity getPlayerById(int id);

	PlayerEntity doLogin(String cred1, String hashedPassword);

	void updateUserWithLoc(PlayerEntity user, ShineLocation loc);

	List<PlayerEntity> getNearPlayers(ShineLocation userloc, int id, double radius);

	void deleteUser(int id);

	PlayerEntity getUserByCredential(String cred1);

}
