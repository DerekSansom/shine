package com.sp.credits;

import org.springframework.stereotype.Repository;

import com.sp.entity.AvailableCreditsEntity;

@Repository
public interface AvailableCreditsDao {

	AvailableCreditsEntity getAvailableCreditsByUserId(int userId);

}
